package com.hippo.coresurvey.repository.submission;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.survey.Survey;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

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
  private Survey survey;
  private List<AnsweredQuestion> answeredQuestions;

  public SubmissionMongoEntity() {
  }

  public SubmissionMongoEntity(String id, Instant timestamp, Survey survey, List<AnsweredQuestion> answeredQuestions) {
    this.id = id;
    this.timestamp = nowIfNull(timestamp);
    this.survey = new Survey(survey);
    this.answeredQuestions = ofNullableList(answeredQuestions);
  }

  public Submission toDomainObject() {
    return new Submission(id, timestamp, survey, answeredQuestions);
  }

  public static SubmissionMongoEntity fromDomainObject(Submission submission) {
    return new SubmissionMongoEntity(
        submission.getId(),
        submission.getTimestamp(),
        submission.getSurvey(),
        submission.getAnsweredQuestions()
    );
  }

  public String getId() {
    return id;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public Survey getSurvey() {
    return survey;
  }

  public List<AnsweredQuestion> getAnsweredQuestions() {
    return answeredQuestions;
  }

}
