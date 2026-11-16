package com.mjallerp.hotel.servicio;

import com.mjallerp.hotel.modelo.EstadoHabitacion;

public final class BusquedaHabitaciones {
  private BusquedaHabitaciones() {}

  public static int[] buscarPrimera(int[][] m, int estado) {
    if (m == null || m.length == 0 || m[0].length == 0) return new int[] {-1, -1};
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        if (m[i][j] == estado) return new int[] {i, j};
      }
    }
    return new int[] {-1, -1};
  }

  public static int[][] buscarTodas(int[][] m, int estado) {
    if (m == null || m.length == 0 || m[0].length == 0) return new int[0][];
    int count = contar(m, estado);
    int[][] pos = new int[count][];
    int k = 0;
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        if (m[i][j] == estado) pos[k++] = new int[] {i, j};
      }
    }
    return pos;
  }

  public static int[][] buscarEnPiso(int[][] m, int fila, int estado) {
    if (m == null || fila < 0 || fila >= m.length) return new int[0][];
    int count = 0;
    for (int v : m[fila]) if (v == estado) count++;
    int[][] pos = new int[count][];
    int k = 0;
    for (int j = 0; j < m[fila].length; j++) {
      if (m[fila][j] == estado) pos[k++] = new int[] {fila, j};
    }
    return pos;
  }

  public static int contar(int[][] m, int estado) {
    if (m == null) return 0;
    int count = 0;
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        if (m[i][j] == estado) count++;
      }
    }
    return count;
  }

  public static int[] buscarPrimeraLibre(int[][] m) {
    return buscarPrimera(m, EstadoHabitacion.LIBRE);
  }

  public static int[][] buscarLibres(int[][] m) {
    return buscarTodas(m, EstadoHabitacion.LIBRE);
  }

  public static int[][] buscarEnMantenimiento(int[][] m) {
    return buscarTodas(m, EstadoHabitacion.MANTENIMIENTO);
  }

  public static int[][] buscarLibresEnPiso(int[][] m, int fila) {
    return buscarEnPiso(m, fila, EstadoHabitacion.LIBRE);
  }
}
