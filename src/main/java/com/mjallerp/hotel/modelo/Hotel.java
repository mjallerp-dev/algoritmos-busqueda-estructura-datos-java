package com.mjallerp.hotel.modelo;

public class Hotel {
  private final String nombre;
  private final int[][] habitaciones;

  public Hotel(String nombre, int[][] habitaciones) {
    if (habitaciones == null || habitaciones.length == 0 || habitaciones[0].length == 0) {
      throw new IllegalArgumentException("El hotel debe tener al menos un piso y una habitación");
    }
    this.nombre = nombre;
    this.habitaciones = habitaciones;
  }

  public String getNombre() {
    return nombre;
  }

  public int[][] getHabitaciones() {
    return habitaciones;
  }

  public int getPisos() {
    return habitaciones.length;
  }

  public int getHabitacionesPorPiso() {
    return habitaciones[0].length;
  }

  public boolean esPosicionValida(int fila, int columna) {
    return fila >= 0 && fila < habitaciones.length && columna >= 0 && columna < habitaciones[fila].length;
  }

  public int getEstado(int fila, int columna) {
    return habitaciones[fila][columna];
  }

  public void setEstado(int fila, int columna, int estado) {
    if (!esPosicionValida(fila, columna)) throw new IllegalArgumentException("Habitación inexistente");
    if (!EstadoHabitacion.esValido(estado)) throw new IllegalArgumentException("Estado inválido");
    habitaciones[fila][columna] = estado;
  }

  public static int numeroHabitacion(int fila, int columna) {
    return (fila + 1) * 100 + (columna + 1);
  }
}
