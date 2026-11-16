package com.mjallerp.taller;

import com.mjallerp.busqueda.ArraySearches;

import java.util.Arrays;
import java.util.Random;

public final class MedicionTiempos {
  private static final int TAMANO = 5_000_000;
  private static final int CORRIDAS = 10;
  private static final int CALENTAMIENTO = 3;

  private MedicionTiempos() {}

  public static void ejecutar() {
    Random random = new Random(42);
    int[] datos = new int[TAMANO];
    for (int i = 0; i < TAMANO; i++) datos[i] = random.nextInt(TAMANO * 2);
    int[] ordenados = Arrays.copyOf(datos, datos.length);
    Arrays.sort(ordenados);

    int[] objetivos = new int[CALENTAMIENTO + CORRIDAS];
    for (int i = 0; i < objetivos.length; i++) objetivos[i] = random.nextInt(TAMANO * 2);

    long totalLineal = 0, totalBinaria = 0;
    for (int r = 0; r < objetivos.length; r++) {
      int t = objetivos[r];
      long inicio = System.nanoTime();
      int idxLineal = ArraySearches.linearSearch(datos, t);
      long tLineal = System.nanoTime() - inicio;

      inicio = System.nanoTime();
      int idxBinaria = ArraySearches.binarySearchIter(ordenados, t);
      long tBinaria = System.nanoTime() - inicio;

      if (r < CALENTAMIENTO) continue;
      totalLineal += tLineal;
      totalBinaria += tBinaria;
      System.out.printf("Corrida %2d | objetivo %8d | lineal %10d ns (idx %d) | binaria %7d ns (idx %d)%n",
          r - CALENTAMIENTO + 1, t, tLineal, idxLineal, tBinaria, idxBinaria);
    }
    System.out.printf("Promedio lineal:  %d ns%n", totalLineal / CORRIDAS);
    System.out.printf("Promedio binaria: %d ns%n", totalBinaria / CORRIDAS);
  }
}
