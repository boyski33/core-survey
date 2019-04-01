package com.hippo.coresurvey.web.rest.resource;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.user.User;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

public class SubmissionRestResource {

  public String id;

  public Instant timestamp;

  public UserRestResource user;

  @NotNull(message = "Survey object cannot be null.")
  @Valid
  public SurveyRestResource survey;

  @NotEmpty
  public List<AnsweredQuestion> answers;

  public SubmissionRestResource() {
  }

  public SubmissionRestResource(
      String id,
      Instant timestamp,
      UserRestResource user,
      @NotNull @Valid SurveyRestResource survey,
      @NotEmpty List<AnsweredQuestion> answers) {
    this.id = id;
    this.timestamp = timestamp;
    this.user = user;
    this.survey = survey;
    this.answers = answers;
  }

  public Submission toDomainObject() {
    User domainUser = user == null ? null : user.toDomainObject();

    return new Submission(id, timestamp, domainUser, survey.toDomainObject(), answers);
  }

  public static SubmissionRestResource fromDomainObject(Submission submission) {
    return new SubmissionRestResource(
        submission.getId(),
        submission.getTimestamp(),
        UserRestResource.fromDomainObject(submission.getUser()),
        SurveyRestResource.fromDomainObject(submission.getSurvey()),
        submission.getAnswers()
    );
  }
}
