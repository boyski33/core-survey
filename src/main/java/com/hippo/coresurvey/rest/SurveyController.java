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

  @Autowired
  SurveyService surveyService;

  @GetMapping()
  public ResponseEntity<List<Survey>> getAllSurveys() {
    List<Survey> surveys = surveyService.getAllSurveys();

    return ResponseEntity.ok(surveys);
  }

  @PostMapping()
  public ResponseEntity<Survey> postSurvey(@RequestBody Survey survey) {
    Survey persistedSurvey = surveyService.createSurvey(survey);

    return ResponseEntity.ok(persistedSurvey);
  }
}
