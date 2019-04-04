package com.hippo.coresurvey.domain.survey;

import com.hippo.coresurvey.domain.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

}
