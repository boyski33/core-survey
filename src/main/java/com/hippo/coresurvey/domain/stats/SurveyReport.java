package com.hippo.coresurvey.domain.stats;

import com.hippo.coresurvey.domain.submission.Submission;

import java.util.List;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;

public class SurveyReport {
  private List<Submission> submissions;
  private List<QuestionStats> surveyStats;

  public SurveyReport() {
  }

  public SurveyReport(List<Submission> submissions, List<QuestionStats> surveyStats) {
    this.submissions = ofNullableList(submissions);
    this.surveyStats = ofNullableList(surveyStats);
  }

  public List<Submission> getSubmissions() {
    return submissions;
  }

  public List<QuestionStats> getSurveyStats() {
    return surveyStats;
  }
}
