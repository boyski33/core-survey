package com.hippo.coresurvey.domain.util;

import java.util.Collections;
import java.util.List;

public final class CollectionsUtil {

  public static <T> List<T> ofNullableList(List<? extends T> list) {
    if (list == null) {
      return Collections.emptyList();
    }

    return Collections.unmodifiableList(list);
  }
}
