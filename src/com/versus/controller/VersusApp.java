package com.versus.controller;

import com.versus.model.Competitor;
import com.versus.model.LeagueCompetition;
import com.versus.model.LeagueCompetitionRules;
import com.versus.model.exceptions.BadInputException;
import com.versus.views.leagueCompetitionView.LeagueCompetitionView;
import io.github.mellamopablo.simplecliframework.App;
import io.github.mellamopablo.simplecliframework.Language;
import io.github.mellamopablo.simplecliframework.MenuEntry;

import java.io.*;
import java.util.*;
import java.util.List;

@SuppressWarnings({"SameParameterValue", "FieldCanBeLocal"})
public class VersusApp {

	private static final String FILE_NAME = "leagueCompetitions.dat";

	private List<LeagueCompetition> leagues;

	private MenuEntry getLeagues = new MenuEntry("Ver ligas", () -> {

		for (int i = 0; i < this.leagues.size(); i++) {

			LeagueCompetition league = this.leagues.get(i);

			int id = i + 1;
			String name = league.getName();
			int numOfTeams = league.getCompetitors().size();

			System.out.printf(
				"#%d, %s (%d equipo%s)\n",
				id,
				name,
				numOfTeams,
				numOfTeams == 1 ? "" : "s"
			);

		}

		if (this.leagues.size() == 0) {

			System.out.println("No hay ligas guardadas.");

		}

	});

	private MenuEntry addLeague = new MenuEntry("Añadir liga", () -> {

		String leagueName = askForString("Introduzca el nombre de la liga");
		boolean twoLegs = askForBoolean("¿Tiene la liga ida y vuelta?");
		int pointsPerWin = askForPositiveInt("Introduzca el número de puntos otorgados por victoria");
		int pointsPerDraw = askForPositiveInt("Introduzca el número de puntos otorgados por empate");
		int pointsPerLoss = askForPositiveInt("Introduzca el número de puntos otorgados por derrota");

		LeagueCompetition league = new LeagueCompetition(leagueName, twoLegs, new LeagueCompetitionRules(
			pointsPerWin, pointsPerDraw, pointsPerLoss
		));

		leagues.add(league);

	});

	private MenuEntry editLeague = new MenuEntry("Editar liga", () -> {

		int id = askForPositiveInt("Introduzca el ID de la liga a editar");
		int index = id - 1;

		if (index < 0 || index >= this.leagues.size()) {

			System.out.println("El ID es incorrecto");

		} else {

			LeagueCompetition league = this.leagues.get(index);

			String leagueName = askForString("Introduzca el nuevo nombre de la liga");
			league.setName(leagueName);

			LeagueCompetitionRules ruleSet = league.getRuleSet();

			if (ruleSet.isModifiable()) {

				int pointsPerWin = askForPositiveInt("Introduzca el número de puntos otorgados por victoria");
				int pointsPerDraw = askForPositiveInt("Introduzca el número de puntos otorgados por empate");
				int pointsPerLoss = askForPositiveInt("Introduzca el número de puntos otorgados por derrota");

				ruleSet.setPointsPerWin(pointsPerWin);
				ruleSet.setPointsPerDraw(pointsPerDraw);
				ruleSet.setPointsPerLoss(pointsPerLoss);

			} else {

				System.out.println("La liga ya ha empezado, y por tanto no se pueden modificar las reglas.");

			}

		}

	});

	private MenuEntry deleteLeague = new MenuEntry("Borrar liga", () -> {

		int id = askForPositiveInt("Introduzca el ID de la liga a borrar");
		int index = id - 1;

		if (index < 0 || index >= this.leagues.size()) {

			System.out.println("El ID es incorrecto");

		} else {

			this.leagues.remove(index);

		}

	});

	private MenuEntry sortByName = new MenuEntry("Ordenar ligas por nombre", () -> {

		this.leagues.sort(Comparator.comparing(a -> a.getName()));

	});

	private MenuEntry sortByNumOfCompetitors = new MenuEntry("Ordenar ligas por nº de equipos", () -> {

		this.leagues.sort((a, b) -> b.getCompetitors().size() - a.getCompetitors().size());

	});

