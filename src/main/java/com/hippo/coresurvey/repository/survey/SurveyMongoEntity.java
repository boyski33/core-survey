package com.hippo.coresurvey.repository.survey;

import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.survey.Survey;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;
import static com.hippo.coresurvey.domain.util.DateTimeUtil.nowIfNull;

@Document(collection = "surveys")
@TypeAlias("SurveyMongoEntity")
public class SurveyMongoEntity {

  @Id
  private String id;

  private String ownerEmail;
  private String title;
  private String description;
  private Instant timestamp;

  private List<Question> questions;

  public SurveyMongoEntity() {
  }

  public SurveyMongoEntity(
      String id,
      String email,
      String title,
      String description,
      Instant timestamp,
      List<Question> questions) {
    this.id = id;
    this.ownerEmail = email;
    this.title = title;
    this.description = description;
    this.timestamp = nowIfNull(timestamp);
    this.questions = ofNullableList(questions);
  }

  public Survey toDomainObject() {
    return new Survey(id, ownerEmail, title, description, timestamp, questions);
  }

  public static SurveyMongoEntity fromDomainObject(Survey survey) {
    return new SurveyMongoEntity(
        survey.getId(),
        survey.getOwnerEmail(),
        survey.getTitle(),
        survey.getDescription(),
        survey.getTimestamp(),
        survey.getQuestions());
  }

  public String getId() {
    return id;
  }

  public String getOwnerEmail() {
    return ownerEmail;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public List<Question> getQuestions() {
    return questions;
  }
}
