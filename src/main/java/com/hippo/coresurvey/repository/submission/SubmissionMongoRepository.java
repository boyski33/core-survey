package com.hippo.coresurvey.repository.submission;

import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubmissionMongoRepository implements SubmissionRepository {

  private SubmissionMongoStore submissionStore;

  @Autowired
  public SubmissionMongoRepository(SubmissionMongoStore submissionStore) {
    this.submissionStore = submissionStore;
  }

  @Override
  public List<Submission> getAllSubmissions() {
    return null;
  }

  @Override
  public Submission getSubmissionById(String id) {
    return null;
  }

  @Override
  public List<Submission> getSubmissionsForSurveyId(String surveyId) {
    return null;
  }
}
