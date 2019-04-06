package com.hippo.coresurvey.domain.survey;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.stats.SurveyReport;
import com.hippo.coresurvey.domain.submission.Submission;
import com.hippo.coresurvey.domain.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveyService {

  private final SurveyRepository surveyRepository;
  private final SubmissionRepository submissionRepository;

  @Autowired
  public SurveyService(SurveyRepository surveyRepository, SubmissionRepository submissionRepository) {
    this.surveyRepository = surveyRepository;
    this.submissionRepository = submissionRepository;
  }

  public List<Survey> getAllSurveys(boolean displayOnlyMeta) {
    if (displayOnlyMeta) {
      return surveyRepository.getAllSurveysMetadata();
    }

    return surveyRepository.getAllSurveys();
  }

  public Optional<Survey> getSurveyById(String id) {
    return surveyRepository.getSurveyById(id);
  }

  public List<Survey> getSurveysForOwner(String ownerEmail) {
    return surveyRepository.getSurveysForOwner(ownerEmail);
  }

  public List<Survey> getSurveysForUser(String userEmail) {
    final List<Survey> allSurveysMeta = this.getAllSurveys(true);
    final List<String> surveysTakenByUser = submissionRepository.getSubmissionsOfUser(userEmail).stream()
        .map(submission -> submission.getSurvey().getId())
        .collect(Collectors.toList());

    return allSurveysMeta.stream()
        .filter(survey -> !surveysTakenByUser.contains(survey.getId()))
        .collect(Collectors.toList());
  }

  public Survey addSurvey(Survey survey) {
    return surveyRepository.addSurvey(survey);
  }

  public SurveyReport getReportForSurvey(String surveyId) {
    List<Submission> submissions = submissionRepository.getSubmissionsForSurvey(surveyId);
    Map<String, Map<String, Integer>> answerStats = submissions.isEmpty() ?
        Collections.emptyMap() : extractAnswerStatsFromSubmissions(submissions);

    return new SurveyReport(submissions, answerStats);
  }

  private Map<String, Map<String, Integer>> extractAnswerStatsFromSubmissions(List<Submission> submissions) {
    // TODO: write a few unit tests

    Map<String, Map<String, Integer>> stats = new HashMap<>();
    List<AnsweredQuestion> answeredQuestions = submissions.stream()
        .map(Submission::getAnswers)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    submissions.get(0).getSurvey().getQuestions().forEach(q -> {
      stats.put(q.getKey(), new HashMap<>());
    });

    answeredQuestions.forEach(ans -> {
      Map<String, Integer> answerMap = stats.get(ans.getQuestion().getKey());
      Integer answerCount = answerMap.getOrDefault(ans.getAnswer(), 0);

      answerMap.put(ans.getAnswer(), answerCount + 1);
    });

    return stats;
  }
}
