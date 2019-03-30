package com.hippo.coresurvey.web.rest.controller;

import com.hippo.coresurvey.domain.user.User;
import com.hippo.coresurvey.domain.user.UserService;
import com.hippo.coresurvey.web.rest.resource.UserRestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{email}")
  public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {

    Optional<User> user = userService.getUserByEmail(email);

    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    }

    return ResponseEntity.ok(new User());
  }

  @PutMapping()
  public ResponseEntity<?> updateUser(@RequestBody @Valid UserRestResource user) {

    User persistedUser = userService.updateUser(user.toDomainObject());
    UserRestResource response = UserRestResource.fromDomainObject(persistedUser);

    return ResponseEntity.ok(response);
  }
}
