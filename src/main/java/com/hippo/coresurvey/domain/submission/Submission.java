package com.hippo.coresurvey.domain.submission;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.user.User;

import java.time.Instant;
import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;
import static com.hippo.coresurvey.domain.util.DateTimeUtil.nowIfNull;

public class Submission {

  private String id;
  private Instant timestamp;
  private User user;
  private Survey survey;
  private List<AnsweredQuestion> answers;

  public Submission() {
  }

  public Submission(String id, Instant timestamp, User user, Survey survey, List<AnsweredQuestion> answers) {
    this.id = id;
    this.timestamp = nowIfNull(timestamp);
    this.user = user;
    this.survey = survey;
    this.answers = ofNullableList(answers);
  }

  public Submission(Submission other) {
    this(other.id, other.timestamp, other.user, other.survey, other.answers);
  }

  public String getId() {
    return id;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Survey getSurvey() {
    return survey;
  }

  public List<AnsweredQuestion> getAnswers() {
    return answers;
  }
}
