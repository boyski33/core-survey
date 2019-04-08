package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.survey.Survey;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class SurveyRestResource {

  public String id;

  @NotBlank
  @Email
  public String ownerEmail;

  @NotBlank
  public String title;

  public String description;

  public Instant timestamp;

  @NotEmpty
  @Valid
  public List<QuestionRestResource> questions;

  public SurveyRestResource() {
  }

  public SurveyRestResource(
      String id,
      @Email String ownerEmail,
      @NotBlank String title,
      String description,
      Instant timestamp,
      @NotEmpty @Valid List<QuestionRestResource> questions) {
    this.id = id;
    this.ownerEmail = ownerEmail;
    this.title = title;
    this.description = description;
    this.timestamp = timestamp;
    this.questions = questions;
  }

  public Survey toDomainObject() {
    List<Question> questionList =
        questions.stream().map(QuestionRestResource::toDomainObject).collect(Collectors.toList());

    return new Survey(id, ownerEmail, title, description, timestamp, questionList);
  }

  public static SurveyRestResource fromDomainObject(Survey survey) {
    List<QuestionRestResource> questionRestResources = survey.getQuestions().stream()
        .map(QuestionRestResource::fromDomainObject)
        .collect(Collectors.toList());

    return new SurveyRestResource(
        survey.getId(),
        survey.getOwnerEmail(),
        survey.getTitle(),
        survey.getDescription(),
        survey.getTimestamp(),
        questionRestResources);
  }

}
