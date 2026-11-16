package com.mjallerp.hotel.modelo;

public final class EstadoHabitacion {
  public static final int LIBRE = 0;
  public static final int OCUPADA = 1;
  public static final int MANTENIMIENTO = 2;

  private EstadoHabitacion() {}

  public static boolean esValido(int estado) {
    return estado == LIBRE || estado == OCUPADA || estado == MANTENIMIENTO;
  }

  public static String nombre(int estado) {
    switch (estado) {
      case LIBRE: return "Libre";
      case OCUPADA: return "Ocupada";
      case MANTENIMIENTO: return "Mantenimiento";
      default: return "Desconocido";
    }
  }

  public static char simbolo(int estado) {
    switch (estado) {
      case LIBRE: return 'L';
      case OCUPADA: return 'O';
      case MANTENIMIENTO: return 'M';
      default: return '?';
    }
  }
}
