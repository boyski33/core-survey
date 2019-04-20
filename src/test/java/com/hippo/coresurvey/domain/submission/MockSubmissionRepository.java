package com.hippo.coresurvey.domain.submission;

import java.util.List;
import java.util.Optional;

public class MockSubmissionRepository implements SubmissionRepository {
  @Override
  public List<Submission> getAllSubmissions() {
    return null;
  }

  @Override
  public Long getCountOfSubmissionsByExistingUsers(String surveyId) {
    return 1L;
  }

  @Override
  public Optional<Submission> getSubmissionById(String id) {
    return Optional.empty();
  }

  @Override
  public List<Submission> getSubmissionsForSurvey(String surveyId) {
    return null;
  }

  @Override
  public List<Submission> getSubmissionsOfUser(String userEmail) {
    return null;
  }

  @Override
  public List<Submission> getSubmissionsForSurveyAndUser(String surveyId, String userEmail) {
    return null;
  }

  @Override
  public Submission addSubmission(Submission submission) {
    return null;
  }
}
