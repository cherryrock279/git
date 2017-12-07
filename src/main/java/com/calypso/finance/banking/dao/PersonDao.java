package com.calypso.finance.banking.dao;

import java.util.List;

import com.calypso.finance.banking.model.PersonPersistence;

public interface PersonDao {
	public PersonPersistence inserPerson(PersonPersistence person);
	public PersonPersistence updatePerson(int personId,String name);
	public PersonPersistence deletePerson(int perosnId);
	public PersonPersistence fecthPersonById(int personId);
	public List<PersonPersistence> fetchAllPerson();
	public List<PersonPersistence> getPersonByName(String name);
}
