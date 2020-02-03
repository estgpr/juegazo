package me.alguien.prueba.gui;

import me.alguien.prueba.game.Engine;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
	private void entityDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Engine.INSTANCE.getEntityManager().renderEntities(g2d);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		entityDrawing(g);
	}
}
