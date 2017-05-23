package com.versus.views.leagueCompetitionView;

import com.versus.model.LeagueCompetition;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.stream.Collectors;

class LeagueCompetitionViewStandings extends JPanel {

	private LeagueCompetition competition;
	private JTable table;

	LeagueCompetition getCompetition() {
		return competition;
	}

	private static String[] columnNames = {
		"Equipo",
		"Puntos",
		"Victorias",
		"Empates",
		"Derrotas",
		"Marcados a favor",
		"Marcados en contra"
	};

	void setCompetition(LeagueCompetition competition) {
		this.competition = competition;
	}

	LeagueCompetitionViewStandings(LeagueCompetition competition) {

		this.setCompetition(competition);

		this.setLayout(new BorderLayout());

		DefaultTableModel model = new DefaultTableModel(mapLeagueCompetitionToTableData(competition), columnNames) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		this.table = new JTable(model);
		this.table.setBounds(this.getX(), this.getX(), this.getWidth(), this.getHeight());

		JScrollPane panel = new JScrollPane(this.table);
		panel.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		panel.setVisible(true);

		this.add(panel);
		this.setVisible(true);

	}

	private static Object[][] mapLeagueCompetitionToTableData(LeagueCompetition competition) {

		return competition
			.getRanking()
			.stream()
			.map(competitor -> {

				Object[] row = new Object[columnNames.length];

				row[0] = competitor.getName();
				row[1] = competitor.getPoints();
				row[2] = competitor.getWins();
				row[3] = competitor.getDraws();
				row[4] = competitor.getLoses();
				row[5] = competitor.getScored();
				row[6] = competitor.getScoredAgainst();

				return row;
			})
			.toArray(Object[][]::new);

	}

	void refreshTableData() {

		table.setModel(new DefaultTableModel(mapLeagueCompetitionToTableData(competition), columnNames) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		});

	}

}
