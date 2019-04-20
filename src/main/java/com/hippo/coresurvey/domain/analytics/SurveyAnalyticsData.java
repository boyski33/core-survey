package com.hippo.coresurvey.domain.analytics;

import com.hippo.coresurvey.domain.submission.Submission;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SurveyAnalyticsData {

  private String surveyId;
  private List<AnalyticsNormalizedSubmission> submissions;

  public SurveyAnalyticsData() {
  }

  public SurveyAnalyticsData(String surveyId, List<AnalyticsNormalizedSubmission> submissions) {
    this.surveyId = surveyId;
    this.submissions = submissions;
  }

  public static SurveyAnalyticsData fromSubmissionList(List<Submission> submissions) {
    if (submissions.isEmpty()) {
      return new SurveyAnalyticsData();
    }

    final String surveyId = submissions.get(0).getSurvey().getId();

    return new SurveyAnalyticsData(surveyId, normalizeSubmissions(submissions));
  }

  private static List<AnalyticsNormalizedSubmission> normalizeSubmissions(List<Submission> submissions) {
    return submissions.stream()
        .filter(sub -> sub.getUser() != null).map(sub -> new AnalyticsNormalizedSubmission(
            getAgeFromDob(sub.getUser().getDateOfBirth()),
            sub.getUser().getGender(),
            sub.getAnswers())
        ).collect(Collectors.toList());
  }

  private static int getAgeFromDob(Date dob) {
    LocalDate date = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    return Period.between(date, LocalDate.now()).getYears();
  }

  public String getSurveyId() {
    return surveyId;
  }

  public List<AnalyticsNormalizedSubmission> getSubmissions() {
    return submissions;
  }
}
