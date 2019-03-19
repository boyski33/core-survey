package com.hippo.coresurvey.repository.submission;

import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class SubmissionMongoRepository implements SubmissionRepository {

  private final SubmissionMongoStore submissionStore;

  @Autowired
  public SubmissionMongoRepository(SubmissionMongoStore submissionStore) {
    this.submissionStore = submissionStore;
  }

  @Override
  public List<Submission> getAllSubmissions() {
    return null;
  }

  @Override
  public Optional<Submission> getSubmissionById(String id) {
    return submissionStore.findById(id);
  }

  @Override
  public List<Submission> getSubmissionsForSurvey(String surveyId) {
    return Collections.emptyList();
  }
}
