package com.hippo.coresurvey.web.rest;

import com.hippo.coresurvey.domain.submission.SubmissionService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    return ResponseEntity.ok(null);
  }
}
