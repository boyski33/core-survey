package com.hippo.coresurvey.domain.submission;

import com.hippo.coresurvey.domain.analytics.AnalyticsService;
import com.hippo.coresurvey.domain.analytics.SurveyAnalyticsData;
import com.hippo.coresurvey.domain.user.User;
import com.hippo.coresurvey.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.yml")
public class SubmissionService {

  @Value("${analytics.train-threshold}")
  private int trainThreshold;

  @Value("${analytics.retrain-factor}")
  private int retrainFactor;

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
    List<Submission> allSubmissions = submissionRepository.getSubmissionsForSurvey(surveyId);

    List<Submission> userSubmissions = getSubmissionsWithUser(allSubmissions);
    userSubmissions.addAll(analyzeAnonymousSubmissions(allSubmissions));

    return userSubmissions;
  }

  private List<Submission> getSubmissionsWithUser(List<Submission> submissions) {
    return submissions.stream()
        .filter(sub -> sub.getUser() != null && !sub.getUser().getIsPredicted())
        .collect(Collectors.toList());
  }

  private List<Submission> analyzeAnonymousSubmissions(List<Submission> submissions) {
    List<Submission> anonymousSubmissions = submissions.stream()
        .filter(sub -> sub.getUser() == null || sub.getUser().getIsPredicted())
        .collect(Collectors.toList());

    return analyticsService.predictUserDetailsForSubmissions(anonymousSubmissions);
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

    Submission persisted = submissionRepository.addSubmission(submission);
    analyzeSubmissions(persisted.getSurvey().getId());

    return persisted;
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

  // todo make async
  private void analyzeSubmissions(String surveyId) {
    long count = submissionRepository.getCountOfSubmissionsByExistingUsers(surveyId);

    if (isTimeToTrain(count)) {
      List<Submission> submissions = submissionRepository.getSubmissionsForSurvey(surveyId);
      analyticsService.trainOnAnalyticsData(SurveyAnalyticsData.fromSubmissionList(submissions));
    }
  }

  private boolean isTimeToTrain(long count) {
    return count >= trainThreshold && (count - trainThreshold) % retrainFactor == 0;
  }

  private User getUserData(String email) {
    Optional<User> user = userService.getUserByEmail(email);

    return user.orElse(null);
  }
}
