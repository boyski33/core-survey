package com.hippo.coresurvey.domain.question;

import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;

public class Question {

  private String key;
  private String label;
  private int order;
  private QuestionControlType controlType;
  private List<QuestionOption> options;

  public Question() {
  }

  public Question(String key, String label, int order, QuestionControlType controlType, List<QuestionOption> options) {
    this.key = key;
    this.label = label;
    this.order = order;
    this.controlType = controlType;
    this.options = ofNullableList(options);
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

  public QuestionControlType getControlType() {
    return controlType;
  }

  public List<QuestionOption> getOptions() {
    return options;
  }
}
