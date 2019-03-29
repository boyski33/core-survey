package com.hippo.coresurvey.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.getUserByEmail(email);
  }

  public User addUser(User user) {
    return userRepository.addUser(user);
  }
}
