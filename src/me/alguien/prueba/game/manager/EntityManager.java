package me.alguien.prueba.game.manager;

import me.alguien.prueba.game.entity.AbstractEntity;
import me.alguien.prueba.game.entity.IFollowable;
import me.alguien.prueba.game.entity.TeamedEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EntityManager {
	private List<AbstractEntity> entities = new CopyOnWriteArrayList<>();
	private JFrame frame;

	public EntityManager(JFrame jFrame) {
		this.frame = jFrame;
	}

	public void addEntity(AbstractEntity entity) {
		entities.add(entity);
	}

	public void updateEntities(MouseEvent mouseEvent) {
		for (AbstractEntity entity : entities) {
			if (entity instanceof IFollowable) {
				if (entity.health <= 0) {
					entities.remove(entity);
					continue;
				}

				IFollowable followable = (IFollowable) entity;
				if (followable.getFollowingEntity() != null) {

					if (!entities.contains(followable.getFollowingEntity()) || followable.getFollowingEntity().health <= 0) {
						followable.setFollowableEntity(null);
						continue;
					}

					int diffX = (entity.posX - followable.getFollowingEntity().posX);
					int diffY = (entity.posY - followable.getFollowingEntity().posY);

					entity.posX -= diffX / 10;
					entity.posX += ThreadLocalRandom.current().nextInt(-2, 3);
					entity.posY -= diffY / 10;
					entity.posY += ThreadLocalRandom.current().nextInt(-2, 3);

					if (entity.timer.check(250) && Math.abs(diffX) < 25 && Math.abs(diffY) < 25) {
//						if (entity instanceof BichoEntity) {
//							BichoEntity.rebuildTextures(BichoEntity.BICHO_ATTACK_SPRITE);
//						}

						followable.getFollowingEntity().health -= entity.damage + Math.random() * 2;
						entity.timer.reset();
					}
				} else {
					for (AbstractEntity entity1 : entities) {
						if (entity instanceof TeamedEntity && entity1 instanceof TeamedEntity) {
							if (entity1.health > 0 && ((TeamedEntity) entity).team != ((TeamedEntity) entity1).team) {
								((IFollowable) entity).setFollowableEntity(entity1);
								return;
							}
						}
					}

					int diffX = (entity.posX - ThreadLocalRandom.current().nextInt(50));
					int diffY = (entity.posY - ThreadLocalRandom.current().nextInt(50));

					entity.posX -= diffX / 10;
					entity.posY -= diffY / 10;

				}
			}
//			int diffX = (entity.posX - mouseEvent.getX());
//			int diffY = (entity.posY - mouseEvent.getY());
//
//			entity.posX -= diffX / 5;
//			entity.posX += Math.random() * 25;
//
//			entity.posY -= diffY /  5;
//			entity.posY += Math.random() * 25;
		}
	}

	public void renderEntities(Graphics2D graphics2D) {
		for (AbstractEntity entity : entities) {
			entity.draw(graphics2D);
		}
	}
}
