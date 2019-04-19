package com.hippo.coresurvey.domain.question;

public class AnsweredQuestion {

  private Question question;
  private String answer;

  public AnsweredQuestion() {
  }

  public AnsweredQuestion(Question question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  public Question getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }
}
