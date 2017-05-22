package com.versus.views;

import javax.swing.*;

public class MenuElement {

	private String text;
	private JPanel content;

	String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}

	JPanel getContent() {
		return content;
	}

	private void setContent(JPanel content) {
		this.content = content;
	}

	public MenuElement(String text, JPanel content) {
		this.setText(text);
		this.setContent(content);
	}

}
