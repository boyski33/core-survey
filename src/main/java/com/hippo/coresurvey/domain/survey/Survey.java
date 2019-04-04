package com.hippo.coresurvey.domain.survey;

import com.hippo.coresurvey.domain.question.Question;

import java.time.Instant;
import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;
import static com.hippo.coresurvey.domain.util.DateTimeUtil.nowIfNull;

public class Survey {

  private String id;
  private String ownerEmail;
  private String title;
  private String description;
  private Instant timestamp;

  private List<Question> questions;

  public Survey() {
  }

  public Survey(String id, String email, String title, String description, Instant timestamp, List<Question> questions) {
    this.id = id;
    this.ownerEmail = email;
    this.title = title;
    this.description = description;
    this.timestamp = nowIfNull(timestamp);
    this.questions = ofNullableList(questions);
  }

  public Survey(Survey survey) {
    this(survey.id, survey.ownerEmail, survey.title, survey.description, survey.timestamp, survey.questions);
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
