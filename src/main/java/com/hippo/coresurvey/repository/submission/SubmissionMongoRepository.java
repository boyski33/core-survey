package com.hippo.coresurvey.repository.submission;

import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SubmissionMongoRepository implements SubmissionRepository {

  private final SubmissionMongoStore submissionStore;

  @Autowired
  public SubmissionMongoRepository(SubmissionMongoStore submissionStore) {
    this.submissionStore = submissionStore;
  }

  @Override
  public List<Submission> getAllSubmissions() {
    return submissionStore.findAll().stream()
        .map(SubmissionMongoEntity::toDomainObject)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Submission> getSubmissionById(String id) {
    return submissionStore.findById(id)
        .map(SubmissionMongoEntity::toDomainObject);
  }

  @Override
  public List<Submission> getSubmissionsForSurvey(String surveyId) {
    return submissionStore.findBySurveyId(surveyId).stream()
        .map(SubmissionMongoEntity::toDomainObject)
        .collect(Collectors.toList());
  }

  @Override
  public Submission addSubmission(Submission submission) {
    SubmissionMongoEntity entity = SubmissionMongoEntity.fromDomainObject(submission);

    return submissionStore.insert(entity).toDomainObject();
  }
}
