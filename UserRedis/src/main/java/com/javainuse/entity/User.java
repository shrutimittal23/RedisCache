package com.javainuse.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class User implements Serializable {
   
	@NotNull
	private int id;
	
	@NotNull
    private String name;
	
	@NotNull
	@Size(min=10, message="Contact should have atleast 2 characters")
	private String contact;

    public User() {
    }

    public User(int id, String name,String contact) {
        this.id = id;
        this.name = name;
        this.contact=contact;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}


}
