package com.hippo.coresurvey.domain.util;

import java.time.Instant;

public final class DateTimeUtil {

  /**
   * Returns a copy of the timestamp or the present time if null.
   *
   * @param instant the passed in timestamp object
   * @return a copy of the timestamp or now if null
   */
  public static Instant nowIfNull(Instant instant) {
    if (instant == null) {
      return Instant.now();
    }

    return Instant.from(instant);
  }
}
