package com.versus.views;

class NavBarElement {

	private String text;
	private NavBarElementClickCallback callback;

	String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}

	NavBarElementClickCallback getCallback() {
		return callback;
	}

	private void setCallback(NavBarElementClickCallback callback) {
		this.callback = callback;
	}

	public NavBarElement(String text, NavBarElementClickCallback callback) {
		this.setText(text);
		this.setCallback(callback);
	}

	interface NavBarElementClickCallback {
		void onClick();
	}

}
