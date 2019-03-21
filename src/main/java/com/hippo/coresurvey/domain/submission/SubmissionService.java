package com.hippo.coresurvey.domain.submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

  private final SubmissionRepository submissionRepository;

  @Autowired
  public SubmissionService(SubmissionRepository submissionRepository) {
    this.submissionRepository = submissionRepository;
  }

  public List<Submission> getAllSubmissions() {
    return submissionRepository.getAllSubmissions();
  }

  public Optional<Submission> getSubmissionById(String id) {
    return submissionRepository.getSubmissionById(id);
  }

  public List<Submission> getSubmissionsForSurvey(String surveyId) {
    return submissionRepository.getSubmissionsForSurvey(surveyId);
  }

  public Submission addSubmission(Submission submission) {
    return submissionRepository.addSubmission(submission);
  }
}
