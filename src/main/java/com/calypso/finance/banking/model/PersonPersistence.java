package com.calypso.finance.banking.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
@NamedQueries({
	@NamedQuery(name="fetchByName",query="Select p from PersonPersistence p where p.name=?"),
	@NamedQuery(name="fetchByAge",query="Select p from PersonPersistence p where p.age=?")
})
public class PersonPersistence {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PERSONID")
	private int id;
	@Column(name="FULLNAME")
	private String name;
	@Column(name="AGE")
	private int age;
	@Column(name="USERCITY")
	private String city;
	@OneToMany(mappedBy="person",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<UserPersistence> users=new HashSet<UserPersistence>();
	
	
	public Set<UserPersistence> getUsers() {
		return users;
	}
	public void setUsers(Set<UserPersistence> users) {
		this.users = users;
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
	public int getAge() {
		return age;
	}
	public void setAge(int i) {
		this.age = i;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
