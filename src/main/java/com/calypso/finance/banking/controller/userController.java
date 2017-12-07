package com.calypso.finance.banking.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calypso.finance.banking.model.PersonPersistence;
import com.calypso.finance.banking.model.User;
import com.calypso.finance.banking.model.UserPersistence;
import com.calypso.finance.banking.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class userController {
	Logger log=LoggerFactory.getLogger(userController.class);
	//setter based injection
	//@Autowired
	private UserService userService;
	@Autowired
	public userController(UserService userService) {
		this.userService=userService;
	}
	@RequestMapping("/name")
	public List<User> fetchName() {
		List<User> userList=new ArrayList<User>();
		User obj=new User();
		obj.setName("f");
		obj.setAge(24);
		obj.setCity("Fremont");
		userList.add(obj);
		
		User obj2=new User();
		obj2.setName("j");
		obj2.setAge(21);
		obj2.setCity("DC");
		userList.add(obj2);
		
		return userList;
		
	}
	@RequestMapping("/name/{id}")
	public String fetchNameByUserId(@PathVariable(value="id") int userId) {
		return "Frank"+userId;
	}
	@RequestMapping("/nameparam")
	public String fetchNameByParam(@RequestParam(value="id") int userId) {
		return "Data"+userId;
	}
	
	@RequestMapping("/address")
	public String fetchAddress(@RequestParam(value="id") int userId,
			@RequestHeader(value="token") String auth) {
		System.out.println("got Token"+auth);
		return "Validated"+userId;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/name",method=RequestMethod.POST)
	public User creatUser(@RequestBody User userObj) {
		log.info("Name is :{}",userObj.getName());
		log.debug("Age is :{}",userObj.getAge());
		log.info("City is :{}",userObj.getCity());
		return userService.createUser(userObj);
		
	}
	@RequestMapping(value="/person",method=RequestMethod.POST)
	public PersonPersistence createPerson(@RequestBody PersonPersistence personObj) {
		log.info("Name is :{}",personObj.getName());
		log.debug("Age is :{}",personObj.getAge());
		log.info("City is :{}",personObj.getCity());
		Set<UserPersistence> users=new HashSet<UserPersistence>();
		for(UserPersistence user:personObj.getUsers()) {
			user.setPerson(personObj);
			users.add(user);
		}
		personObj.setUsers(users);
		return userService.creatPerson(personObj);
		
	}
	@RequestMapping(value="/person/{id}")
	public PersonPersistence findById(@PathVariable(value="id") int personId) {
		return userService.findById(personId);	
	}
	@RequestMapping(value="/person/all")
	public List<PersonPersistence> findAll(){
		return userService.findAllPerson();
	}
	@RequestMapping(value="/person/{id}",method=RequestMethod.DELETE)
	public void deleteById(@PathVariable(value="id") int personId) {
		userService.deleteById(personId);
	}
	@RequestMapping(value="/person")
	public List<PersonPersistence> findByNameAndAge(@RequestParam(value="name") String name,@RequestParam(value="age") int age){
		return userService.getPersonByNameAndAge(name, age);
		
	}
	@RequestMapping(value="/person/all",method=RequestMethod.DELETE)
	public void deleteAll() {
		userService.deleteAll();
	}
	@PreAuthorize("hasRole('ROlE_ADMIN')")
	@RequestMapping(value="/person",method=RequestMethod.PUT)
	public void updateAgeById(@RequestParam(value="id") int Id,@RequestParam(value="age") int age) {
		userService.updateAge(Id, age);
	}
	@RequestMapping(value="/person/{id}",method=RequestMethod.PUT)
	public PersonPersistence updateNameById(@PathVariable(value="id") int personId,@RequestParam(value="name") String name) {
	return userService.updateName(personId, name);
	}
	@RequestMapping(value="/person",method=RequestMethod.GET)
	public List<PersonPersistence> fetchByName(@RequestParam(value="name") String name){
		return userService.findByName(name);
	}
}
