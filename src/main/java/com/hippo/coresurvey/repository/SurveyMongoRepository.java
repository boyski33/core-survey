package com.hippo.coresurvey.repository;

import com.hippo.coresurvey.domain.Survey;
import com.hippo.coresurvey.domain.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SurveyMongoRepository implements SurveyRepository {

  @Autowired
  SurveyStore repo;

  @Override
  public List<Survey> getAllSurveys() {
    return repo.findAll().stream()
        .map(SurveyMongoEntity::toDomainObject)
        .collect(Collectors.toList());
  }

  @Override
  public Survey addSurvey(Survey survey) {
    return repo.insert(SurveyMongoEntity.fromDomainObject(survey)).toDomainObject();
  }
}
