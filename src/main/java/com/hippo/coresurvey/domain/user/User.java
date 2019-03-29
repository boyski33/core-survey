package com.hippo.coresurvey.domain.user;

import java.time.Instant;

public class User {

  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private Instant dateOfBirth;
  private Gender gender;

  public User() {
  }

  public User(String id, String email, String firstName, String lastName, Instant dateOfBirth, Gender gender) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
  }

  public String getId() {
    return id;
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

  public Instant getDateOfBirth() {
    return dateOfBirth;
  }

  public Gender getGender() {
    return gender;
  }
}
