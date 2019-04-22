package com.hippo.coresurvey.domain.survey;

import com.hippo.coresurvey.domain.submission.MockSubmissionRepository;
import com.hippo.coresurvey.domain.submission.SubmissionService;
import com.hippo.coresurvey.web.rest.controller.SurveyController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SurveyServiceTest {


  @Mock
  private SubmissionService submissionService;

  @InjectMocks
  private SurveyService surveyService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.surveyService = new SurveyService(new MockSurveyRepository(), submissionService);
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