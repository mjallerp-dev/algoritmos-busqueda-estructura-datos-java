package com.mjallerp.hotel.datos;

import com.mjallerp.hotel.modelo.Hotel;

public final class EscenariosHotel {
  private EscenariosHotel() {}

  public static Hotel normal() {
    int[][] m = {
      {1, 1, 0, 2, 1},
      {0, 1, 1, 0, 2},
      {1, 1, 1, 1, 1},
      {2, 0, 0, 1, 0}
    };
    return new Hotel("Hotel Caribe (escenario normal)", m);
  }

  public static Hotel lleno() {
    int[][] m = {
      {1, 1, 1, 2},
      {1, 2, 1, 1},
      {1, 1, 1, 1}
    };
    return new Hotel("Hotel Caribe (sin disponibilidad)", m);
  }

  public static Hotel temporadaBaja() {
    int[][] m = {
      {0, 0, 0, 0, 0, 0},
      {0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0}
    };
    return new Hotel("Hotel Caribe (temporada baja)", m);
  }
}
