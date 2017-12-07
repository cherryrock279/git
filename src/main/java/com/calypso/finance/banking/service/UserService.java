package com.calypso.finance.banking.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calypso.finance.banking.dao.PersonDao;
import com.calypso.finance.banking.model.PersonPersistence;
import com.calypso.finance.banking.model.User;
import com.calypso.finance.banking.repository.PersonRepository;
@Service
public class UserService {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	private PersonDao personDao;
	Logger log=LoggerFactory.getLogger(UserService.class);
	public User createUser(User user) {
		log.info("Saving data for :{}",user.getName());
		//ToDo:Invoke DAO class
		PersonPersistence person=new PersonPersistence();
		person.setName(user.getName());
		person.setAge(user.getAge());
		person.setCity(user.getCity());
		//persistence go to the spring data
		//PersonPersistence p=personRepository.save(person);
		PersonPersistence p=personDao.inserPerson(person);
		user.setUserId(p.getId());
		return user;
	}
	public PersonPersistence findById(int personId) {
		log.info("Fetching data for{}",personId);
		PersonPersistence person=personRepository.findOne(personId);
		return person;
	}
	public List<PersonPersistence> findAllPerson(){
		log.info("Fetching all Person Record");
		return (List<PersonPersistence>) personRepository.findAll();
	}
	public void deleteById(int personId) {
		log.info("delete person {}",personId);
		personRepository.delete(personId);
	}
	public void deleteAll() {
		log.info("delete person all");
		personRepository.deleteAll();
	}
	public void updateAge(int personId,int age) {
		log.info("update person"+personId+"age"+age);
		PersonPersistence person=personRepository.findOne(personId);
		person.setAge(age);
		personRepository.save(person);
	}
	public List<PersonPersistence> getPersonByNameAndAge(String name,int age){
		return personRepository.findByNameAndAge(name, age);
	}
	public PersonPersistence updateName(int personId,String name) {
		return personDao.updatePerson(personId, name);
	}
	public List<PersonPersistence> findByName(String name){
		return personDao.getPersonByName(name);
	}
	public PersonPersistence creatPerson(PersonPersistence personObj) {
		return personDao.inserPerson(personObj);
	}
}
