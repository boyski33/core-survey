package com.hippo.coresurvey.web.rest.controller;

import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.submission.SubmissionService;
import com.hippo.coresurvey.web.rest.resource.SubmissionRestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

  private final SubmissionService submissionService;

  @Autowired
  public SubmissionController(SubmissionService submissionService) {
    this.submissionService = submissionService;
  }

  @GetMapping()
  public ResponseEntity<List<SubmissionRestResource>> getAllSubmissions() {

    List<SubmissionRestResource> submissions =
        submissionService.getAllSubmissions().stream()
            .map(SubmissionRestResource::fromDomainObject)
            .collect(Collectors.toList());

    return ResponseEntity.ok(submissions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubmissionRestResource> getSubmissionById(@PathVariable("id") String id) {

    Optional<Submission> submission = submissionService.getSubmissionById(id);

    if (submission.isPresent()) {
      SubmissionRestResource response = SubmissionRestResource.fromDomainObject(submission.get());

      return ResponseEntity.ok(response);
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/survey/{surveyId}")
  public ResponseEntity<List<SubmissionRestResource>> getSubmissionsForSurvey(@PathVariable("surveyId") String surveyId) {

    List<SubmissionRestResource> submissions =
        submissionService.getSubmissionsForSurvey(surveyId).stream()
            .map(SubmissionRestResource::fromDomainObject)
            .collect(Collectors.toList());

    return ResponseEntity.ok(submissions);
  }

  @PostMapping()
  public ResponseEntity<SubmissionRestResource> postSubmission(@RequestBody @Valid SubmissionRestResource submission) {

    Submission persistedSubmission = submissionService.addSubmission(submission.toDomainObject());
    SubmissionRestResource response = SubmissionRestResource.fromDomainObject(persistedSubmission);

    return ResponseEntity.ok(response);
  }
}
