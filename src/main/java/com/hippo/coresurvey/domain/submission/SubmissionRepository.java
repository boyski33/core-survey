package com.hippo.coresurvey.domain.submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository {

  List<Submission> getAllSubmissions();

  Optional<Submission> getSubmissionById(String id);

  List<Submission> getSubmissionsForSurvey(String surveyId);

  Submission addSubmission(Submission submission);
}
