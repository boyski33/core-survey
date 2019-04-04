package com.hippo.coresurvey.domain.user;

import java.util.Date;

public class User {

  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private Date dateOfBirth;
  private Gender gender;

  public User() {
  }

  public User(String id, String email, String firstName, String lastName, Date dateOfBirth, Gender gender) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
  }

  public User(User other) {
    this(other.id, other.email, other.firstName, other.lastName, other.dateOfBirth, other.gender);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public Gender getGender() {
    return gender;
  }
}
