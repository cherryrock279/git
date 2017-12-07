package com.calypso.finance.banking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.calypso.finance.banking.dao.PersonDao;
import com.calypso.finance.banking.model.PersonPersistence;
@Repository("personDao")
@Transactional(propagation = Propagation.REQUIRED)
public class PersonDaoImpl implements PersonDao {
	Logger log=LoggerFactory.getLogger(PersonDaoImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public PersonPersistence inserPerson(PersonPersistence person) {
		log.info("Insert Data for{}",person.getName());
		entityManager.persist(person);
		entityManager.flush();
		log.info("personId generate:{}",person.getId());
		return person;
	}

	@Override
	public PersonPersistence updatePerson(int personId, String name) {
		log.info("Update Data for{}",personId);
		PersonPersistence person=entityManager.find(PersonPersistence.class, personId);
		person.setName(name);
		entityManager.persist(person);
		return person;
	}

	@Override
	public PersonPersistence deletePerson(int perosnId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonPersistence fecthPersonById(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonPersistence> fetchAllPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonPersistence> getPersonByName(String name) {
		TypedQuery<PersonPersistence> q=entityManager.createNamedQuery("fetchByName",PersonPersistence.class)
				.setParameter(1, name);
		List<PersonPersistence> result=q.getResultList();
		return result;
	}

}
