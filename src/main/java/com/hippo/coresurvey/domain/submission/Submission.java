package com.hippo.coresurvey.domain.submission;

import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.question.AnsweredQuestion;

import java.time.Instant;
import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;
import static com.hippo.coresurvey.domain.util.DateTimeUtil.nowIfNull;

public class Submission {

  private String id;
  private Instant timestamp;
  private Survey survey;
  private List<AnsweredQuestion> answeredQuestions;

  public Submission() {
  }

  public Submission(String id, Instant timestamp, Survey survey, List<AnsweredQuestion> answeredQuestions) {
    this.id = id;
    this.timestamp = nowIfNull(timestamp);
    this.survey = new Survey(survey);
    this.answeredQuestions = ofNullableList(answeredQuestions);
  }

  public Submission(Submission submission) {
    this(submission.id, submission.timestamp, submission.survey, submission.answeredQuestions);
  }

  public String getId() {
    return id;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public Survey getSurvey() {
    return survey;
  }

  public List<AnsweredQuestion> getAnsweredQuestions() {
    return answeredQuestions;
  }
}
