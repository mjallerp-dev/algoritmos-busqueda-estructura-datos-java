package com.mjallerp.taller;

import com.mjallerp.busqueda.ArraySearches;

import java.util.Arrays;

public final class EjerciciosGuiados {
  private EjerciciosGuiados() {}

  public static boolean contains(int[] a, int t) {
    return ArraySearches.linearSearch(a, t) != -1;
  }

  public static int count(int[] a, int t) {
    if (a == null) return 0;
    int c = 0;
    for (int v : a) if (v == t) c++;
    return c;
  }

  public static int[] removeFirst(int[] a, int t) {
    if (a == null) return new int[0];
    int idx = ArraySearches.linearSearch(a, t);
    if (idx == -1) return Arrays.copyOf(a, a.length);
    int[] r = new int[a.length - 1];
    System.arraycopy(a, 0, r, 0, idx);
    System.arraycopy(a, idx + 1, r, idx, a.length - idx - 1);
    return r;
  }

  public static int argMin(int[] a) {
    if (a == null || a.length == 0) return -1;
    int min = 0;
    for (int i = 1; i < a.length; i++) {
      if (a[i] < a[min]) min = i;
    }
    return min;
  }

  public static int binarySearchOrInsertionPoint(int[] sorted, int t) {
    if (sorted == null) return -1;
    int lo = 0, hi = sorted.length - 1;
    while (lo <= hi) {
      int mid = lo + ((hi - lo) >>> 1);
      if (sorted[mid] == t) return mid;
      if (sorted[mid] < t) lo = mid + 1;
      else hi = mid - 1;
    }
    return -lo - 1;
  }

  public static int firstIndexOfSorted(int[] a, int t) {
    if (a == null || a.length == 0) return -1;
    int lb = ArraySearches.lowerBound(a, t);
    return (lb < a.length && a[lb] == t) ? lb : -1;
  }

  public static int lastIndexOfSorted(int[] a, int t) {
    if (a == null || a.length == 0) return -1;
    return ArraySearches.lastIndexOfSorted(a, t);
  }

  public static int count2D(int[][] m, int t) {
    if (m == null) return 0;
    int c = 0;
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        if (m[i][j] == t) c++;
      }
    }
    return c;
  }

  public static int[][] staircaseSearchAll(int[][] m, int t) {
    if (m == null || m.length == 0 || m[0].length == 0) return new int[0][];
    int n = m.length, p = m[0].length;
    int[][] temp = new int[n * p][];
    int k = 0;
    int i = 0, j = p - 1;
    while (i < n && j >= 0) {
      if (m[i][j] > t) j--;
      else if (m[i][j] < t) i++;
      else {
        int c = j;
        while (c >= 0 && m[i][c] == t) temp[k++] = new int[] {i, c--};
        i++;
      }
    }
    return Arrays.copyOf(temp, k);
  }

  public static int[][] flattenedRange(int[][] m, int t) {
    int[][] none = {{-1, -1}, {-1, -1}};
    if (m == null || m.length == 0 || m[0].length == 0) return none;
    int p = m[0].length;
    int total = m.length * p;
    int lo = 0, hi = total;
    while (lo < hi) {
      int mid = lo + ((hi - lo) >>> 1);
      if (m[mid / p][mid % p] < t) lo = mid + 1;
      else hi = mid;
    }
    if (lo == total || m[lo / p][lo % p] != t) return none;
    int first = lo;
    hi = total;
    while (lo < hi) {
      int mid = lo + ((hi - lo) >>> 1);
      if (m[mid / p][mid % p] <= t) lo = mid + 1;
      else hi = mid;
    }
    int last = lo - 1;
    return new int[][] {{first / p, first % p}, {last / p, last % p}};
  }

  public static void ejecutar() {
    int[] a = {7, 3, 3, 9, 1, 3};
    System.out.println("Arreglo: " + Arrays.toString(a));
    System.out.println("1. contains(9) -> " + contains(a, 9) + " | contains(5) -> " + contains(a, 5));
    System.out.println("2. count(3) -> " + count(a, 3) + " | count(5) -> " + count(a, 5));
    System.out.println("3. removeFirst(3) -> " + Arrays.toString(removeFirst(a, 3)));
    System.out.println("4. argMin -> " + argMin(a) + " | argMin([]) -> " + argMin(new int[0]));

    int[] sorted = {1, 3, 3, 3, 7, 9, 10};
    System.out.println("Ordenado: " + Arrays.toString(sorted));
    System.out.println("5. binarySearchOrInsertionPoint(7) -> " + binarySearchOrInsertionPoint(sorted, 7)
        + " | (8) -> " + binarySearchOrInsertionPoint(sorted, 8));
    System.out.println("6. firstIndexOfSorted(3) -> " + firstIndexOfSorted(sorted, 3)
        + " | lastIndexOfSorted(3) -> " + lastIndexOfSorted(sorted, 3)
        + " | firstIndexOfSorted(5) -> " + firstIndexOfSorted(sorted, 5));

    int[][] m = {
      {4, 1, 4},
      {2, 4, 8},
      {4, 6, 9}
    };
    System.out.println("7. count2D(4) -> " + count2D(m, 4));

    int[][] ms = {
      {1, 2, 2, 5},
      {2, 2, 4, 6},
      {2, 3, 5, 7}
    };
    System.out.println("8. staircaseSearchAll(2) -> " + Arrays.deepToString(staircaseSearchAll(ms, 2)));

    int[][] mg = {
      {1, 3, 3},
      {3, 3, 8},
      {9, 10, 12}
    };
    System.out.println("9. flattenedRange(3) -> " + Arrays.deepToString(flattenedRange(mg, 3))
        + " | (4) -> " + Arrays.deepToString(flattenedRange(mg, 4)));
  }
}
