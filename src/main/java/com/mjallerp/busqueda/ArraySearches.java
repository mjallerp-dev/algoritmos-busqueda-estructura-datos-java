package com.mjallerp.busqueda;

import java.util.Comparator;

public final class ArraySearches {
  private ArraySearches() {}

  public static int linearSearch(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    for (int i = 0; i < a.length; i++) {
      if (a[i] == target) return i;
    }
    return -1;
  }

  public static int[] linearSearchAll(int[] a, int target) {
    if (a == null || a.length == 0) return new int[0];
    int count = 0;
    for (int v : a) if (v == target) count++;
    int[] idx = new int[count];
    int k = 0;
    for (int i = 0; i < a.length; i++) if (a[i] == target) idx[k++] = i;
    return idx;
  }

  public static int linearSearchLast(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    for (int i = a.length - 1; i >= 0; i--) {
      if (a[i] == target) return i;
    }
    return -1;
  }

  public static int linearSearchSentinel(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    int last = a[a.length - 1];
    a[a.length - 1] = target;
    int i = 0;
    while (a[i] != target) i++;
    a[a.length - 1] = last;
    if (i < a.length - 1) return i;
    return (last == target) ? a.length - 1 : -1;
  }

  public static int binarySearchIter(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    int lo = 0, hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + ((hi - lo) >>> 1);
      if (a[mid] == target) return mid;
      if (a[mid] < target) lo = mid + 1;
      else hi = mid - 1;
    }
    return -1;
  }

  public static int binarySearchRec(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    return bs(a, target, 0, a.length - 1);
  }

  private static int bs(int[] a, int t, int lo, int hi) {
    if (lo > hi) return -1;
    int mid = lo + ((hi - lo) >>> 1);
    if (a[mid] == t) return mid;
    if (a[mid] < t) return bs(a, t, mid + 1, hi);
    return bs(a, t, lo, mid - 1);
  }

  public static int lowerBound(int[] a, int target) {
    int lo = 0, hi = a.length;
    while (lo < hi) {
      int mid = lo + ((hi - lo) >>> 1);
      if (a[mid] < target) lo = mid + 1;
      else hi = mid;
    }
    return lo;
  }

  public static int lastIndexOfSorted(int[] a, int target) {
    int lo = 0, hi = a.length;
    while (lo < hi) {
      int mid = lo + ((hi - lo) >>> 1);
      if (a[mid] <= target) lo = mid + 1;
      else hi = mid;
    }
    int idx = lo - 1;
    return (idx >= 0 && a[idx] == target) ? idx : -1;
  }

  public static <T> int binarySearch(T[] a, T target, Comparator<? super T> cmp) {
    if (a == null || a.length == 0) return -1;
    int lo = 0, hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + ((hi - lo) >>> 1);
      int c = cmp.compare(a[mid], target);
      if (c == 0) return mid;
      if (c < 0) lo = mid + 1;
      else hi = mid - 1;
    }
    return -1;
  }
}
