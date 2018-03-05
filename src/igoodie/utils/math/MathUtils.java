package igoodie.utils.math;

import java.util.Random;

public final class MathUtils {
	
	private static final Random random = new Random();
	
	/* Float field replicates */
	public static final float PI = (float) Math.PI;
	public static final float E = (float) Math.E;
	
	public static int randomInt() {
		return random.nextInt();
	}
	
	public static int randomInt(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}

	public static float randomFloat() {
		return random.nextFloat();
	}
	
	public static float randomFloat(float min, float max) {
		return random.nextFloat() * (max-min) + min;
	}
	
	public static float lerp(float start, float stop, float amt) {
		return start + (stop-start) * amt;
	}
	
	/* Float wrappers */
	public static float cos(float x) {
		return (float) Math.cos(x);
	}
	
	public static float sin(float x) {
		return (float) Math.sin(x);
	}
	
	public static float tan(float x) {
		return (float) Math.tan(x);
	}
	
	public static float atan2(float y, float x) {
		return (float) Math.atan2(y, x);
	}
	
	public static float sqrt(float x) {
		return (float) Math.sqrt(x);
	}
}
