package com.hippo.coresurvey.repository.submission;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.user.User;
import com.hippo.coresurvey.repository.survey.SurveyMongoEntity;
import com.hippo.coresurvey.repository.user.UserMongoEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;
import static com.hippo.coresurvey.domain.util.DateTimeUtil.nowIfNull;

@Document(collection = "submissions")
@TypeAlias("SubmissionMongoEntity")
public class SubmissionMongoEntity {

  @Id
  private String id;

  private Instant timestamp;

  @Valid
  private UserMongoEntity user;

  private SurveyMongoEntity survey;

  private List<AnsweredQuestion> answers;

  public SubmissionMongoEntity() {
  }

  public SubmissionMongoEntity(String id,
                               Instant timestamp,
                               UserMongoEntity user,
                               SurveyMongoEntity survey,
                               List<AnsweredQuestion> answers) {
    this.id = id;
    this.timestamp = nowIfNull(timestamp);
    this.user = user;
    this.survey = survey;
    this.answers = ofNullableList(answers);
  }

  public Submission toDomainObject() {
    User domainUser = user == null ? null : user.toDomainObject();

    return new Submission(id, timestamp, domainUser, survey.toDomainObject(), answers);
  }

  public static SubmissionMongoEntity fromDomainObject(Submission submission) {
    return new SubmissionMongoEntity(
        submission.getId(),
        submission.getTimestamp(),
        UserMongoEntity.fromDomainObject(submission.getUser()),
        SurveyMongoEntity.fromDomainObject(submission.getSurvey()),
        submission.getAnswers()
    );
  }

  public String getId() {
    return id;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public UserMongoEntity getUser() {
    return user;
  }

  public SurveyMongoEntity getSurvey() {
    return survey;
  }

  public List<AnsweredQuestion> getAnswers() {
    return answers;
  }

}