	private MenuEntry addTeam = new MenuEntry("Añadir equipo a una liga", () -> {

		int id = askForPositiveInt("Introduzca el ID de la liga a la cual añadir un equipo");
		int index = id - 1;

		if (index < 0 || index >= this.leagues.size()) {

			System.out.println("El ID es incorrecto");

		} else {

			Competitor competitor = askForTeam("Introduzca el nombre del equipo");
			this.leagues.get(index).addCompetitor(competitor);

		}

	});

	private MenuEntry launchUI = new MenuEntry("Lanzar panel de control de una liga", () -> {

		int id = askForPositiveInt("Introduzca el ID de la liga sobre la cual lanzar el panel de control");
		int index = id - 1;

		if (index < 0 || index >= this.leagues.size()) {

			System.out.println("El ID es incorrecto");

		} else {

			LeagueCompetition league = this.leagues.get(index);

			if (league.getCompetitors().size() % 2 == 0 && league.getCompetitors().size() > 0) {

				try {

					if (league.getRounds() == null || league.getRounds().size() == 0) {

						league.generateRounds();

					}

					new LeagueCompetitionView(league);

				} catch (BadInputException e) {

					System.out.println("¡Ha ocurrido un error al generar las rondas de la liga!");
					e.printStackTrace();

				}

			} else {

				System.out.println("Esa liga debe tener un número par de competidores.");

			}

		}

	});

	public VersusApp() {

		// Si existe una lista de competiciones en el disco, la leemos
		// De lo contrario, creamos una lista vacía

		try {

			this.leagues = getSavedLeagues().orElseGet(ArrayList::new);

			// Nota: el código fuente del paquete io.github.mellamopablo.simplecliframework se encuentra en:
			// https://github.com/MeLlamoPablo/java-ejercicios-simples/tree/master/simple-cli-framework
			App app = new App("Versus", Language.SPANISH);

			app.add(getLeagues);
			app.add(addLeague);
			app.add(editLeague);
			app.add(deleteLeague);
			app.add(sortByName);
			app.add(sortByNumOfCompetitors);
			app.add(addTeam);
			app.add(launchUI);

			app.start();

			saveLeagues(this.leagues);

			// Si el usuario ha abierto la interfaz gráfica, el proceso no se cierra solo, así que forzamos la salida.
			System.exit(0);

		} catch (IOException | ClassNotFoundException e) {

			System.out.println("Lo sentimos, ha ocurrido un error inesperado...");
			e.printStackTrace();

		}

	}

	@SuppressWarnings("unchecked")
	private static Optional<List<LeagueCompetition>> getSavedLeagues() throws IOException, ClassNotFoundException {

		try {

			FileInputStream fis = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);

			List<LeagueCompetition> leagues = (List<LeagueCompetition>) ois.readObject();

			ois.close();
			fis.close();

			return Optional.of(leagues);

		} catch (FileNotFoundException e) {

			return Optional.empty();

		}

	}

	private static void saveLeagues(List<LeagueCompetition> leagues) throws IOException {

		FileOutputStream fos = new FileOutputStream(FILE_NAME);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(leagues);

		oos.close();
		fos.close();

	}

	private Competitor askForTeam(String message) {

		return new Competitor(askForString(message));

	}

	private boolean askForBoolean(String message) {

		return askForString(message + " (Y/N)").trim().toLowerCase().charAt(0) == 'y';

	}

	private int askForPositiveInt(String message) {

		int input = askForInt(message);

		if (input >= 0) {

			return input;

		} else {

			System.out.println("Introduzca un número positivo o cero.");
			return askForPositiveInt(message);

		}

	}

	private int askForInt(String message) {

		try {

			return Integer.parseInt(askForString(message));

		} catch (NumberFormatException e) {

			System.out.println("La entrada no es válida.");
			return askForInt(message);

		}

	}

	private String askForString(String message) {

		Scanner input = new Scanner(System.in);

		try {

		    System.out.print(message + ": ");
		    return input.nextLine();

		} catch (InputMismatchException e) {

		    System.out.println("La entrada no es válida.");
		    return askForString(message);

		}


	}

}
