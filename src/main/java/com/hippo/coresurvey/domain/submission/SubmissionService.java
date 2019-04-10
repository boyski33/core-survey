package com.hippo.coresurvey.domain.submission;

import com.hippo.coresurvey.domain.analytics.AnalyticsService;
import com.hippo.coresurvey.domain.user.User;
import com.hippo.coresurvey.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

  private final SubmissionRepository submissionRepository;
  private final UserService userService;
  private final AnalyticsService analyticsService;

  @Autowired
  public SubmissionService(SubmissionRepository submissionRepository,
                           UserService userService,
                           AnalyticsService analyticsService) {
    this.submissionRepository = submissionRepository;
    this.userService = userService;
    this.analyticsService = analyticsService;
  }

  public List<Submission> getAllSubmissions() {
    return submissionRepository.getAllSubmissions();
  }

  public Optional<Submission> getSubmissionById(String id) {
    return submissionRepository.getSubmissionById(id);
  }

  public List<Submission> getSubmissionsForSurvey(String surveyId) {
    this.analyticsService.sendSubmissionBatch(Collections.emptyList());
    return submissionRepository.getSubmissionsForSurvey(surveyId);
  }

  public List<Submission> getSubmissionsOfUser(String userEmail) {
    return submissionRepository.getSubmissionsOfUser(userEmail);
  }

  public Submission addSubmission(Submission submission) {
    if (submission.getUser() == null) {
      submission.setUser(null);
    } else {
      String email = submission.getUser().getEmail();
      submission.setUser(getUserData(email));
    }

    return submissionRepository.addSubmission(submission);
  }

  public boolean userAlreadyPostedSubmission(Submission submission) {
    if (submission.getUser() == null) {
      return false;
    }

    List<Submission> userSubmissions =
        submissionRepository.getSubmissionsForSurveyAndUser(
            submission.getSurvey().getId(),
            submission.getUser().getEmail());

    return !userSubmissions.isEmpty();
  }

  private void sendSubmissionForAnalytics() {

  }

  private User getUserData(String email) {
    Optional<User> user = userService.getUserByEmail(email);

    return user.orElse(null);
  }
}
