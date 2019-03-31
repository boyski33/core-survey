package com.hippo.coresurvey.domain.submission;

import com.hippo.coresurvey.domain.survey.SurveyService;
import com.hippo.coresurvey.domain.user.User;
import com.hippo.coresurvey.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

  private final SubmissionRepository submissionRepository;
  private final UserService userService;

  @Autowired
  public SubmissionService(SubmissionRepository submissionRepository, UserService userService) {
    this.submissionRepository = submissionRepository;
    this.userService = userService;
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
    String email = submission.getUser().getEmail();
    submission.setUser(getUserData(email));

    return submissionRepository.addSubmission(submission);
  }

  private User getUserData(String email) {
    Optional<User> user = userService.getUserByEmail(email);

    return user.orElse(null);
  }
}
