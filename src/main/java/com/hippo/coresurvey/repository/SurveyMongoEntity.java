package com.hippo.coresurvey.repository;

import com.hippo.coresurvey.domain.Survey;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "surveys")
public class SurveyMongoEntity {

  @Id
  private String id;

  private String title;
  private String description;

  public SurveyMongoEntity() {
  }

  public SurveyMongoEntity(String id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public Survey toDomainObject() {
    return new Survey(id, title, description);
  }

  public static SurveyMongoEntity fromDomainObject(Survey survey) {
    return new SurveyMongoEntity(survey.getId(), survey.getTitle(), survey.getDescription());
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }
}
