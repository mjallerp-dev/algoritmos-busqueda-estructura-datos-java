# Algoritmos de búsqueda - Estructura de datos (Java)

Actividad de la Unidad 2: algoritmos de búsqueda en arreglos y matrices. Incluye el taller completo
(implementaciones base, ejercicios guiados, medición de tiempos y retos) y el **ejercicio asignado
número 10: Control de habitaciones**.

## Ejercicio 10: Control de habitaciones

Las habitaciones de un hotel se representan con una matriz `int[][]` donde cada fila es un piso y
cada columna una habitación del piso. Cada celda guarda el estado de la habitación:

| Valor | Estado        |
|-------|---------------|
| 0     | Libre         |
| 1     | Ocupada       |
| 2     | Mantenimiento |

El número de habitación se calcula como `(fila + 1) * 100 + (columna + 1)`; por ejemplo `[1, 3]` es la habitación 204.

Búsquedas implementadas (todas manuales, sin métodos de búsqueda del lenguaje):

| Búsqueda                              | Algoritmo                         | Complejidad          |
|---------------------------------------|-----------------------------------|----------------------|
| Primera habitación libre              | Búsqueda lineal 2D (fila a fila)  | O(1) mejor, O(n·m) peor |
| Todas las habitaciones libres         | Recorrido completo de la matriz   | O(n·m)               |
| Habitaciones en mantenimiento         | Recorrido completo de la matriz   | O(n·m)               |
| Disponibilidad en un piso específico  | Búsqueda lineal sobre una fila    | O(m)                 |

Todas retornan la fila y columna de cada habitación encontrada (`[-1, -1]` o un arreglo vacío cuando no hay coincidencias).
No se usa búsqueda binaria porque los estados no están ordenados dentro de la matriz.

## Estructura del proyecto

```
src/main/java/com/mjallerp
├── Main.java                         Punto de entrada
├── consola                           Menú principal y lectura de datos
├── busqueda                          ArraySearches y MatrixSearches (implementaciones base)
├── taller                            Demo, ejercicios guiados, medición de tiempos y retos
└── hotel                             Ejercicio 10
    ├── modelo                        EstadoHabitacion y Hotel
    ├── datos                         Escenarios de prueba del hotel
    ├── servicio                      BusquedaHabitaciones
    ├── consola                       MenuHotel
    └── pruebas                       PruebasHabitaciones
```

## Requisitos

- Java 17 o superior
- Maven (opcional, IntelliJ IDEA lo trae incluido)

## Ejecución

Desde IntelliJ IDEA: abrir el proyecto y ejecutar `com.mjallerp.Main`.

Desde la terminal con Maven:

```bash
mvn compile
java -cp target/classes com.mjallerp.Main
```

Sin Maven:

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out com.mjallerp.Main
```

## Menú

```
1. Demo del taller (arreglos y matrices)
2. Ejercicios guiados
3. Medición de tiempos lineal vs binaria
4. Retos de aprendizaje
5. Ejercicio 10: Control de habitaciones
0. Salir
```

Dentro de la opción 5 se puede ver el mapa del hotel, ejecutar cada búsqueda, cambiar el estado de una
habitación, cargar otros escenarios (normal, sin disponibilidad, temporada baja), ejecutar los casos de
prueba y consultar la complejidad de los algoritmos.
