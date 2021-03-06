package com.hippo.coresurvey.domain.analytics;

import com.hippo.coresurvey.domain.submission.Submission;

import java.util.List;

public interface AnalyticsService {
  void trainOnAnalyticsData(SurveyAnalyticsData analyticsData);
  List<Submission> predictUserDetailsForSubmissions(List<Submission> submissions);
}
