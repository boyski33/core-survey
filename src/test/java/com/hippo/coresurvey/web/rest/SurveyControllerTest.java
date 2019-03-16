package com.hippo.coresurvey.web.rest;

import com.hippo.coresurvey.domain.Survey;
import com.hippo.coresurvey.domain.SurveyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SurveyControllerTest {
  @Mock
  private SurveyService service;

  @InjectMocks
  private SurveyController controller;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);
  }

  @Test public void
  given_invalid_id_when_calling_survey_endpoint_then_return_404() {
    // GIVEN
    when(service.getSurveyById(any())).thenReturn(Optional.empty());

    // WHEN
    ResponseEntity res = controller.getSurveyById("1");

    // THEN
    assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
  }

  @Test public void
  given_valid_id_when_calling_survey_endpoint_then_return_200() {
    // GIVEN
    when(service.getSurveyById(any())).thenReturn(Optional.of(new Survey()));

    // WHEN
    ResponseEntity res = controller.getSurveyById("1");

    // THEN
    assertEquals(HttpStatus.OK, res.getStatusCode());
  }
}