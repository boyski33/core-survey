package com.hippo.coresurvey.repository.user;

import com.hippo.coresurvey.domain.user.Gender;
import com.hippo.coresurvey.domain.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Document(collection = "users")
@TypeAlias("UserMongoEntity")
public class UserMongoEntity {

  @Id
  private String id;

  @NotBlank
  @Email
  private String email;

  private String firstName;

  private String lastName;

  @NotNull
  @Past
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date dateOfBirth;

  @NotNull
  private Gender gender;

  private Boolean isPredicted;

  public UserMongoEntity() {
  }

  public UserMongoEntity(String id,
                         @NotBlank String email,
                         String firstName,
                         String lastName,
                         @NotNull Date dateOfBirth,
                         @NotNull Gender gender,
                         Boolean isPredicted) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.isPredicted = isPredicted;
  }

  public UserMongoEntity(UserMongoEntity other) {
    this(other.id, other.email, other.firstName, other.lastName, other.dateOfBirth, other.gender, other.isPredicted);
  }

  public User toDomainObject() {
    return new User(id, email, firstName, lastName, dateOfBirth, gender, isPredicted);
  }

  public static UserMongoEntity fromDomainObject(User user) {
    if (user == null) {
      return null;
    }

    return new UserMongoEntity(
        user.getId(),
        user.getEmail(),
        user.getFirstName(),
        user.getLastName(),
        user.getDateOfBirth(),
        user.getGender(),
        user.getIsPredicted()
    );
  }

  public String getId() {
    return id;
  }
}
