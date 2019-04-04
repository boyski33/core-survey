package com.hippo.coresurvey.domain.question;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class QuestionControlTypeTest {

  @Test
  public void given_dropdown_enum_type_when_toValue_then_convert_to_string() {
    // GIVEN
    QuestionControlType questionType = QuestionControlType.DROPDOWN;

    // WHEN
    String actual = questionType.toValue();

    // THEN
    assertEquals("dropdown", actual);
  }

  @Test
  public void given_dropdown_as_string_when_forValue_then_convert_to_enum_type() {
    // GIVEN
    String dropdown = "dropdown";

    // WHEN
    QuestionControlType actual = QuestionControlType.forValue(dropdown);

    // THEN
    assertEquals(QuestionControlType.DROPDOWN, actual);
  }

  @Test
  public void given_random_string_when_forValue_then_return_null() {
    // GIVEN
    String random = "random";

    // WHEN
    QuestionControlType actual = QuestionControlType.forValue(random);

    // THEN
    assertNull(actual);
  }

  @Test
  public void given_textbox_as_upper_case_when_forValue_then_convert_to_enum_type() {
    // GIVEN
    String textbox = "tExtBOx";

    // WHEN
    QuestionControlType actual = QuestionControlType.forValue(textbox);

    // THEN
    assertEquals(QuestionControlType.TEXTBOX, actual);
  }

}