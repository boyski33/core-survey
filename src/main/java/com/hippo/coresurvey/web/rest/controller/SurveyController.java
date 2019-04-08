package com.hippo.coresurvey.web.rest.controller;

import com.hippo.coresurvey.domain.stats.SurveyReport;
import com.hippo.coresurvey.domain.survey.Survey;
import com.hippo.coresurvey.domain.survey.SurveyService;
import com.hippo.coresurvey.web.rest.resource.SurveyRestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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
            .collect(toList());

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

  /**
   * Get survey metadata (without question list) for the given email.
   *
   * @param ownerEmail the email of the surveys owner
   * @return list of Surveys
   */
  @GetMapping("/user/{email}")
  public ResponseEntity<List<SurveyRestResource>> getSurveysForOwner(@PathVariable("email") String ownerEmail) {

    List<SurveyRestResource> surveys =
        surveyService.getSurveysForOwner(ownerEmail).stream()
            .map(SurveyRestResource::fromDomainObject)
            .collect(toList());

    return ResponseEntity.ok(surveys);
  }

  /**
   * Get the surveys not yet taken by the logged in user.
   *
   * @param userEmail the email of the currently logged in user
   * @return list of Surveys
   */
  @GetMapping("/for-user/{email}")
  public ResponseEntity<List<SurveyRestResource>> getSurveysForUser(@PathVariable("email") String userEmail) {

    List<SurveyRestResource> surveys =
        surveyService.getSurveysForUser(userEmail).stream()
            .map(SurveyRestResource::fromDomainObject)
            .collect(toList());

    return ResponseEntity.ok(surveys);
  }

  @PostMapping()
  public ResponseEntity<SurveyRestResource> postSurvey(@RequestBody @Valid SurveyRestResource survey) {

    Survey persistedSurvey = surveyService.addSurvey(survey.toDomainObject());
    SurveyRestResource response = SurveyRestResource.fromDomainObject(persistedSurvey);

    return ResponseEntity.ok(response);
  }

  /**
   * Get a list of all survey submissions and statistics of the given answers.
   *
   * @param surveyId the ID of the survey you need a report for
   * @return a list of submissions and a map of answer count
   */
  @GetMapping("report/{surveyId}")
  public ResponseEntity<?> getReportForSurvey(@PathVariable("surveyId") String surveyId) {

    SurveyReport surveyReport = surveyService.getReportForSurvey(surveyId);

    return ResponseEntity.ok(surveyReport);
  }
}
