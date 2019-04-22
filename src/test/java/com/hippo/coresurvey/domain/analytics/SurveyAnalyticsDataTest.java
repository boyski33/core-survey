package com.hippo.coresurvey.domain.analytics;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.question.QuestionControlType;
import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.user.Gender;
import com.hippo.coresurvey.domain.user.User;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SurveyAnalyticsDataTest {

  private Date dob = new Date(0); // Jan 1, 1970

  @Test
  public void givenSubmissionListReturnNormalizedAnalyticsDataObject() {
    List<Submission> submissions = getSubmissionStubs();
    SurveyAnalyticsData analyticsData = SurveyAnalyticsData.fromSubmissionList(submissions);

    AnalyticsNormalizedSubmission analyticsSubmission = analyticsData.getSubmissions().get(0);

    assertEquals("id1", analyticsData.getSurveyId());
    assertNotEquals("id2", analyticsData.getSurveyId());
    assertEquals(Gender.MALE, analyticsSubmission.getUserGender());
    assertEquals(49, analyticsSubmission.getUserAge());
  }

  @Test
  public void givenSubmissionWithNullUserShouldNotBeIncludedForAnalysis() {
    List<Submission> submissions = getSubmissionStubs();
    submissions.add(new Submission("id", Instant.MIN, null, null, getAnswerStubs()));
    SurveyAnalyticsData analyticsData = SurveyAnalyticsData.fromSubmissionList(submissions);

    assertEquals(2, analyticsData.getSubmissions().size());
  }

  private List<Submission> getSubmissionStubs() {
    final User user =
        new User("id1", "mail@mail.bg", "John", "Smith", dob, Gender.MALE, false);
    final User user2 =
        new User("id2", "mail@mail.bg", "Maria", "Smith", dob, Gender.FEMALE, false);

    List<Submission> submissions = new ArrayList<>();
    Survey survey = new Survey("id1", "", "", "", Instant.MIN, Collections.emptyList());

    submissions.add(new Submission("id", Instant.MIN, user, survey, getAnswerStubs()));
    submissions.add(new Submission("id2", Instant.MIN, user2, survey, getAnswerStubs()));

    return submissions;
  }

  private List<AnsweredQuestion> getAnswerStubs() {
    List<AnsweredQuestion> answers = new ArrayList<>();
    Question q = new Question("key1", "label1", 1, QuestionControlType.RADIO, Collections.emptyList());

    answers.add(new AnsweredQuestion(q, "answer1"));

    return answers;
  }


}