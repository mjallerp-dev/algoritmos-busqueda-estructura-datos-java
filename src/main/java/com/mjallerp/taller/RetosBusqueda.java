package com.mjallerp.taller;

import com.mjallerp.busqueda.ArraySearches;

import java.util.Arrays;
import java.util.Random;

public final class RetosBusqueda {
  private RetosBusqueda() {}

  public static int jumpSearch(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    int n = a.length;
    int step = (int) Math.sqrt(n);
    int prev = 0, curr = step;
    while (curr < n && a[curr - 1] < target) {
      prev = curr;
      curr += step;
    }
    int end = Math.min(curr, n);
    for (int i = prev; i < end; i++) {
      if (a[i] == target) return i;
    }
    return -1;
  }

  public static int exponentialSearch(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    if (a[0] == target) return 0;
    int bound = 1;
    while (bound < a.length && a[bound] < target) bound *= 2;
    int lo = bound / 2, hi = Math.min(bound, a.length - 1);
    while (lo <= hi) {
      int mid = lo + ((hi - lo) >>> 1);
      if (a[mid] == target) return mid;
      if (a[mid] < target) lo = mid + 1;
      else hi = mid - 1;
    }
    return -1;
  }

  public static int safeSearch(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    int lo = 0, hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + ((hi - lo) >>> 1);
      if (a[mid] == target) return mid;
      if (a[mid] < target) lo = mid + 1;
      else hi = mid - 1;
    }
    boolean izquierdaValida = lo == 0 || a[lo - 1] < target;
    boolean derechaValida = lo == a.length || a[lo] > target;
    if (izquierdaValida && derechaValida && isSorted(a)) return -1;
    System.out.println("[LOG] Postcondición de binaria no cumplida, se usa búsqueda lineal");
    return ArraySearches.linearSearch(a, target);
  }

  private static boolean isSorted(int[] a) {
    for (int i = 1; i < a.length; i++) {
      if (a[i - 1] > a[i]) return false;
    }
    return true;
  }

  public static void ejecutar() {
    int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21};
    System.out.println("Ordenado: " + Arrays.toString(sorted));
    System.out.println("1. jumpSearch(13) -> " + jumpSearch(sorted, 13) + " | (4) -> " + jumpSearch(sorted, 4));
    System.out.println("2. exponentialSearch(19) -> " + exponentialSearch(sorted, 19)
        + " | (20) -> " + exponentialSearch(sorted, 20));

    int[] casiOrdenado = {1, 3, 5, 7, 4, 9, 11};
    System.out.println("Casi ordenado: " + Arrays.toString(casiOrdenado));
    System.out.println("3. safeSearch(4) -> " + safeSearch(casiOrdenado, 4));
    System.out.println("3. safeSearch en ordenado(9) -> " + safeSearch(sorted, 9));

    System.out.println();
    System.out.println("Comparación de tiempos (ns) en el peor caso (último elemento):");
    System.out.printf("%10s | %12s | %10s | %10s%n", "Tamaño", "Lineal", "Binaria", "Jump");
    int[] tamanos = {1_000, 100_000, 1_000_000};
    Random random = new Random(7);
    for (int n : tamanos) {
      int[] datos = new int[n];
      int valor = 0;
      for (int i = 0; i < n; i++) {
        valor += 1 + random.nextInt(3);
        datos[i] = valor;
      }
      int t = datos[n - 1];
      for (int w = 0; w < 5; w++) {
        ArraySearches.linearSearch(datos, t);
        ArraySearches.binarySearchIter(datos, t);
        jumpSearch(datos, t);
      }
      long inicio = System.nanoTime();
      ArraySearches.linearSearch(datos, t);
      long tl = System.nanoTime() - inicio;
      inicio = System.nanoTime();
      ArraySearches.binarySearchIter(datos, t);
      long tb = System.nanoTime() - inicio;
      inicio = System.nanoTime();
      jumpSearch(datos, t);
      long tj = System.nanoTime() - inicio;
      System.out.printf("%10d | %12d | %10d | %10d%n", n, tl, tb, tj);
    }
  }
}
