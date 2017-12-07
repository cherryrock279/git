package com.calypso.finance.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER")
public class UserPersistence {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USERID")
	private int id;
	@Column(name="USERNAME")
	private String name;
	@Column(name="PASSWORD")
	private String password;
	@ManyToOne()
	@JoinColumn(name="PERSONID")
	@JsonIgnore
	private PersonPersistence person;
	public PersonPersistence getPerson() {
		return person;
	}
	public void setPerson(PersonPersistence person) {
		this.person = person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
