package com.versus.views.support;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class JFrameX extends JFrame {

	private int width;
	private int height;
	protected Toolkit toolkit = Toolkit.getDefaultToolkit();

	@Override
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.updateSize();
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.updateSize();
	}

	public JFrameX(String title, int width, int height) {

		if (!"".equals(title)) {
			this.setTitle(title);
		}

		this.setWidth(width);
		this.setHeight(height);
		this.center();
		this.setResizable(false);

	}

	private void updateSize() {
		this.setSize(this.getWidth(), this.getHeight());
	}

	protected void center() {

		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (int) (screenDimensions.getWidth() / 2) - (this.getWidth() / 2);
		int y = (int) (screenDimensions.getHeight() / 2) - (this.getHeight() / 2);

		this.setLocation(x, y);

	}

}
