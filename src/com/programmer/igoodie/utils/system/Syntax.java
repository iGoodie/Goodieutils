package com.programmer.igoodie.utils.system;

public final class Syntax {

	/**
	 * Equivalent of <b>!!</b> operation in Javascript
	 */
	public static boolean truthy(Object o) {
		if (o == null)
			return false;

		if (o instanceof Boolean)
			return (boolean) o;

		if (o instanceof String)
			return !((String) o).isEmpty();

		if (o instanceof Byte)
			return !o.equals((byte) 0);

		if (o instanceof Short)
			return !o.equals((short) 0);

		if (o instanceof Float)
			return !o.equals(0.0f) && !o.equals(Float.NaN);

		if (o instanceof Double)
			return !o.equals(0.0) && !o.equals(Double.NaN);

		return true;
	}

	/**
	 * Equivalent of <b>!</b> operation in Javascript
	 */
	public static boolean falsey(Object o) {
		return !truthy(o);
	}
	
	/**
	 * Equivalent of <b>IN</b> operation in SQL
	 * @param o Object to be searched
	 * @param things Objects to be iterated
	 * @return Whether first object is in or not
	 */
	public static boolean in(Object o, Object...things) {
		for(Object thing : things) {
			if(o.equals(thing))
				return true;
		}
		
		return false;
	}

}
