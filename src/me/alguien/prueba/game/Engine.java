package me.alguien.prueba.game;

import me.alguien.prueba.game.manager.EntityManager;

import javax.swing.*;

public enum Engine {
	INSTANCE;

	private EntityManager entityManager;
	private JFrame window;

	public void setup(JFrame window) {
		this.window = window;
		entityManager = new EntityManager(window);

		new Thread(() -> {
			while (true) {
				window.repaint();
				try {
					Thread.sleep(1000 / 45);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
