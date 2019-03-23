package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.survey.Survey;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

public class SurveyResource {

  public String id;

  @NotBlank
  public String title;

  public String description;

  public Instant timestamp;

  @NotEmpty
  public List<Question> questions;

  public SurveyResource() {
  }

  public SurveyResource(
      String id,
      String title,
      String description,
      Instant timestamp,
      List<Question> questions) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.timestamp = timestamp;
    this.questions = questions;
  }

  public Survey toDomainObject() {
    return new Survey(id, title, description, timestamp, questions);
  }

  public static SurveyResource fromDomainObject(Survey survey) {
    return new SurveyResource(
        survey.getId(),
        survey.getTitle(),
        survey.getDescription(),
        survey.getTimestamp(),
        survey.getQuestions());
  }

}
