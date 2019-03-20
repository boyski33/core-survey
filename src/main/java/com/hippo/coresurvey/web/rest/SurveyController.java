package com.hippo.coresurvey.web.rest;

import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

  private final SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @GetMapping()
  public ResponseEntity<List<Survey>> getAllSurveys(
      @RequestParam(value = "meta", required = false) boolean displayOnlyMeta) {

    List<Survey> surveys = surveyService.getAllSurveys(displayOnlyMeta);

    return ResponseEntity.ok(surveys);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getSurveyById(@PathVariable("id") String id) {

    Optional<Survey> survey = surveyService.getSurveyById(id);

    if (survey.isPresent()) {
      return ResponseEntity.ok(survey.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping()
  public ResponseEntity<Survey> postSurvey(@RequestBody Survey survey) {
    Survey persistedSurvey = surveyService.addSurvey(survey);

    return ResponseEntity.ok(persistedSurvey);
  }
}
