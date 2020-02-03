package me.alguien.prueba.game.entity.impl;

import me.alguien.prueba.game.entity.AbstractEntity;
import me.alguien.prueba.game.entity.IFollowable;
import me.alguien.prueba.game.entity.TeamedEntity;
import me.alguien.prueba.utils.TransparentImageFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.util.concurrent.ThreadLocalRandom;

public class BichoEntity extends TeamedEntity implements IFollowable {
	private AbstractEntity followingEntity;

	public boolean isAttacking;

	public BichoEntity(int posX, int posY) {
		super("bicho");

		this.health = 100;
		this.damage = 1;
		this.posX = posX;
		this.posY = posY;

		setTeam(Team.values()[ThreadLocalRandom.current().nextInt(Team.values().length - 1)]);
	}

	public static final ImageIcon BICHO_SPRITE = new ImageIcon("bicho.png");

	private static ImageProducer filteredImgProd = new FilteredImageSource(BICHO_SPRITE.getImage().getSource(), TransparentImageFilter.filter);

	private static Image transparentImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(team == Team.BLUE ? Color.BLUE : team == Team.RED ? Color.RED : Color.GRAY);
		g2d.drawString(this.name + " " + this.health, posX, posY);
		//g2d.fillRect(posX, posY, 15,  15);
		BufferedImage texture = toBufferedImage(transparentImg);
		int alpha = 0;
		if (health > 95) {
			alpha = 120;
		} else if (health > 70) {
			alpha = 80;
		} else if (health > 40) {
			alpha = 40;
		}
		if (texture != null) {
			g2d.drawImage(team == Team.BLUE ? dye(texture, new Color(0, 0, 255, alpha)) : team == Team.RED ? dye(texture, new Color(255, 0, 0, alpha)) : dye(texture, new Color(57, 57, 57, 120)), posX, posY, null);
		}
	}

	public static void rebuildTextures(ImageIcon toChange) {
		filteredImgProd = new FilteredImageSource(toChange.getImage().getSource(), TransparentImageFilter.filter);
		transparentImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
	}

	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		if (img.getWidth(null) == -1) {
			return null;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	private static BufferedImage dye(BufferedImage image, Color color) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage dyed = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dyed.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.setColor(color);
		g.fillRect(0, 0, w, h);
		g.dispose();
		return dyed;
	}

	@Override
	public void setFollowableEntity(AbstractEntity abstractEntity) {
		followingEntity = abstractEntity;
	}

	@Override
	public AbstractEntity getFollowingEntity() {
		return followingEntity;
	}
}
