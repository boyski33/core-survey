package com.hippo.coresurvey.web.rest.controller;

import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.survey.SurveyService;
import com.hippo.coresurvey.web.rest.resource.SurveyResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

  private final SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @GetMapping()
  public ResponseEntity<List<SurveyResource>> getAllSurveys(
      @RequestParam(value = "meta", required = false) boolean displayOnlyMeta) {

    List<SurveyResource> surveys =
        surveyService.getAllSurveys(displayOnlyMeta).stream()
        .map(SurveyResource::fromDomainObject)
        .collect(Collectors.toList());

    return ResponseEntity.ok(surveys);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SurveyResource> getSurveyById(@PathVariable("id") String id) {

    Optional<Survey> survey = surveyService.getSurveyById(id);

    if (survey.isPresent()) {
      SurveyResource response = SurveyResource.fromDomainObject(survey.get());

      return ResponseEntity.ok(response);
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping()
  public ResponseEntity<SurveyResource> postSurvey(@Valid @RequestBody SurveyResource survey) {
    Survey persistedSurvey = surveyService.addSurvey(survey.toDomainObject());
    SurveyResource response = SurveyResource.fromDomainObject(persistedSurvey);

    return ResponseEntity.ok(response);
  }
}
