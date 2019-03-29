package com.hippo.coresurvey.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
  MALE,
  FEMALE,
  OTHER;

  private static Map<String, Gender> genderMap = new HashMap<>();

  static {
    genderMap.put("male", MALE);
    genderMap.put("female", FEMALE);
    genderMap.put("other", OTHER);
  }

  @JsonCreator
  public static Gender forValue(String name) {
    return genderMap.get(name.toLowerCase());
  }

  @JsonValue
  public String toValue() {
    for (Map.Entry<String, Gender> entry : genderMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }

    return null;
  }
}
