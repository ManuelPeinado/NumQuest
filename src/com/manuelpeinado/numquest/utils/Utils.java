package com.manuelpeinado.numquest.utils;

import java.util.Random;

public class Utils {
	/**
	 * Generates a number in the interval [min, max)
	 */
	public static int randomInt(Random random, int min, int max) {
		return min + random.nextInt(max - min);
	}
}
