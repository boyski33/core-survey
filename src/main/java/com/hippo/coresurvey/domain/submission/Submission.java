package com.hippo.coresurvey.domain.submission;

import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.question.AnsweredQuestion;

import java.time.Instant;
import java.util.List;

public class Submission {

  private String id;
  private Instant timestamp;
  private Survey survey;
  private List<AnsweredQuestion> answeredQuestions;

  public Submission() {
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
