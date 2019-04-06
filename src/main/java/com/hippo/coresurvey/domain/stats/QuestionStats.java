package com.hippo.coresurvey.domain.stats;

import com.hippo.coresurvey.domain.question.Question;

import java.util.Map;

public class QuestionStats {

  private Question question;
  private Map<String, Integer> answerCount;

  public QuestionStats() {
  }

  public QuestionStats(Question question, Map<String, Integer> answerCount) {
    this.question = question;
    this.answerCount = answerCount;
  }

  public Question getQuestion() {
    return question;
  }

  public Map<String, Integer> getAnswerCount() {
    return answerCount;
  }
}
