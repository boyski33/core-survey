package com.hippo.coresurvey.domain.submission;

import java.util.List;

public interface SubmissionRepository {

  List<Submission> getAllSubmissions();

  Submission getSubmissionById(String id);

  List<Submission> getSubmissionsForSurveyId(String surveyId);
}
