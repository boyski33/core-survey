package com.hippo.coresurvey.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

// TODO: we can argue that this exposes rest logic and should be extracted in the rest layer
public enum QuestionControlType {
  TEXTBOX,
  DROPDOWN,
  RADIO,
  CHECKBOX;

  private static Map<String, QuestionControlType> namesMap = new HashMap<>();

  static {
    namesMap.put("textbox", TEXTBOX);
    namesMap.put("dropdown", DROPDOWN);
    namesMap.put("radio", RADIO);
    namesMap.put("checkbox", CHECKBOX);
  }

  @JsonCreator
  public static QuestionControlType forValue(String name) {
    return namesMap.get(name.toLowerCase());
  }

  @JsonValue
  public String toValue() {
    for (Map.Entry<String, QuestionControlType> entry : namesMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }

    return null;
  }
}
