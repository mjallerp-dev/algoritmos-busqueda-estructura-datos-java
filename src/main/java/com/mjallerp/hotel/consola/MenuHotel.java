package com.mjallerp.hotel.consola;

import com.mjallerp.consola.LectorConsola;
import com.mjallerp.hotel.datos.EscenariosHotel;
import com.mjallerp.hotel.modelo.EstadoHabitacion;
import com.mjallerp.hotel.modelo.Hotel;
import com.mjallerp.hotel.pruebas.PruebasHabitaciones;
import com.mjallerp.hotel.servicio.BusquedaHabitaciones;

public class MenuHotel {
  private final LectorConsola lector;
  private Hotel hotel;

  public MenuHotel(LectorConsola lector) {
    this.lector = lector;
    this.hotel = EscenariosHotel.normal();
  }

  public void mostrar() {
    int opcion;
    do {
      System.out.println("\n===== CONTROL DE HABITACIONES =====");
      System.out.println("Hotel actual: " + hotel.getNombre());
      System.out.println("1. Mostrar mapa del hotel");
      System.out.println("2. Encontrar la primera habitación libre");
      System.out.println("3. Encontrar todas las habitaciones libres");
      System.out.println("4. Localizar habitaciones en mantenimiento");
      System.out.println("5. Buscar disponibilidad en un piso específico");
      System.out.println("6. Cambiar estado de una habitación");
      System.out.println("7. Cargar otro escenario");
      System.out.println("8. Ejecutar casos de prueba");
      System.out.println("9. Ver complejidad de los algoritmos");
      System.out.println("0. Volver");
      opcion = lector.leerEntero("Seleccione una opción: ");
      System.out.println();
      switch (opcion) {
        case 1: mostrarMapa(); break;
        case 2: primeraLibre(); break;
        case 3: todasLibres(); break;
        case 4: enMantenimiento(); break;
        case 5: disponibilidadPiso(); break;
        case 6: cambiarEstado(); break;
        case 7: cargarEscenario(); break;
        case 8: PruebasHabitaciones.ejecutar(); break;
        case 9: mostrarComplejidad(); break;
        case 0: break;
        default: System.out.println("Opción inválida.");
      }
      if (opcion != 0) lector.pausar();
    } while (opcion != 0);
  }

  private void mostrarMapa() {
    int[][] m = hotel.getHabitaciones();
    System.out.println("L = Libre | O = Ocupada | M = Mantenimiento");
    System.out.print("        ");
    for (int j = 0; j < hotel.getHabitacionesPorPiso(); j++) System.out.printf("Col %-5d", j);
    System.out.println();
    for (int i = 0; i < m.length; i++) {
      System.out.printf("Piso %-2d ", i + 1);
      for (int j = 0; j < m[i].length; j++) {
        System.out.printf("%d %c    ", Hotel.numeroHabitacion(i, j), EstadoHabitacion.simbolo(m[i][j]));
      }
      System.out.println();
    }
    System.out.printf("Libres: %d | Ocupadas: %d | Mantenimiento: %d%n",
        BusquedaHabitaciones.contar(m, EstadoHabitacion.LIBRE),
        BusquedaHabitaciones.contar(m, EstadoHabitacion.OCUPADA),
        BusquedaHabitaciones.contar(m, EstadoHabitacion.MANTENIMIENTO));
  }

  private void primeraLibre() {
    int[] pos = BusquedaHabitaciones.buscarPrimeraLibre(hotel.getHabitaciones());
    if (pos[0] == -1) {
      System.out.println("No hay habitaciones libres en el hotel.");
      return;
    }
    System.out.println("Primera habitación libre:");
    imprimirPosicion(pos);
  }

  private void todasLibres() {
    int[][] pos = BusquedaHabitaciones.buscarLibres(hotel.getHabitaciones());
    imprimirResultados("Habitaciones libres", pos, "No hay habitaciones libres en el hotel.");
  }

  private void enMantenimiento() {
    int[][] pos = BusquedaHabitaciones.buscarEnMantenimiento(hotel.getHabitaciones());
    imprimirResultados("Habitaciones en mantenimiento", pos, "No hay habitaciones en mantenimiento.");
  }

  private void disponibilidadPiso() {
    int piso = lector.leerEntero("Ingrese el número de piso (1 - " + hotel.getPisos() + "): ");
    if (piso < 1 || piso > hotel.getPisos()) {
      System.out.println("El piso " + piso + " no existe en el hotel.");
      return;
    }
    int[][] pos = BusquedaHabitaciones.buscarLibresEnPiso(hotel.getHabitaciones(), piso - 1);
    imprimirResultados("Habitaciones libres en el piso " + piso, pos,
        "No hay disponibilidad en el piso " + piso + ".");
  }

  private void cambiarEstado() {
    int piso = lector.leerEntero("Piso (1 - " + hotel.getPisos() + "): ");
    int hab = lector.leerEntero("Habitación del piso (1 - " + hotel.getHabitacionesPorPiso() + "): ");
    if (!hotel.esPosicionValida(piso - 1, hab - 1)) {
      System.out.println("La habitación indicada no existe.");
      return;
    }
    int estado = lector.leerEntero("Nuevo estado (0 = Libre, 1 = Ocupada, 2 = Mantenimiento): ");
    if (!EstadoHabitacion.esValido(estado)) {
      System.out.println("Estado inválido.");
      return;
    }
    hotel.setEstado(piso - 1, hab - 1, estado);
    System.out.println("Habitación " + Hotel.numeroHabitacion(piso - 1, hab - 1)
        + " actualizada a " + EstadoHabitacion.nombre(estado) + ".");
  }

  private void cargarEscenario() {
    System.out.println("1. Escenario normal");
    System.out.println("2. Hotel sin disponibilidad");
    System.out.println("3. Temporada baja (casi todo libre)");
    int opcion = lector.leerEntero("Seleccione el escenario: ");
    switch (opcion) {
      case 1: hotel = EscenariosHotel.normal(); break;
      case 2: hotel = EscenariosHotel.lleno(); break;
      case 3: hotel = EscenariosHotel.temporadaBaja(); break;
      default:
        System.out.println("Escenario inválido.");
        return;
    }
    System.out.println("Escenario cargado: " + hotel.getNombre());
  }

  private void mostrarComplejidad() {
    int n = hotel.getPisos(), m = hotel.getHabitacionesPorPiso();
    System.out.println("Matriz actual: " + n + " pisos x " + m + " habitaciones = " + (n * m) + " posiciones");
    System.out.println("Primera habitación libre: búsqueda lineal 2D, mejor caso O(1), peor caso O(n·m).");
    System.out.println("Todas las libres: dos recorridos completos (contar y llenar), O(n·m).");
    System.out.println("Mantenimiento: dos recorridos completos (contar y llenar), O(n·m).");
    System.out.println("Disponibilidad en un piso: recorrido lineal de una sola fila, O(m).");
    System.out.println("No se usa búsqueda binaria porque los estados no están ordenados dentro de la matriz.");
  }

  private void imprimirResultados(String titulo, int[][] pos, String mensajeVacio) {
    if (pos.length == 0) {
      System.out.println(mensajeVacio);
      return;
    }
    System.out.println(titulo + " (" + pos.length + " encontradas):");
    for (int[] p : pos) imprimirPosicion(p);
  }

  private void imprimirPosicion(int[] p) {
    System.out.printf("  Habitación %d -> [fila %d, columna %d] (piso %d)%n",
        Hotel.numeroHabitacion(p[0], p[1]), p[0], p[1], p[0] + 1);
  }
}
