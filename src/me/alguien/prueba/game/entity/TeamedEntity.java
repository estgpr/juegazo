package me.alguien.prueba.game.entity;

public abstract class TeamedEntity extends AbstractEntity {
	public Team team;

	public TeamedEntity(String name) {
		super(name);
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public enum Team {
		RED, BLUE, UNKNOWN;
	}
}
