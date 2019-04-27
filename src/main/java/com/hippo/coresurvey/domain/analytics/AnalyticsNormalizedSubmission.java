package com.hippo.coresurvey.domain.analytics;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.user.Gender;

import java.util.List;

public class AnalyticsNormalizedSubmission {

  private int userAge;
  private Gender userGender;
  private List<AnsweredQuestion> answers;

  public AnalyticsNormalizedSubmission() {
  }

  public AnalyticsNormalizedSubmission(int userAge, Gender userGender, List<AnsweredQuestion> answers) {
    this.userAge = userAge;
    this.userGender = userGender;
    this.answers = answers;
  }

  public int getUserAge() {
    return userAge;
  }

  public Gender getUserGender() {
    return userGender;
  }

  public List<AnsweredQuestion> getAnswers() {
    return answers;
  }
}
