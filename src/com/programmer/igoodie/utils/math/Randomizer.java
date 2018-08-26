package com.programmer.igoodie.utils.math;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

	private static final Random RANDOM = new Random();
	
	private static final char[] ALPHABET = ("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()).toCharArray();
	
	public static String randomString(int length) {
		String word = "";
		for(int i=0; i<length; i++) {
			word += randomChar();
		}
		return word;
	}

	public static char randomChar() {
		return ALPHABET[RANDOM.nextInt(ALPHABET.length)];
	}
	
	/**
	 * Replace random seed of the randomizer with given one
	 * @param seed New random seed
	 */
	public static void randomSeed(long seed) {
		RANDOM.setSeed(seed);
	}
	
	/**
	 * Pseudo-randomize random seed of the randomizer
	 */
	public static void randomSeed() {
		RANDOM.setSeed(randomBoolean() ? System.currentTimeMillis() : RANDOM.nextInt());
	}
	
	public static boolean randomBoolean() {
		return RANDOM.nextBoolean();
	}
	
	/**
	 * Generates and returns a random int between {@link Short.MIN_VALUE} and {@link Short.MAX_VALUE}
	 * @return A random short value
	 */
	public static short randomShort() {
		return (short) RANDOM.nextInt(Short.MAX_VALUE + 1);
	}
	
	/**
	 * Generates and returns a random int between {@link Integer.MIN_VALUE} and {@link Integer.MAX_VALUE}
	 * @return A random integer value
	 */
	public static int randomInt() {
		return RANDOM.nextInt();
	}
	
	/**
	 * Generates and returns a random int between [<b>min</b> and <b>max</b>]
	 * @param min Minimum boundary for rng
	 * @param max Maximum boundary for rng
	 * @return A random integer value
	 */
	public static int randomInt(int min, int max) {
		return RANDOM.nextInt(max - min + 1) + min;
	}

	/**
	 * Generates and returns a random long using {@link Random#nextLong()}
	 * @return A random long value
	 */
	public static long randomLong() {
		return RANDOM.nextLong();
	}
	
	/**
	 * Generates and returns a random long between [<b>min</b> and <b>max</b>]
	 * using {@link ThreadLocalRandom#nextLong(long, long)}
	 * @param min Minimum boundary for rng
	 * @param max Maximum boundary for rng
	 * @return A random integer value
	 */
	public static long randomLong(long min, long max) {
		return ThreadLocalRandom.current().nextLong(min, max);
	}
	
	/**
	 * Generates and returns a random float between [0.0f and 1.0f)
	 * @return
	 */
	public static float randomFloat() {
		return RANDOM.nextFloat();
	}
	
	/**
	 * Generates and returns a random float between [<b>min</b> and <b>max</b>]
	 * @param min Minimum boundary for rng
	 * @param max Maximum boundary for rng
	 * @return A random float value
	 */
	public static float randomFloat(float min, float max) {
		return RANDOM.nextFloat() * (max-min) + min;
	}

	/**
	 * Generates and returns a random double between [0.0d and 1.0d)
	 * @return
	 */
	public static double randomDouble() {
		return RANDOM.nextDouble();
	}
	
	/**
	 * Generates and returns a random double between [<b>min</b> and <b>max</b>]
	 * @param min Minimum boundary for rng
	 * @param max Maximum boundary for rng
	 * @return A random double value
	 */
	public static double randomDouble(double min, double max) {
		return RANDOM.nextDouble() * (max-min) + min;
	}
	
}
