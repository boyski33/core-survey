package com.hippo.coresurvey.rest;

import com.hippo.coresurvey.domain.Survey;
import com.hippo.coresurvey.domain.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

  private final SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @GetMapping()
  public ResponseEntity<List<Survey>> getAllSurveys() {
    List<Survey> surveys = surveyService.getAllSurveys();

    return ResponseEntity.ok(surveys);
  }

  @PostMapping()
  public ResponseEntity<Survey> postSurvey(@RequestBody Survey survey) {
    Survey persistedSurvey = surveyService.addSurvey(survey);

    return ResponseEntity.ok(persistedSurvey);
  }
}
