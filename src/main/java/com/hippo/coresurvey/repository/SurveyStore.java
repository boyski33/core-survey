package com.hippo.coresurvey.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyStore extends MongoRepository<SurveyMongoEntity, String> {

  @Override
  List<SurveyMongoEntity> findAll();

  @Override
  <S extends SurveyMongoEntity> S insert(S entity);
}
