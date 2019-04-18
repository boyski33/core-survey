package com.hippo.coresurvey.domain.analytics;

import com.hippo.coresurvey.domain.submission.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataNormalizer {

  public DataNormalizer() {
  }

  public static SurveyAnalyticsData convertFromSubmissionList(List<Submission> submissions) {
    if (submissions.isEmpty()) {
      return new SurveyAnalyticsData();
    }

    final String surveyId = submissions.get(0).getSurvey().getId();

    return new SurveyAnalyticsData(surveyId, normalizeSubmissions(submissions));
  }

  private static List<AnalyticsSubmission> normalizeSubmissions(List<Submission> submissions) {
    List<AnalyticsSubmission> normalized = new ArrayList<>();

    for (Submission sub : submissions) {
      // todo finish this
      // todo only add submissions with user present
      normalized.add(new AnalyticsSubmission());
    }

    return normalized;
  }
}
