package com.versus.views.support;

import com.versus.model.exceptions.NullInputException;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuUI extends JPanel {

	private NavBar navBar;
	private JPanel currentContent;

	private Optional<JPanel> getCurrentContent() {
		return Optional.ofNullable(currentContent);
	}

	private void setCurrentContent(JPanel nextContent) {

		this.getCurrentContent().ifPresent(content -> content.setVisible(false));

		nextContent.setBounds(
			0,
			(int) (this.getHeight() * 0.10),
			this.getWidth(),
			(int) (this.getHeight() * 0.95)
		);
		nextContent.setVisible(true);

		this.currentContent = nextContent;

	}

	public MenuUI(int width, int height, List<MenuElement> elements) {

		super();

		if (elements.size() == 0) {
			throw new NullInputException("Can't create a MenuUI with 0 elements!");
		}

		this.setSize(width, height);

		this.setLayout(null);

		this.navBar = new NavBar(
			elements
				.stream()
				.map(element -> new NavBarElement(
					element.getText(),
					() -> this.setCurrentContent(element.getContent())
				))
				.collect(Collectors.toList())
		);

		this.navBar.setBounds(0, 0, width, (int) (height * 0.05));
		this.navBar.setVisible(true);

		this.add(navBar);

		this.setCurrentContent(elements.get(0).getContent());
		elements.stream().map(MenuElement::getContent).forEach(this::add);

		this.setVisible(true);

	}

}
