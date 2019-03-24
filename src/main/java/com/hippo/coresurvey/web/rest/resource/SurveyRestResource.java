package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.survey.Survey;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

public class SurveyRestResource {

  public String id;

  @NotBlank
  public String title;

  public String description;

  public Instant timestamp;

  @NotEmpty
  public List<Question> questions;

  public SurveyRestResource() {
  }

  public SurveyRestResource(
      String id,
      @NotBlank String title,
      String description,
      Instant timestamp,
      @NotEmpty List<Question> questions) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.timestamp = timestamp;
    this.questions = questions;
  }

  public Survey toDomainObject() {
    return new Survey(id, title, description, timestamp, questions);
  }

  public static SurveyRestResource fromDomainObject(Survey survey) {
    return new SurveyRestResource(
        survey.getId(),
        survey.getTitle(),
        survey.getDescription(),
        survey.getTimestamp(),
        survey.getQuestions());
  }

}
