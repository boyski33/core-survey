package com.hippo.coresurvey.domain;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository {

  List<Survey> getAllSurveys();

  Optional<Survey> getSurveyById(String id);

  Survey addSurvey(Survey survey);
}
