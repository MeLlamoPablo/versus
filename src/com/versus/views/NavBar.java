package com.versus.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class NavBar extends JPanel {

	private List<NavBarElement> elements;

	public NavBar(List<NavBarElement> elements) {
		this.elements = elements;

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.elements.stream().map(element -> {

			JButton button = new JButton(element.getText());
			button.addActionListener(event -> element.getCallback().onClick());
			return button;

		}).forEach(this::add);

		this.setVisible(true);
	}

}
