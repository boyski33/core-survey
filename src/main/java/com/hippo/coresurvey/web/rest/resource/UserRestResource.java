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

  @Email(message = "Email not formatted properly.")
  public String email;

  public String firstName;

  public String lastName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public Date dateOfBirth;

  public Gender gender;

  public UserRestResource() {
  }

  public UserRestResource(
      String id,
      @Email String email,
      String firstName,
      String lastName,
      Date dateOfBirth,
      Gender gender) {
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
    if (user == null) {
      return null;
    }

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
