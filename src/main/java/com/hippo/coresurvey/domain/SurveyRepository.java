package com.hippo.coresurvey.domain;

import java.util.List;

public interface SurveyRepository {

  List<Survey> getAllSurveys();

  Survey addSurvey(Survey survey);
}
