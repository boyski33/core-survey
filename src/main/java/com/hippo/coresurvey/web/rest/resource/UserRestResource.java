package com.hippo.coresurvey.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hippo.coresurvey.domain.user.Gender;
import com.hippo.coresurvey.domain.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class UserRestResource {

  public String id;

  @NotBlank
  @Email(message = "Email not formatted properly.")
  public String email;

  public String firstName;

  public String lastName;

  @NotNull
  @Past(message = "Date of birth should be in the past")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public Date dateOfBirth;

  @NotNull
  public Gender gender;

  @JsonProperty("is_predicted")
  public Boolean isPredicted;

  public UserRestResource() {
  }

  public UserRestResource(String id,
                          @Email String email,
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

  public User toDomainObject() {
    return new User(id, email, firstName, lastName, dateOfBirth, gender, isPredicted);
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
        user.getGender(),
        user.getIsPredicted()
    );
  }

}
