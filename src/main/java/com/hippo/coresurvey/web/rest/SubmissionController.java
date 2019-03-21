package com.hippo.coresurvey.web.rest;

import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.submission.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

  private final SubmissionService submissionService;

  @Autowired
  public SubmissionController(SubmissionService submissionService) {
    this.submissionService = submissionService;
  }

  @GetMapping()
  public ResponseEntity<?> getAllSubmissions() {
    List<Submission> submissions = submissionService.getAllSubmissions();

    return ResponseEntity.ok(submissions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getSubmissionById(@PathVariable("id") String id) {
    Optional<Submission> submission = submissionService.getSubmissionById(id);

    if (submission.isPresent()) {
      return ResponseEntity.ok(submission.get());
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/survey/{surveyId}")
  public ResponseEntity<?> getSubmissionsForSurvey(@PathVariable("surveyId") String surveyId) {
    List<Submission> submissions = submissionService.getSubmissionsForSurvey(surveyId);

    return ResponseEntity.ok(submissions);
  }

  @PostMapping()
  public ResponseEntity<?> postSubmission(@RequestBody Submission submission) {
    Submission persistedSubmission = submissionService.addSubmission(submission);

    return ResponseEntity.ok(persistedSubmission);
  }
}
