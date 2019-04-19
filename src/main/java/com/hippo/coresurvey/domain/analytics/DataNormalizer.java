package com.hippo.coresurvey.domain.analytics;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.user.Gender;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        .filter(sub -> sub.getUser() != null).map(sub -> {
          int age = getAgeFromDob(sub.getUser().getDateOfBirth());
          Gender gender = sub.getUser().getGender();
          List<AnsweredQuestion> answers = sub.getAnswers();

          return new AnalyticsNormalizedSubmission(age, gender, answers);
        })
        .collect(Collectors.toList());
  }

  private static int getAgeFromDob(Date dob) {
    // fixme
    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    int d1 = Integer.parseInt(formatter.format(dob));
    int d2 = Integer.parseInt(formatter.format(new Date()));
    return (d2 - d1) / 10000;
  }
}
