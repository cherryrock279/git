package com.calypso.finance.banking.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.calypso.finance.banking.App;
import com.calypso.finance.banking.model.PersonPersistence;
import com.calypso.finance.banking.model.UserPersistence;
import com.calypso.finance.banking.service.UserService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=App.class)
@WebAppConfiguration
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class UserControllerTest {
	private static final String BASE_RESOURCE="/v1/user";
	private static final String CREATE_PERSON="/person";
	private MockMvc mocMvc;
	@InjectMocks
	userController controller;
	@Mock
	UserService userService;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mocMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}
	@Test
	public void testCreatePerson() {
		PersonPersistence person=createPerson();
		when(userService.creatPerson(any(PersonPersistence.class))).thenReturn(person);
		try {
			this.mocMvc.perform(post(BASE_RESOURCE+CREATE_PERSON).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).content(createPersonString(person)))
					.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private PersonPersistence createPerson() {
		PersonPersistence person=new PersonPersistence();
		person.setAge(22);
		person.setName("S");
		person.setCity("NYC");
		Set<UserPersistence> users=new HashSet<UserPersistence>();
		UserPersistence user=new UserPersistence();
		user.setName("SUPER");
		user.setPassword("221");
		users.add(user);
		person.setUsers(users);
		return person;
	}
	private String createPersonString(PersonPersistence person) {
		ObjectMapper mapper=new ObjectMapper();
		StringWriter writer=new StringWriter();
		try {
			mapper.writeValue(writer, person);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(writer.toString());
		return writer.toString();
	}
}
