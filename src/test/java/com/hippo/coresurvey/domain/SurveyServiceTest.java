package com.hippo.coresurvey.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SurveyServiceTest {

  private SurveyService surveyService;

  @Before
  public void setup() {
    this.surveyService = new SurveyService(new MockSurveyRepository());
  }

  @Test
  public void given_metadata_flag_true_when_get_surveys_then_return_empty_question_list() {
    //GIVEN
    boolean requestMetadata = true;

    //WHEN
    List<Survey> actual = surveyService.getAllSurveys(requestMetadata);

    //THEN
    assertTrue(actual.get(0).getQuestions().isEmpty());
  }

  @Test
  public void given_metadata_flag_false_when_get_surveys_then_return_with_questions() {
    //GIVEN
    boolean requestMetadata = false;

    //WHEN
    List<Survey> actual = surveyService.getAllSurveys(requestMetadata);

    //THEN
    assertFalse(actual.get(0).getQuestions().isEmpty());
  }


}