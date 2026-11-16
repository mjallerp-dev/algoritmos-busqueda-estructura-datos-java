package com.mjallerp.hotel.pruebas;

import com.mjallerp.hotel.datos.EscenariosHotel;
import com.mjallerp.hotel.servicio.BusquedaHabitaciones;

import java.util.Arrays;

public final class PruebasHabitaciones {
  private static int exitosas;
  private static int fallidas;

  private PruebasHabitaciones() {}

  public static void ejecutar() {
    exitosas = 0;
    fallidas = 0;
    int[][] normal = EscenariosHotel.normal().getHabitaciones();
    int[][] lleno = EscenariosHotel.lleno().getHabitaciones();
    int[][] baja = EscenariosHotel.temporadaBaja().getHabitaciones();

    verificar("Primera libre en escenario normal",
        new int[] {0, 2}, BusquedaHabitaciones.buscarPrimeraLibre(normal));
    verificar("Primera libre en hotel lleno (no existe)",
        new int[] {-1, -1}, BusquedaHabitaciones.buscarPrimeraLibre(lleno));
    verificar("Primera libre en temporada baja (primera posición)",
        new int[] {0, 0}, BusquedaHabitaciones.buscarPrimeraLibre(baja));

    verificar("Todas las libres en escenario normal (varias apariciones)",
        new int[][] {{0, 2}, {1, 0}, {1, 3}, {3, 1}, {3, 2}, {3, 4}}, BusquedaHabitaciones.buscarLibres(normal));
    verificar("Todas las libres en hotel lleno",
        new int[0][], BusquedaHabitaciones.buscarLibres(lleno));

    verificar("Mantenimiento en escenario normal",
        new int[][] {{0, 3}, {1, 4}, {3, 0}}, BusquedaHabitaciones.buscarEnMantenimiento(normal));
    verificar("Mantenimiento en temporada baja (no existe)",
        new int[0][], BusquedaHabitaciones.buscarEnMantenimiento(baja));

    verificar("Disponibilidad piso 2",
        new int[][] {{1, 0}, {1, 3}}, BusquedaHabitaciones.buscarLibresEnPiso(normal, 1));
    verificar("Disponibilidad piso 3 (sin libres)",
        new int[0][], BusquedaHabitaciones.buscarLibresEnPiso(normal, 2));
    verificar("Disponibilidad en piso inexistente",
        new int[0][], BusquedaHabitaciones.buscarLibresEnPiso(normal, 9));
    verificar("Matriz nula",
        new int[] {-1, -1}, BusquedaHabitaciones.buscarPrimeraLibre(null));

    System.out.println("\nResultado: " + exitosas + " exitosas, " + fallidas + " fallidas");
  }

  private static void verificar(String nombre, int[] esperado, int[] obtenido) {
    registrar(nombre, Arrays.equals(esperado, obtenido), Arrays.toString(esperado), Arrays.toString(obtenido));
  }

  private static void verificar(String nombre, int[][] esperado, int[][] obtenido) {
    registrar(nombre, Arrays.deepEquals(esperado, obtenido),
        Arrays.deepToString(esperado), Arrays.deepToString(obtenido));
  }

  private static void registrar(String nombre, boolean ok, String esperado, String obtenido) {
    if (ok) exitosas++; else fallidas++;
    System.out.printf("[%s] %s -> obtenido %s%n", ok ? "OK" : "FALLO", nombre, obtenido);
    if (!ok) System.out.println("       esperado " + esperado);
  }
}
