package me.alguien.prueba;

import me.alguien.prueba.game.Engine;
import me.alguien.prueba.gui.GUI;

import java.awt.*;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			GUI ex = new GUI(500, 500);
			Engine.INSTANCE.setup(ex);
			ex.setVisible(true);
		});
	}
}
