package com.mjallerp.taller;

import com.mjallerp.busqueda.ArraySearches;
import com.mjallerp.busqueda.MatrixSearches;

import java.util.Arrays;

public class MainSearchDemo {
  public static void main(String[] args) {
    ejecutar();
  }

  public static void ejecutar() {
    int[] a = {7, 3, 3, 9, 1, 3};
    System.out.println("Linear first 3 -> " + ArraySearches.linearSearch(a, 3));
    System.out.println("Linear last 3  -> " + ArraySearches.linearSearchLast(a, 3));
    System.out.println("All 3 -> " + Arrays.toString(ArraySearches.linearSearchAll(a, 3)));
    System.out.println("Sentinel 9 -> " + ArraySearches.linearSearchSentinel(a, 9));

    int[] sorted = {1, 3, 3, 3, 7, 9, 10};
    System.out.println("Binary 7 -> " + ArraySearches.binarySearchIter(sorted, 7));
    System.out.println("Binary rec 7 -> " + ArraySearches.binarySearchRec(sorted, 7));
    System.out.println("lowerBound(3) -> " + ArraySearches.lowerBound(sorted, 3));
    System.out.println("lastIndexOfSorted(3) -> " + ArraySearches.lastIndexOfSorted(sorted, 3));

    String[] nombres = {"Ana", "Carlos", "Luis", "Maria", "Sofia"};
    System.out.println("Comparator Luis -> " + ArraySearches.binarySearch(nombres, "Luis", String::compareTo));

    int[][] m1 = {
      {1, 4, 7},
      {2, 5, 8},
      {3, 6, 9}
    };
    System.out.println("Linear2D 6 -> " + Arrays.toString(MatrixSearches.linearSearch2D(m1, 6)));
    System.out.println("Staircase 5 -> " + Arrays.toString(MatrixSearches.staircaseSearch(m1, 5)));

    int[][] m2 = {
      {1, 3, 5},
      {7, 9, 11},
      {13, 15, 17}
    };
    System.out.println("Flattened 15 -> " + Arrays.toString(MatrixSearches.binarySearchFlattened(m2, 15)));
  }
}
