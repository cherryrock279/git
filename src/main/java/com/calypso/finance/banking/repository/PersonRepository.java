package com.calypso.finance.banking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.calypso.finance.banking.model.PersonPersistence;

public interface PersonRepository extends CrudRepository<PersonPersistence,Integer>{
	public List<PersonPersistence> findByNameAndAge(String name,int age);
}
