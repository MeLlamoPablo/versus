package com.versus.views.support;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class NavBar extends JPanel {

	public NavBar(List<NavBarElement> elements) {
		elements = elements;

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		elements.stream().map(element -> {

			JButton button = new JButton(element.getText());
			button.addActionListener(event -> element.getCallback().onClick());
			return button;

		}).forEach(this::add);

		this.setVisible(true);
	}

}
