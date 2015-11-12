package com.boole.index.domain;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
public class AbstractIndexPerson extends AbstractIndexEntity {
    private String firstName;
    private String lastName;
    private String email;

    public AbstractIndexPerson() {
    }

    public AbstractIndexPerson(String firstName, String lastName) {
        this(firstName, lastName, null);
    }

    public AbstractIndexPerson(String firstName, String lastName, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
