package com.hippo.coresurvey.domain.util;

import java.util.Collections;
import java.util.List;

public final class CollectionsUtil {

  /**
   * Returns an unmodifiable copy of the list or an empty list
   * if the passed in list is null.
   *
   * @param list the passed in list you wish to copy
   * @param <T>  the type of the list
   * @return a copy of the list or empty
   */
  public static <T> List<T> ofNullableList(List<? extends T> list) {
    if (list == null) {
      return Collections.emptyList();
    }

    return Collections.unmodifiableList(list);
  }
}
