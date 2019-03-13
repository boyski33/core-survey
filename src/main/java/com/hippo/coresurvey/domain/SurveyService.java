package com.hippo.coresurvey.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

  private final SurveyRepository surveyRepository;

  @Autowired
  public SurveyService(SurveyRepository surveyRepository) {
    this.surveyRepository = surveyRepository;
  }

  public List<Survey> getAllSurveys() {
    return surveyRepository.getAllSurveys();
  }

  public Survey createSurvey(Survey survey) {
    return surveyRepository.addSurvey(survey);
  }

}
