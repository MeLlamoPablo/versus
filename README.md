# versus

`versus` is a Java project for my `Developement of Web Applications` course. It allows to generate single elimination brackets, double elimination brackets, and leagues.

## Manual de uso

`versus` soporta tres tipos de competiciones:

- **Competición de eliminación simple**: una competición donde un número de equipos que sea potencia de dos
(4, 8, 16, etc.) se enfrentan en sucesivas rondas, avanzando el ganador a la siguiente ronda, y eliminándose el
perdedor, hasta que solo queda un campeón. Ejemplo:
[Wimbledon 2016](https://en.wikipedia.org/wiki/2016_Wimbledon_Championships_%E2%80%93_Men%27s_Singles#Draw).
- **Competición de eliminación doble**: una competición similar a la de eliminación simple, con la diferencia de que
los perdedores descienden al llamado "cuadro inderior", que puede considerarse como otra competición de eliminación
simple en sí misma (al perder en el cuadro inferior sí se elimina del torneo). El ganador del cuadro superior y el del
cuadro inferior se enfrentan en la final. Ejemplo:
[The International 2016](http://wiki.teamliquid.net/dota2/The_International/2016/Main_Event#Bracket).
- **Competición de liga**: una competición en la que todos los equipos se enfrentan con el resto. Se otogran puntos por
victoria y por empate, y al final de todas las jornadas, el equipo con más puntos es proclamado campeón. Ejemplo:
[LaLiga Santander 2017](https://es.wikipedia.org/wiki/Primera_Divisi%C3%B3n_de_Espa%C3%B1a_2016-17#Resultados).

Pese a soportar estos tres tipos de competiciones, solo se pueden gestionar ligas mediante interfaz gráfica. Las
competiciones de eliminación serán implementadas en posteriores versiones.

Al ejecutar el programa, verá una pantalla como la siguiente:

```
*** Versus ***

* 1 - Añadir equipo
* 2 - Editar liga
* 0 - Salir

Por favor, introduzca el número de una opción: 
```

Introduzca un `1` y pulse `ENTER` para añadir un equipo. Tras haber añadido suficientes equipos, introduzca un `2`
para lanzar la interfaz gráfica de edición. Recuerde que es preciso que introduzca un número de equipos par. `versus`
no soporta aún la generación de ligas con un número de participantes impares, y se producirá un error al intentarla.