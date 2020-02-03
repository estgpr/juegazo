package me.alguien.prueba.game.entity;

import me.alguien.prueba.utils.Timer;

public abstract class AbstractEntity implements IRenderable {
	public Timer timer = new Timer();
	public int posX, posY;
	public String name;

	public float health;
	public float damage;

	public AbstractEntity(String name) {
		this.name = name;
	}
}
