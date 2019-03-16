package com.hippo.coresurvey.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

class MockSurveyRepository implements SurveyRepository {

  @Override
  public List<Survey> getAllSurveys() {
    return new ArrayList<>(asList(
        new Survey("id", "title", "description", asList(new Question())),
        new Survey("id2", "title2", "description2", asList(new Question()))
    ));
  }

  @Override
  public List<Survey> getAllSurveysMetadata() {
    return new ArrayList<>(asList(
        new Survey("id", "title", "description", Collections.emptyList()),
        new Survey("id2", "title2", "description2", Collections.emptyList())
    ));
  }

  @Override
  public Optional<Survey> getSurveyById(String id) {
    return Optional.empty();
  }

  @Override
  public Survey addSurvey(Survey survey) {
    return null;
  }
}

