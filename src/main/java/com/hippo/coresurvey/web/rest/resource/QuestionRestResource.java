package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.question.QuestionControlType;
import com.hippo.coresurvey.domain.question.QuestionOption;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class QuestionRestResource {

  @NotBlank
  public String key;

  @NotBlank
  public String label;

  public int order;

  @NotNull
  public QuestionControlType controlType;

  public List<QuestionOption> options;

  public QuestionRestResource() {
  }

  public QuestionRestResource(@NotBlank String key,
                              @NotBlank String label,
                              int order,
                              @NotNull QuestionControlType controlType,
                              List<QuestionOption> options) {
    this.key = key;
    this.label = label;
    this.order = order;
    this.controlType = controlType;
    this.options = options;
  }

  public Question toDomainObject() {
    return new Question(key, label, order, controlType, options);
  }

  public static QuestionRestResource fromDomainObject(Question question) {
    return new QuestionRestResource(
        question.getKey(),
        question.getLabel(),
        question.getOrder(),
        question.getControlType(),
        question.getOptions());
  }
}
