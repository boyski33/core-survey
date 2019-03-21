package com.hippo.coresurvey.domain.submission;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.survey.Survey;

import java.time.Instant;
import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;
import static com.hippo.coresurvey.domain.util.DateTimeUtil.nowIfNull;

public class Submission {

  private String id;
  private Instant timestamp;
  private Survey survey;
  private List<AnsweredQuestion> answers;

  public Submission() {
  }

  public Submission(String id, Instant timestamp, Survey survey, List<AnsweredQuestion> answers) {
    this.id = id;
    this.timestamp = nowIfNull(timestamp);
    this.survey = new Survey(survey);
    this.answers = ofNullableList(answers);
  }

  public Submission(Submission submission) {
    this(submission.id, submission.timestamp, submission.survey, submission.answers);
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

  public List<AnsweredQuestion> getAnswers() {
    return answers;
  }
}
