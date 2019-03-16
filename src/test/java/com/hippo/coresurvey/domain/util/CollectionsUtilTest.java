package com.hippo.coresurvey.domain.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionsUtilTest {

//  public static <T> List<T> ofNullableList(List<? extends T> list) {
//    if (list == null) {
//      return Collections.emptyList();
//    }
//
//    return Collections.unmodifiableList(list);
//  }


  @Test public void
  given_null_when_ofNullableList_then_return_empty_list() {
    // GIVEN
    List<Integer> list = null;

    // WHEN
    List<Integer> result = CollectionsUtil.ofNullableList(list);

    // THEN
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test public void
  given_actual_list_when_ofNullableList_then_return_list_copy() {
    // GIVEN
    int a = 10;
    List<Integer> list = new ArrayList<>();
    list.add(a);

    // WHEN
    List<Integer> result = CollectionsUtil.ofNullableList(list);

    // THEN
    assertEquals(1, result.size());
    assertTrue(result.contains(a));
  }
}