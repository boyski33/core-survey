package com.hippo.coresurvey.domain.survey;

import com.hippo.coresurvey.domain.question.AnsweredQuestion;
import com.hippo.coresurvey.domain.question.Question;
import com.hippo.coresurvey.domain.stats.QuestionStats;
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
    List<QuestionStats> surveyStats = submissions.isEmpty() ?
        Collections.emptyList() : extractSurveyStatsFromSubmissions(submissions);

    return new SurveyReport(submissions, surveyStats);
  }

  private List<QuestionStats> extractSurveyStatsFromSubmissions(List<Submission> submissions) {

    List<Question> surveyQuestions = submissions.get(0).getSurvey().getQuestions();

    List<QuestionStats> stats = new ArrayList<>(surveyQuestions.size());

    List<AnsweredQuestion> answeredQuestions = submissions.stream()
        .map(Submission::getAnswers)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    for (Question question : surveyQuestions) {
      Map<String, Integer> answers = new HashMap<>();

      answeredQuestions.stream()
          .filter(answeredQuestion -> answeredQuestion.getQuestion().getKey().equals(question.getKey()))
          .forEach(answeredQuestion -> {
            Integer count = answers.getOrDefault(answeredQuestion.getAnswer(), 0);
            answers.put(answeredQuestion.getAnswer(), count + 1);
          });

      stats.add(new QuestionStats(question, answers));
    }

    return stats;
  }
}
