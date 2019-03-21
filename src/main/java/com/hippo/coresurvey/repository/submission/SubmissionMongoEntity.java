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
  private List<AnsweredQuestion> answers;

  public SubmissionMongoEntity() {
  }

  public SubmissionMongoEntity(String id, Instant timestamp, Survey survey, List<AnsweredQuestion> answers) {
    this.id = id;
    this.timestamp = nowIfNull(timestamp);
    this.survey = new Survey(survey);
    this.answers = ofNullableList(answers);
  }

  public Submission toDomainObject() {
    return new Submission(id, timestamp, survey, answers);
  }

  public static SubmissionMongoEntity fromDomainObject(Submission submission) {
    return new SubmissionMongoEntity(
        submission.getId(),
        submission.getTimestamp(),
        submission.getSurvey(),
        submission.getAnswers()
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

  public List<AnsweredQuestion> getAnswers() {
    return answers;
  }

}
