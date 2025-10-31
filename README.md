# 🌳 PROGRAMACIÓN DE SERVICIOS Y PROCESOS  
## Uso de estructuras dinámicas de datos: Árboles y Grafos  
---

## 🧩 Descripción general

Este proyecto contiene tres ejercicios independientes que implementan **estructuras dinámicas de datos** en Java (Árboles y Grafos), aplicadas a problemas prácticos.  
Cada ejercicio se desarrolla con una estructura dinámica (nodos enlazados o grafos ponderados) y se ejecuta mediante una interfaz **interactiva de consola**.

---

## 📘 EJERCICIO 1 – Árbol de Letras

### 🧠 Descripción

Se genera un **árbol binario de letras**, donde el usuario puede ingresar una letra y el programa devolverá el **camino desde la raíz hasta el nodo** que contiene esa letra.  
Durante la búsqueda, se muestra la **secuencia de letras visitadas** y las **direcciones tomadas**:
- `L` = izquierda  
- `R` = derecha  

Si la letra no se encuentra, el programa lo indica claramente.

### ⚙️ Características técnicas
- Estructura dinámica de nodos enlazados (punteros `izq` y `der`).
- Implementación de búsqueda en profundidad (**DFS**) para encontrar la ruta.
- Visualización del árbol **por niveles** (BFS).
- Gestión de errores por entradas vacías o letras inexistentes.

---

## 📞 EJERCICIO 2 – Listín Telefónico (Árbol de Búsqueda Binaria)

### 🧠 Descripción

Se implementa un **Listín Telefónico** (agenda) que gestiona contactos en tiempo real usando un **Árbol Binario de Búsqueda (BST)** dinámico.  
Permite agregar, eliminar, modificar y buscar contactos de manera eficiente.

### 📋 Estructura de cada contacto

| Campo      | Tipo    | Descripción |
|-------------|----------|-------------|
| `nombre`    | String  | Nombre del contacto |
| `apellidos` | String  | Apellidos del contacto |
| `telefono`  | String  | Único, clave de búsqueda |
| `email`     | String  | Opcional |

### ⚙️ Funcionalidades
- **Altas** → Inserta un nuevo contacto (ordenado por *apellido + nombre*).  
- **Bajas** → Elimina contacto por teléfono.  
- **Modificación** → Permite cambiar nombre, apellidos o email manteniendo el teléfono.  
- **Búsquedas:**
  - Por teléfono (búsqueda directa con HashMap).
  - Por prefijo (ej. “Go” devuelve “Gómez”, “González”...).
  - Por rango alfabético (ej. entre “G” y “M”).  
- **Listado ordenado (inorden)** → muestra todos los contactos ordenados alfabéticamente.

### 🧱 Estructuras utilizadas
- Árbol Binario de Búsqueda (BST) para ordenar por apellidos/nombres.
- HashMap adicional para búsqueda directa por teléfono.


---

## 🚇 EJERCICIO 3 – Planificador de Rutas (Grafos)

### 🧠 Descripción

Se diseña un **planificador de rutas urbanas** usando un **Grafo ponderado y dirigido**.  
El programa permite calcular rutas óptimas combinando distintos modos de transporte:

🚶‍♂️ Peatonal  
🚲 Bicicleta  
🚌 Autobús  
🚇 Metro  

Cada arista del grafo tiene un **peso** que representa el tiempo total (en minutos), y el sistema puede aplicar **restricciones** (evitar bicicleta, minimizar transbordos, hora punta, etc.).

### ⚙️ Características técnicas

- **Grafo ponderado y dirigido:** Algunas rutas son unidireccionales.  
- **Algoritmo de Dijkstra:** Calcula el camino más corto (en tiempo).  
- **Restricciones opcionales:**
  - Evitar bicicleta (`--sin-bici`)
  - Minimizar transbordos (`--min-transbordos`)
  - Penalización por hora punta (`+50%` de tiempo en transporte público).  
- **Modularidad:** Los vértices representan lugares y las aristas, conexiones con tipo de transporte y tiempo asociado.

### 🧱 Estructuras utilizadas
- HashMap de adyacencias (`Map<String, List<Arista>>`).
- Cola de prioridad (min-heap) para Dijkstra.
- Clases:
  - `Vertice` → Representa un punto de la ciudad.
  - `Arista` → Representa una conexión (con tipo y tiempo).
  - `Grafo` → Gestiona nodos, aristas y cálculo de rutas.





