package com.hippo.coresurvey.repository.user;

import com.hippo.coresurvey.domain.user.Gender;
import com.hippo.coresurvey.domain.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "users")
@TypeAlias("UserMongoEntity")
public class UserMongoEntity {

  @Id
  private String id;

  @Indexed(unique = true)
  private String email;

  private String firstName;

  private String lastName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dateOfBirth;

  private Gender gender;

  public UserMongoEntity() {
  }

  public UserMongoEntity(
      String id,
      String email,
      String firstName,
      String lastName,
      LocalDate dateOfBirth,
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

  public static UserMongoEntity fromDomainObject(User user) {
    return new UserMongoEntity(
        user.getId(),
        user.getEmail(),
        user.getFirstName(),
        user.getLastName(),
        user.getDateOfBirth(),
        user.getGender()
    );
  }

  public String getId() {
    return id;
  }
}
