package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.user.Gender;
import com.hippo.coresurvey.domain.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserRestResource {

  public String id;

  @NotBlank
  @Email
  public String email;

  public String firstName;

  public String lastName;

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public Date dateOfBirth;

  @NotNull
  public Gender gender;

  public UserRestResource() {
  }

  public UserRestResource(
      String id,
      @Email String email,
      String firstName,
      String lastName,
      @NotNull Date dateOfBirth,
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
