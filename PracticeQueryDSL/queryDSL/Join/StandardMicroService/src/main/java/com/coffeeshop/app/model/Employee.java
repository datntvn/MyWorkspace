// This file is for first Exercise
package com.coffeeshop.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
There is a  problem with table naming convention, TABLE NAME MUST BE all lower case
Even use: @Table(name="\"Employee\"") won't fix problem 
Instead, if we want to have a proper customized name, we must implement a custom naming class

CREATE TABLE IF NOT EXISTS employee
(
    id   INT     NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    dept VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
insert into employee (name, dept) values ("Foo Bar", "Tropic");
insert into employee (name, dept) values ("John Doe", "Wood");
*/
@Entity
public class Employee {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	private String dept;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + "]";
	}

}