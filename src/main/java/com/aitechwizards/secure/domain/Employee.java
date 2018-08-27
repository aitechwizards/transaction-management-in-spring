package com.aitechwizards.secure.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

public class Employee {
	private int id;
	private String name;
	private Set<Address> empAddress;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public Set<Address> getEmpAddress() {
		return empAddress;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empl", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	public void setEmpAddress(Set<Address> empAddress) {
		this.empAddress = empAddress;
	}

}
