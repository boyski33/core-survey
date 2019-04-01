package com.hippo.coresurvey.domain.submission;

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

  private User getUserData(String email) {
    Optional<User> user = userService.getUserByEmail(email);

    return user.orElse(null);
  }
}
