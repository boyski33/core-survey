package com.hippo.coresurvey.repository.survey;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SurveyMongoStore extends MongoRepository<SurveyMongoEntity, String> {

  @Override
  List<SurveyMongoEntity> findAll();

  @Query(value = "{}", fields = "{questions: 0}")
  List<SurveyMongoEntity> findAllAndExcludeQuestions();

  @Override
  Optional<SurveyMongoEntity> findById(String id);

  @Query(value = "{}", fields = "{questions: 0}")
  List<SurveyMongoEntity> findByOwnerEmail(String email);

  @Override
  <S extends SurveyMongoEntity> S insert(S entity);
}
