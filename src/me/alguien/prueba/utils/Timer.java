package me.alguien.prueba.utils;

public class Timer {
	private long currentMillis;

	public Timer() {
		currentMillis = System.currentTimeMillis();
	}

	public boolean check(int toWaitMs) {
		return System.currentTimeMillis() >= currentMillis + toWaitMs;
	}

	public void reset() {
		currentMillis = System.currentTimeMillis();
	}
}
