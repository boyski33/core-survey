package com.hippo.coresurvey.domain;

import com.hippo.coresurvey.domain.util.CollectionsUtil;

import java.util.List;

public class Survey {

  private String id;
  private String title;
  private String description;
  private List<Question> questions;

  public Survey() {
  }

  public Survey(String id, String title, String description, List<Question> questions) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.questions = CollectionsUtil.ofNullableList(questions);
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  @Override
  public String toString() {
    return "Survey{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

}
