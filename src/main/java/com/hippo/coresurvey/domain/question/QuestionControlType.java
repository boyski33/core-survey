package com.hippo.coresurvey.domain.question;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum QuestionControlType {
  TEXTBOX,
  TEXTAREA,
  DROPDOWN,
  RADIO,
  YESNO,
  RANGE,
  CHECKBOX,
  DATE;

  private static Map<String, QuestionControlType> namesMap = new HashMap<>();

  static {
    namesMap.put("textbox", TEXTBOX);
    namesMap.put("textarea", TEXTAREA);
    namesMap.put("dropdown", DROPDOWN);
    namesMap.put("radio", RADIO);
    namesMap.put("yesno", YESNO);
    namesMap.put("range", RANGE);
    namesMap.put("checkbox", CHECKBOX);
    namesMap.put("date", DATE);
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
