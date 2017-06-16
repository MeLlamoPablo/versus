# versus

`versus` is a Java project for my `Developement of Web Applications` course. It allows to generate single elimination brackets, double elimination brackets, and leagues.

## Manual de uso

`versus` soporta tres tipos de competiciones:

- **Competición de eliminación simple**: una competición donde un número de equipos que sea potencia de dos
(4, 8, 16, etc.) se enfrentan en sucesivas rondas, avanzando el ganador a la siguiente ronda, y eliminándose el
perdedor, hasta que solo queda un campeón. Ejemplo:
[Wimbledon 2016](https://en.wikipedia.org/wiki/2016_Wimbledon_Championships_%E2%80%93_Men%27s_Singles#Draw).
- **Competición de eliminación doble**: una competición similar a la de eliminación simple, con la diferencia de que
los perdedores descienden al llamado "cuadro inferior", que puede considerarse como otra competición de eliminación
simple en sí misma (al perder en el cuadro inferior sí se elimina del torneo). El ganador del cuadro superior y el del
cuadro inferior se enfrentan en la final. Ejemplo:
[The International 2016](http://wiki.teamliquid.net/dota2/The_International/2016/Main_Event#Bracket).
- **Competición de liga**: una competición en la que todos los equipos se enfrentan con el resto. Se otogran puntos por
victoria y por empate, y al final de todas las jornadas, el equipo con más puntos es proclamado campeón. Ejemplo:
[LaLiga Santander 2017](https://es.wikipedia.org/wiki/Primera_Divisi%C3%B3n_de_Espa%C3%B1a_2016-17#Resultados).

Pese a soportar estos tres tipos de competiciones, solo se pueden gestionar ligas mediante interfaz gráfica. Las
competiciones de eliminación serán implementadas en posteriores versiones.

Al ejecutar el programa, veremos una pantalla como la siguiente:

```
*** Versus ***

* 1 - Ver ligas
* 2 - Añadir liga
* 3 - Editar liga
* 4 - Borrar liga
* 5 - Ordenar ligas por nombre
* 6 - Ordenar ligas por nº de equipos
* 7 - Añadir equipo a una liga
* 8 - Lanzar panel de control de una liga
* 0 - Salir

Por favor, introduzca el número de una opción: 
```

Para crear una liga, primero debemos introducir `2`. Introduciremos el nombre, si es de ida o de ida y vuelta, y el
número de puntos otorgados por victoria, empate y derrota. Por ejemplo:

```
Introduzca el nombre de la liga: LaLiga Santander
¿Tiene la liga ida y vuelta? (Y/N): y
Introduzca el número de puntos otorgados por victoria: 3
Introduzca el número de puntos otorgados por empate: 1
Introduzca el número de puntos otorgados por derrota: 0
```

Tras ello, deberemos añadir equipos a la liga. Pulsamos la opción `7`, e introducimos el ID de la liga (Si no hemos
creado una antes, será `1`). Tras ello, introducimos el nombre del equipo:

```
Introduzca el ID de la liga a la cual añadir un equipo: 1
Introduzca el nombre del equipo: Sevilla F.C.
```

Añadiremos tantos equipos como deseemos. **Importante: el número de equipos debe ser par**.

Una vez añadidos, pulsamos 8 e introducimos de nuevo el ID de la liga. Con ello, accederemos al panel de control, donde
podremos editar los resultados y ver la clasificación.

Recuerda pulsar 0 para salir, en lugar de cerrar la ventana; de esa forma se guardarán los datos para el próximo uso.
