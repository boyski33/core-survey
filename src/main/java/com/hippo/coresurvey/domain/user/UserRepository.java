package com.hippo.coresurvey.domain.user;

import java.util.Optional;

public interface UserRepository {

  Optional<User> getUserByEmail(String email);

  User addUser(User user);
}
