package com.hippo.coresurvey.domain.question;

import java.util.List;

public class Question {

  private String key;
  private String label;
  private int order;
  private String placeholder;
  private QuestionControlType controlType;
  private List<QuestionOption> options;

  public Question() {
  }

  public String getKey() {
    return key;
  }

  public String getLabel() {
    return label;
  }

  public int getOrder() {
    return order;
  }

  public String getPlaceholder() {
    return placeholder;
  }

  public QuestionControlType getControlType() {
    return controlType;
  }

  public List<QuestionOption> getOptions() {
    return options;
  }
}
