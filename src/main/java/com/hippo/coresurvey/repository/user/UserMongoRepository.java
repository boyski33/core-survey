package com.hippo.coresurvey.repository.user;

import com.hippo.coresurvey.domain.user.User;
import com.hippo.coresurvey.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserMongoRepository implements UserRepository {

  private final UserMongoStore userStore;

  @Autowired
  public UserMongoRepository(UserMongoStore userStore) {
    this.userStore = userStore;
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return userStore.findByEmail(email)
        .map(UserMongoEntity::toDomainObject);
  }

  @Override
  public User addUser(User user) {
    UserMongoEntity entity = UserMongoEntity.fromDomainObject(user);

    return userStore.insert(entity).toDomainObject();
  }
}
