package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.submission.Submission;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

public class SubmissionRestResource {

  public String id;

  public Instant timestamp;

  @NotNull
  @Valid
  public SurveyRestResource survey;

  @NotEmpty
  public List<AnsweredQuestion> answers;

  public SubmissionRestResource() {
  }

  public SubmissionRestResource(
      String id,
      Instant timestamp,
      @NotNull @Valid SurveyRestResource survey,
      @NotEmpty List<AnsweredQuestion> answers) {
    this.id = id;
    this.timestamp = timestamp;
    this.survey = survey;
    this.answers = answers;
  }

  public Submission toDomainObject() {
    return new Submission(id, timestamp, survey.toDomainObject(), answers);
  }

  public static SubmissionRestResource fromDomainObject(Submission submission) {
    return new SubmissionRestResource(
        submission.getId(),
        submission.getTimestamp(),
        SurveyRestResource.fromDomainObject(submission.getSurvey()),
        submission.getAnswers()
    );
  }
}
