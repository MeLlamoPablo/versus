package com.versus.controller;

import com.versus.model.Competitor;
import com.versus.model.LeagueCompetition;
import com.versus.model.LeagueCompetitionRules;
import com.versus.model.exceptions.BadInputException;
import com.versus.views.leagueCompetitionView.LeagueCompetitionView;
import io.github.mellamopablo.simplecliframework.App;
import io.github.mellamopablo.simplecliframework.Language;
import io.github.mellamopablo.simplecliframework.MenuEntry;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings({"SameParameterValue", "FieldCanBeLocal"})
public class VersusApp {

	private List<Competitor> teams = new ArrayList<>();

	private LeagueCompetition league;

	private MenuEntry addTeam = new MenuEntry("Añadir equipo", () -> {

		Competitor competitor = askForTeam("Introduzca el nombre del equipo");
		teams.add(competitor);

	});

	private MenuEntry launchUI = new MenuEntry("Editar liga", () -> {

		String leagueName = askForString("Introduzca el nombre de la liga");
		boolean twoLegs = askForBoolean("¿Tiene la liga ida y vuelta?");
		int pointsPerWin = askForPositiveInt("Introduzca el número de puntos otorgados por victoria");
		int pointsPerDraw = askForPositiveInt("Introduzca el número de puntos otorgados por empate");
		int pointsPerLoss = askForPositiveInt("Introduzca el número de puntos otorgados por derrota");

		league = new LeagueCompetition(leagueName, twoLegs, new LeagueCompetitionRules(
			pointsPerWin, pointsPerDraw, pointsPerLoss
		));

		teams.forEach(league::addCompetitor);

		try {

			league.generateRounds();

			new LeagueCompetitionView(league);

		} catch (BadInputException e) {
			System.out.println("¡Ha ocurrido un error al generar las rondas de la liga!");
			e.printStackTrace();
		}

	});

	public VersusApp() {

		// Nota: el código fuente del paquete io.github.mellamopablo.simplecliframework se encuentra en:
		// https://github.com/MeLlamoPablo/java-ejercicios-simples/tree/master/simple-cli-framework
		App app = new App("Versus", Language.SPANISH);

		app.add(addTeam);
		app.add(launchUI);

		app.start();

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
