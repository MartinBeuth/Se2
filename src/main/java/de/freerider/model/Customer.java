package de.freerider.model;
import java.util.*;
import java.time.*;

public class Customer
{
    private String id;
    private String lastName;
    private String firstName;
    private String contact;
    enum Status{
        New,InRegistration,Active,Suspended,Deleted;
    }
    private Status status;


    public Customer(String id, String lastName, String firstName, String contact, Status status){
        this.id=null;
        this.lastName=lastName;
        this.firstName=firstName;
        this.contact=contact;
        this.status=Status.New;
    }

    public String getId() {
        return id;
    }

    private String getLastName() {
        return lastName;
    }

    private String getFirstName() {
        return firstName;
    }

    private String getContact() {
        return contact;
    }

    private Status getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setContact(String contact) {
        this.contact = contact;
    }

    private void setStatus(Status status){
        this.status = status;
    }


}
