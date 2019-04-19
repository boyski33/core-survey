package com.hippo.coresurvey.domain.analytics;

import java.util.List;

public class SurveyAnalyticsData {

  private String surveyId;
  private List<AnalyticsNormalizedSubmission> submissions;

  public SurveyAnalyticsData() {
  }

  public SurveyAnalyticsData(String surveyId, List<AnalyticsNormalizedSubmission> submissions) {
    this.surveyId = surveyId;
    this.submissions = submissions;
  }

  public String getSurveyId() {
    return surveyId;
  }

  public List<AnalyticsNormalizedSubmission> getSubmissions() {
    return submissions;
  }
}
