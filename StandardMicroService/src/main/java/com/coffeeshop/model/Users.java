/**
 *
 * <p>Filename: Users.java
 * <p>Created At: Aug 19th 2018
 *
 * @author DatNT
 */
package com.coffeeshop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

public class Users {

    private String name;
    private Long salary;
    private Integer id;
    
    @JsonTypeInfo(
    	      use = JsonTypeInfo.Id.NAME, 
    	      include = As.PROPERTY, 
    	      property = "type")
    private List<Addresses> addresses;
    
    @JsonTypeInfo(
  	      use = JsonTypeInfo.Id.NAME, 
  	      include = As.PROPERTY, 
  	      property = "type")
    private List<Roles> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @JsonProperty("customname")
    public String getTheName() {
        return "CUSTOMED-NAME-"+name;
    }
}
