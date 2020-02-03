package me.alguien.prueba.game.entity;

public interface IFollowable {
	void setFollowableEntity(AbstractEntity abstractEntity);

	AbstractEntity getFollowingEntity();
}
