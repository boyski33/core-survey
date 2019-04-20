package com.hippo.coresurvey.domain.analytics;

import com.hippo.coresurvey.domain.submission.Submission;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
}
