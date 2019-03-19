package com.hippo.coresurvey.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

  @GetMapping()
  public ResponseEntity<?> getAllSubmissions() {

    return ResponseEntity.ok(null);
  }
}
