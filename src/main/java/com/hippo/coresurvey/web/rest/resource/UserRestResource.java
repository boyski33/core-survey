package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.user.Gender;
import com.hippo.coresurvey.domain.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;

public class UserRestResource {
  public String id;

  //  @NotBlank
  @Email
  public String email;

  public String firstName;

  public String lastName;

  @NotNull
  public Instant dateOfBirth;

  @NotNull
  public Gender gender;

  public UserRestResource() {
  }

  public UserRestResource(
      String id,
      @Email String email,
      String firstName,
      String lastName,
      @NotNull Instant dateOfBirth,
      @NotNull Gender gender) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
  }

  public User toDomainObject() {
    return new User(id, email, firstName, lastName, dateOfBirth, gender);
  }

  public static UserRestResource fromDomainObject(User user) {
    return new UserRestResource(
        user.getId(),
        user.getEmail(),
        user.getFirstName(),
        user.getLastName(),
        user.getDateOfBirth(),
        user.getGender()
    );
  }

}
