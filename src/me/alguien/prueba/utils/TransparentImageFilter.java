package me.alguien.prueba.utils;

import java.awt.*;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;

public class TransparentImageFilter {
	public static final ImageFilter filter;

	static {
		filter = new RGBImageFilter() {
			int transparentColor = Color.white.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == transparentColor) {
					return 0x00FFFFFF & rgb;
				} else {
					return rgb;
				}
			}
		};
	}
}
