package com.hippo.coresurvey.domain.submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository {

  List<Submission> getAllSubmissions();

  Optional<Submission> getSubmissionById(String id);

  List<Submission> getSubmissionsForSurvey(String surveyId);

  Long getCountOfSubmissionsByExistingUsers(String surveyId);

  List<Submission> getSubmissionsOfUser(String userEmail);

  List<Submission> getSubmissionsForSurveyAndUser(String surveyId, String userEmail);

  Submission addSubmission(Submission submission);
}
