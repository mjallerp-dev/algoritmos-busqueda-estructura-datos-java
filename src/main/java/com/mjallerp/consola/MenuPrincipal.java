package com.mjallerp.consola;

import com.mjallerp.hotel.consola.MenuHotel;
import com.mjallerp.taller.EjerciciosGuiados;
import com.mjallerp.taller.MainSearchDemo;
import com.mjallerp.taller.MedicionTiempos;
import com.mjallerp.taller.RetosBusqueda;

public class MenuPrincipal {
  private final LectorConsola lector;
  private final MenuHotel menuHotel;

  public MenuPrincipal(LectorConsola lector) {
    this.lector = lector;
    this.menuHotel = new MenuHotel(lector);
  }

  public void mostrar() {
    int opcion;
    do {
      System.out.println("\n===== ALGORITMOS DE BÚSQUEDA =====");
      System.out.println("1. Demo del taller (arreglos y matrices)");
      System.out.println("2. Ejercicios guiados");
      System.out.println("3. Medición de tiempos lineal vs binaria");
      System.out.println("4. Retos de aprendizaje");
      System.out.println("5. Ejercicio 10: Control de habitaciones");
      System.out.println("0. Salir");
      opcion = lector.leerEntero("Seleccione una opción: ");
      System.out.println();
      switch (opcion) {
        case 1: MainSearchDemo.ejecutar(); break;
        case 2: EjerciciosGuiados.ejecutar(); break;
        case 3: MedicionTiempos.ejecutar(); break;
        case 4: RetosBusqueda.ejecutar(); break;
        case 5: menuHotel.mostrar(); break;
        case 0: System.out.println("Hasta luego."); break;
        default: System.out.println("Opción inválida.");
      }
      if (opcion >= 1 && opcion <= 4) lector.pausar();
    } while (opcion != 0);
  }
}
