package de.freerider.model;
import java.util.*;
import java.time.*;

import de.freerider.repository.*;
public class Customer
{
    public String id;
    public String lastName;
    public String firstName;
    public String contact;
    public enum Status{
        New,InRegistration,Active,Suspended,Deleted;
    }
    public Status status;
    public IDGenerator idGen = new IDGenerator("C",
			IDGenerator.IDTYPE.NUM, 6);


    public Customer(String id, String lastName, String firstName, String contact){
        this.id=null;
        this.lastName=lastName;
        this.firstName=firstName;
        this.contact=contact;
        Status status = Status.New;
    }
    
    public Customer(String lastName, String firstName, String contact){
        id=null;
        this.lastName=lastName;
        this.firstName=firstName;
        this.contact=contact;
        Status status = Status.New;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
    	return lastName;
    	
    }

    public String getFirstName() {
        return firstName;
    }

    public String getContact() {
        return contact;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setStatus(Status string){
        this.status = string;
    }
    



}
