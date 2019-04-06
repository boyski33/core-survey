package com.hippo.coresurvey.domain.stats;

import com.hippo.coresurvey.domain.submission.Submission;

import java.util.List;
import java.util.Map;

import static com.hippo.coresurvey.domain.util.CollectionsUtil.ofNullableList;

public class SurveyReport {
  private List<Submission> submissions;
  private Map<String, Map<String, Integer>> answerStats;

  public SurveyReport() {
  }

  public SurveyReport(List<Submission> submissions, Map<String, Map<String, Integer>> answerStats) {
    this.submissions = ofNullableList(submissions);
    this.answerStats = answerStats;
  }

  public List<Submission> getSubmissions() {
    return submissions;
  }

  public Map<String, Map<String, Integer>> getAnswerStats() {
    return answerStats;
  }
}
