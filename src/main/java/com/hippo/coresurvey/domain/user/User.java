package com.hippo.coresurvey.domain.user;

import java.util.Date;

public class User {

  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private Date dateOfBirth;
  private Gender gender;
  private Boolean isPredicted;

  public User() {
  }

  public User(String id,
              String email,
              String firstName,
              String lastName,
              Date dateOfBirth,
              Gender gender,
              Boolean isPredicted) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.isPredicted = isPredicted;
  }

  public User(User other) {
    this(other.id, other.email, other.firstName, other.lastName, other.dateOfBirth, other.gender, other.isPredicted);
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

  public Boolean getIsPredicted() {
    return isPredicted != null && isPredicted;
  }
}
