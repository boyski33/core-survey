package com.hippo.coresurvey.repository.user;

import com.hippo.coresurvey.domain.user.User;
import com.hippo.coresurvey.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
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
  public User updateUser(User user) {
    Optional<UserMongoEntity> existingUser = this.userStore.findByEmail(user.getEmail());

    if (existingUser.isPresent()) {
      UserMongoEntity e = existingUser.get();
      user.setId(e.getId());
    }

    UserMongoEntity entity = UserMongoEntity.fromDomainObject(user);

    return userStore.save(entity).toDomainObject();
  }
}
