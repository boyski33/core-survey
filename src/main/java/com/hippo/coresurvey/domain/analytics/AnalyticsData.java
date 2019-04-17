package com.hippo.coresurvey.domain.analytics;

import java.util.List;

public class AnalyticsData {

  private String surveyId;
  private List<AnalyticsSubmission> submissions;

  public AnalyticsData() {
  }

  public AnalyticsData(String surveyId, List<AnalyticsSubmission> submissions) {
    this.surveyId = surveyId;
    this.submissions = submissions;
  }

  public String getSurveyId() {
    return surveyId;
  }

  public List<AnalyticsSubmission> getSubmissions() {
    return submissions;
  }
}