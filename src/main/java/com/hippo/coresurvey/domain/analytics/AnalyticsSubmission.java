package com.hippo.coresurvey.domain.analytics;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.user.Gender;

import java.util.List;

public class AnalyticsSubmission {

  private int userAge;
  private Gender userGender;
  private List<AnsweredQuestion> answeredQuestions;

  public AnalyticsSubmission() {
  }

  public AnalyticsSubmission(int userAge, Gender userGender, List<AnsweredQuestion> answeredQuestions) {
    this.userAge = userAge;
    this.userGender = userGender;
    this.answeredQuestions = answeredQuestions;
  }

  public int getUserAge() {
    return userAge;
  }

  public Gender getUserGender() {
    return userGender;
  }

  public List<AnsweredQuestion> getAnsweredQuestions() {
    return answeredQuestions;
  }
}
