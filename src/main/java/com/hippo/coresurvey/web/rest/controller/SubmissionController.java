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

  @GetMapping("/user/{userEmail}")
  public ResponseEntity<List<SubmissionRestResource>> getSubmissionsOfUser(@PathVariable("userEmail") String userEmail) {

    List<SubmissionRestResource> submissions =
        submissionService.getSubmissionsOfUser(userEmail).stream()
            .map(SubmissionRestResource::fromDomainObject)
            .collect(Collectors.toList());

    return ResponseEntity.ok(submissions);
  }

  /**
   * Post a new submission for a survey. Submissions can either have a null user, or a user with
   * an email property. A request will be made to find if such a user exists in the database.
   * If not, the submission with be persisted with a null User field.
   *
   * @param submission the resource with a <pre>user: {email: string}</pre> or null
   * @return 200 OK with the submission or 400 Bad Request if submission for survey already posted
   */
  @PostMapping()
  public ResponseEntity<?> postSubmission(@RequestBody @Valid SubmissionRestResource submission) {

    if (submissionService.userAlreadyPostedSubmission(submission.toDomainObject())) {
      return ResponseEntity.badRequest().body("You cannot post another submission.");
    }

    Submission persistedSubmission = submissionService.addSubmission(submission.toDomainObject());
    SubmissionRestResource response = SubmissionRestResource.fromDomainObject(persistedSubmission);

    return ResponseEntity.ok(response);
  }
}
