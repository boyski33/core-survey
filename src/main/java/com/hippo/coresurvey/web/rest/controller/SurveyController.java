package com.hippo.coresurvey.web.rest.controller;

import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.survey.SurveyService;
import com.hippo.coresurvey.web.rest.resource.SurveyRestResource;
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
  public ResponseEntity<List<SurveyRestResource>> getAllSurveys(
      @RequestParam(value = "meta", required = false) boolean displayOnlyMeta) {

    List<SurveyRestResource> surveys =
        surveyService.getAllSurveys(displayOnlyMeta).stream()
        .map(SurveyRestResource::fromDomainObject)
        .collect(Collectors.toList());

    return ResponseEntity.ok(surveys);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SurveyRestResource> getSurveyById(@PathVariable("id") String id) {

    Optional<Survey> survey = surveyService.getSurveyById(id);

    if (survey.isPresent()) {
      SurveyRestResource response = SurveyRestResource.fromDomainObject(survey.get());

      return ResponseEntity.ok(response);
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping()
  public ResponseEntity<SurveyRestResource> postSurvey(@RequestBody @Valid SurveyRestResource survey) {

    Survey persistedSurvey = surveyService.addSurvey(survey.toDomainObject());
    SurveyRestResource response = SurveyRestResource.fromDomainObject(persistedSurvey);

    return ResponseEntity.ok(response);
  }
}
