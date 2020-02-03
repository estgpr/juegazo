package me.alguien.prueba.gui;

import me.alguien.prueba.game.Engine;
import me.alguien.prueba.game.entity.impl.BichoEntity;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame implements MouseListener, MouseMotionListener {
	private MouseEvent currentMouse;

	private final GamePanel surface;

	public GUI(int width, int height) {
		this.add(surface = new GamePanel());
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.setTitle("hola");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Thread(() -> {
			while (true) {
				if (currentMouse != null) {
					Engine.INSTANCE.getEntityManager().updateEntities(currentMouse);
				}
				try {
					Thread.sleep(1000 / 45);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Engine.INSTANCE.getEntityManager().addEntity(new BichoEntity(e.getX(), e.getY()));

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		currentMouse = e;
	}
}
