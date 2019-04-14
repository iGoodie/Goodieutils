package com.programmer.igoodie.utils.math;

public final class MathUtils {
	
	/* Float field replicates */
	/**
	 * Float PI constant of {@link Math.PI}
	 */
	public static final float PI = (float) Math.PI;
	
	/**
	 * Float E constant of {@link Math.E}
	 */
	public static final float E = (float) Math.E;
	
	/**
	 * The linear interpolation between <b>start</b> and <b>stop</b>
	 * with an amount of <b>amt</b>. <br/>
	 * <b>amt = 0.0f</b> returns <b>start</b> <br/>
	 * <b>amt = 0.5f</b> returns <b>(start+stop)/2</b> <br/>
	 * <b>amt = 1.0f</b> returns <b>stop</b>
	 * @param start Starting point of the interpolation
	 * @param stop Stoping point of the interpolation
	 * @param amt Ratio of interpolation
	 * @return Linear interpolation from start to stop at given amt
	 */
	public static float lerp(float start, float stop, float amt) {
		return start + (stop-start) * amt;
	}
	
	/**
	 * Clamps given value between <b>min</b> and <b>max</b>
	 * @param value Value to be clamped
	 * @param min Minimum boundary
	 * @param max Maximum boundary
	 * @return Clamped value
	 */
	public static int clamp(int value, int min, int max) {
		if(value < min) return min;
		if(value > max) return max;
		return value;
	}
	
	/**
	 * Clamps given value between <b>min</b> and <b>max</b>
	 * @param value Value to be clamped
	 * @param min Minimum boundary
	 * @param max Maximum boundary
	 * @return Clamped value
	 */
	public static float clamp(float value, float min, float max) {
		if(value < min) return min;
		if(value > max) return max;
		return value;
	}
	
	/**
	 * Truncates floating point of a float according to given parameters
	 * @param num Float value to be truncated
	 * @param resolution Number of digits to keep as floating point
	 * @return Truncated float value
	 */
	public static float resolveError(float num, float resolution) {
		float f10 = (float) Math.pow(10, resolution);
		int i10 = (int) f10;
		return (int)(num*i10)/f10;
	}
	
	/**
	 * Calculates a^b where a and b are integers faster than native.
	 * @param a Base
	 * @param b Exponent
	 * @return Returns (a^b)
	 */
	public static int fastIntPow(int a, int b) {
		int res = 1;
		while(b>0) {
			if((b&1) == 1) {
				res *= a;
			}
			b >>= 1;
			a *= a;
		}
		return res;
	}
	
	public static int gcd(int a, int b) {
		while(a!=b) {
			if(a>b)
				a -= b;
			else
				b -= a;
		}
		return a;
	}

	public static int lcm(int a, int b) {
		return a*(b/gcd(a, b));
	}
	
	public static int positiveMod(int a, int b) {
		int mod = a % b;
		if(mod<0) mod += b;
		return mod;
	}
	
	/* Int wrappers */
	/**
	 * Integer wrapper for {@link Math.signum}
	 */
	public static int signum(int num) {
		if(num == 0) return 0;
		return num < 0 ? -1 : 1;
	}
	
	/* Float wrappers */
	/**
	 * Float wrapper for {@link Math.cos}
	 */
	public static float cos(float x) {
		return (float) Math.cos(x);
	}
	
	/**
	 * Float wrapper for {@link Math.sin}
	 */
	public static float sin(float x) {
		return (float) Math.sin(x);
	}
	
	/**
	 * Float wrapper for {@link Math.tan}
	 */
	public static float tan(float x) {
		return (float) Math.tan(x);
	}
	
	/**
	 * Float wrapper for {@link Math.atan2}
	 */
	public static float atan2(float y, float x) {
		return (float) Math.atan2(y, x);
	}
	
	/**
	 * Float wrapper for {@link Math.sqrt}
	 */
	public static float sqrt(float x) {
		return (float) Math.sqrt(x);
	}
}
