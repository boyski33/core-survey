package com.hippo.coresurvey.repository.survey;

import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.survey.Survey;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;

@Document(collection = "surveys")
@TypeAlias("SurveyMongoEntity")
public class SurveyMongoEntity {

  @Id
  private String id;

  private String title;
  private String description;
  private List<Question> questions;

  public SurveyMongoEntity() {
  }

  public SurveyMongoEntity(String id, String title, String description, List<Question> questions) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.questions = ofNullableList(questions);
  }

  public Survey toDomainObject() {
    return new Survey(id, title, description, questions);
  }

  public static SurveyMongoEntity fromDomainObject(Survey survey) {
    return new SurveyMongoEntity(
        survey.getId(),
        survey.getTitle(),
        survey.getDescription(),
        survey.getQuestions());
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

  public List<Question> getQuestions() {
    return questions;
  }
}
