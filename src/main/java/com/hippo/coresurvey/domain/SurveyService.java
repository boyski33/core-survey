package com.hippo.coresurvey.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

  @Autowired
  SurveyRepository surveyRepository;

  public List<Survey> getAllSurveys() {
    return surveyRepository.getAllSurveys();
  }

  public Survey createSurvey(Survey survey) {
    return surveyRepository.addSurvey(survey);
  }

}
