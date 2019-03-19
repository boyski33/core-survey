package com.hippo.coresurvey.repository.submission;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.survey.Survey;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

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

  public Submission toDomainObject() {
    return new Submission(); // TODO: Builder pattern?
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
