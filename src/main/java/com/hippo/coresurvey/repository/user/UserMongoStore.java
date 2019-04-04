package com.hippo.coresurvey.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoStore extends MongoRepository<UserMongoEntity, String> {

  @Override
  <U extends UserMongoEntity> U save(U user);

  Optional<UserMongoEntity> findByEmail(String email);
}
