package igoodie.utils.io;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class NetUtils {
	
	/* Readers */
	/**
	 * Takes in a byte array and parses a short with first 2 elements of it
	 * @param buffer Byte array to be parsed
	 * @return Parsed short value
	 */
	public static short asShort(byte[] buffer) {
		return asShort(buffer, 0);
	}
	
	/**
	 * Takes in a byte array and parses a short with 2 elements of it starting from an offset
	 * @param buffer Byte array to be parsed
	 * @param offset Offset for initial byte to be parsed
	 * @return Parsed short value
	 */
	public static short asShort(byte[] buffer, int offset) {
		return (short) (
				  ((buffer[offset+0]) << 8) 
				| ((buffer[offset+1]) << 0)
		);
	}
	
	/**
	 * Takes in a byte array and parses an integer with first 4 elements of it
	 * @param buffer Byte array to be parsed
	 * @return Parsed integer value
	 */
	public static int asInt(byte[] buffer) {
		return asInt(buffer, 0);
	}
	
	/**
	 * Takes in a byte array and parses an integer with 4 elements of it starting from an offset
	 * @param buffer Byte array to be parsed
	 * @param offset Offset for initial byte to be parsed
	 * @return Parsed integer value
	 */
	public static int asInt(byte[] buffer, int offset) {
		return    ((buffer[offset+0]) << 24)
				| ((buffer[offset+1]) << 16) 
				| ((buffer[offset+2]) << 8) 
				| ((buffer[offset+3]) << 0);
	}
	
	/**
	 * Takes in a byte array and parses a long with first 8 elements of it
	 * @param buffer Byte array to be parsed
	 * @return Parsed long value
	 */
	public static long asLong(byte[] buffer) {
		return asLong(buffer, 0);
	}

	/**
	 * Takes in a byte array and parses a long with 8 elements of it starting from an offset
	 * @param buffer Byte array to be parsed
	 * @param offset Offset for initial byte to be parsed
	 * @return Parsed long value
	 */
	public static long asLong(byte[] buffer, int offset) {
		return    ((long)(buffer[offset+0]) << 56)
				| ((long)(buffer[offset+1]) << 48) 
				| ((long)(buffer[offset+2]) << 40) 
				| ((long)(buffer[offset+3]) << 32)
				| ((long)(buffer[offset+4]) << 24)
				| ((long)(buffer[offset+5]) << 16) 
				| ((long)(buffer[offset+6]) << 8) 
				| ((long)(buffer[offset+7]) << 0);
	}
	
	/**
	 * Takes in a byte array and parses a float with first 4 elements of it
	 * @param buffer Byte array to be parsed
	 * @return Parsed float value
	 */
	public static float asFloat(byte[] buffer) {
		return asFloat(buffer, 0);
	}

	/**
	 * Takes in a byte array and parses a float with 4 elements of it starting from an offset
	 * @param buffer Byte array to be parsed
	 * @param offset Offset for initial byte to be parsed
	 * @return Parsed float value
	 */
	public static float asFloat(byte[] buffer, int offset) {
		return Float.intBitsToFloat(
				  ((buffer[offset+0]) << 24)
				| ((buffer[offset+1]) << 16) 
				| ((buffer[offset+2]) << 8) 
				| ((buffer[offset+3]) << 0)
		);
	}
	
	/**
	 * Takes in a byte array and parses a double with first 8 elements of it
	 * @param buffer Byte array to be parsed
	 * @return Parsed double value
	 */
	public static double asDouble(byte[] buffer) {
		return asDouble(buffer, 0);
	}

	/**
	 * Takes in a byte array and parses a double with 8 elements of it starting from an offset
	 * @param buffer Byte array to be parsed
	 * @param offset Offset for initial byte to be parsed
	 * @return Parsed double value
	 */
	public static double asDouble(byte[] buffer, int offset) {
		return Double.longBitsToDouble(
				  ((long)(buffer[offset+0]) << 56)
				| ((long)(buffer[offset+1]) << 48) 
				| ((long)(buffer[offset+2] ) << 40) 
				| ((long)(buffer[offset+3]) << 32)
				| ((long)(buffer[offset+4]) << 24)
				| ((long)(buffer[offset+5]) << 16) 
				| ((long)(buffer[offset+6]) << 8) 
				| ((long)(buffer[offset+7]) << 0)
		);
	}

	/* Writers */
	/**
	 * Write bytes of given short value to given byte array with an offset.
	 * @param buffer Byte array to be overwritten
	 * @param offset Offset for initial written byte index
	 * @param num Number to write
	 */
	public static void writeBytes(byte[] buffer, int offset, short num){
		buffer[offset  ] = (byte) (num >> 8);
		buffer[offset+1] = (byte) (num >> 0);
	}
	
	/**
	 * Write bytes of given integer value to given byte array with an offset.
	 * @param buffer Byte array to be overwritten
	 * @param offset Offset for initial written byte index
	 * @param num Number to write
	 */
	public static void writeBytes(byte[] buffer, int offset, int num){
		buffer[offset  ] = (byte) (num >> 24);
		buffer[offset+1] = (byte) (num >> 16);
		buffer[offset+2] = (byte) (num >> 8);
		buffer[offset+3] = (byte) (num >> 0);
	}
	
	/**
	 * Write bytes of given long value to given byte array with an offset.
	 * @param buffer Byte array to be overwritten
	 * @param offset Offset for initial written byte index
	 * @param num Number to write
	 */
	public static void writeBytes(byte[] buffer, int offset, long num){
		buffer[offset  ] = (byte) (num >> 56);
		buffer[offset+1] = (byte) (num >> 48);
		buffer[offset+2] = (byte) (num >> 40);
		buffer[offset+3] = (byte) (num >> 32);
		buffer[offset+4] = (byte) (num >> 24);
		buffer[offset+5] = (byte) (num >> 16);
		buffer[offset+6] = (byte) (num >> 8);
		buffer[offset+7] = (byte) (num >> 0);
	}

	/**
	 * Write bytes of given float value to given byte array with an offset.
	 * @param buffer Byte array to be overwritten
	 * @param offset Offset for initial written byte index
	 * @param num Number to write
	 */
	public static void writeBytes(byte[] buffer, int offset, float num){
		int i = Float.floatToIntBits(num);
		buffer[offset  ] = (byte) (i >> 24);
		buffer[offset+1] = (byte) (i >> 16);
		buffer[offset+2] = (byte) (i >> 8);
		buffer[offset+3] = (byte) (i >> 0);
	}
	
	/**
	 * Write bytes of given double value to given byte array with an offset.
	 * @param buffer Byte array to be overwritten
	 * @param offset Offset for initial written byte index
	 * @param num Number to write
	 */
	public static void writeBytes(byte[] buffer, int offset, double num){
		long l = Double.doubleToLongBits(num);
		buffer[offset  ] = (byte) (l >> 56);
		buffer[offset+1] = (byte) (l >> 48);
		buffer[offset+2] = (byte) (l >> 40);
		buffer[offset+3] = (byte) (l >> 32);
		buffer[offset+4] = (byte) (l >> 24);
		buffer[offset+5] = (byte) (l >> 16);
		buffer[offset+6] = (byte) (l >> 8);
		buffer[offset+7] = (byte) (l >> 0);
	}
	
	/* Truncators */
	/**
	 * Truncates given string if it exceeds given maximum value.
	 * @param str String value to be truncated
	 * @param max Maximum character limit
	 * @return Truncated String value
	 */
	public static String truncateString(String str, int max) {
		if(str.length() <= max) return str;
		return str.substring(0, max); //Truncate rightmost chars
	}
	
	/**
	 * Truncates the digits of given long value to 4 byte (integer)
	 * @param num Long value to be truncated
	 * @return Truncated long value as integer
	 */
	public static int truncateToInt(long num) {
		String s1 = num + "";
		return Integer.parseInt(s1.substring(Math.max(s1.length() - 9, 0)));
		//Evil digit truncator from long to int.
		//Might be a little bit slow, but there is no way to do it as we know
	}
	
	/* Packet-evaluation Helpers */
	/**
	 * Returns either null or the <b><i>InetAddress</b></i> instance
	 * of given host string. One liner to evade try/catch surrounds.
	 * @param host Host string such as "localhost" or "x.x.x.x"
	 * @return InetAddress instance of given host string or null
	 */
	public static InetAddress getInternetAdress(String host) {
		try {
			return InetAddress.getByName(host);
		}
		catch(UnknownHostException e) {
			return null;
		}
	}

	/**
	 * Converts given byte array to hex string with the prefix "0x"
	 * and delimites indicies with "_"
	 * @param source Byte array to be converted
	 * @return Converted hex string
	 */
	public static String asHexString(byte[] source) {
		if(source.length == 0) return "";
		StringBuilder sb = new StringBuilder("0x");
		
		for(int i=0; i<source.length; i++) {
			sb.append(String.format("%02X", source[i]));
			if(i != source.length-1) sb.append("_");
		}
		
		return sb.toString().trim();
	}
	
	/**
	 * Converts given long to hex string with the prefix "0x"
	 * @param num Long to be converted
	 * @return Converted hex string
	 */
	public static String asHexString(long num) {
		String hex = String.format("%016X", num);
		StringBuilder sb = new StringBuilder("0x");
		
		for(int i=0; i<hex.length()-4; i+=4) {
			sb.append(hex.substring(i, i+4) + "_");
		}
		sb.append(hex.substring(12, 16));
		
		return sb.toString();
	}
	
	/**
	 * Converts given integer to hex string with the prefix "0x"
	 * @param num Integer to be converted
	 * @return Converted hex string
	 */
	public static String asHexString(int num) {
		String hex = String.format("%08X", num);
		StringBuilder sb = new StringBuilder("0x");
		
		for(int i=0; i<hex.length()-4; i+=4) {
			sb.append(hex.substring(i, i+4) + "_");
		}
		sb.append(hex.substring(4, 8));
		
		return sb.toString();
	}
	
	/**
	 * Converts given byte array to binary string with the prefix "0b"
	 * and delimites indicies with "_"
	 * @param source Byte array to be converted
	 * @return Converted binary string
	 */
	public static String asBinString(byte[] source) {
		if(source.length == 0) return "";
		StringBuilder sb = new StringBuilder("0b");
		
		for(int i=0; i<source.length; i++) {
			sb.append(String.format("%8s", Integer.toBinaryString(source[i])));
			if(i != source.length-1) sb.append("_");
		}
		
		return sb.toString().trim().replace(" ", "0");
	}
	
	/* Hacky-wack Methods */
	/**
	 * Checks if <i>s1</i> > <i>s2</i> in a wrap-around manner. 
	 * It isn't going to work for numbers such as <b><i>abs(s2-s1)>Short.MAX/2</i></b>. <br/>
	 * This method is designed to compare packet sequence numbers.
	 * @param s1 First number to be checked
	 * @param s2 Second number to be checked
	 * @return Returns true if s1 is sequentially greater than s2
	 * @see {@link #sequence_less_than(short, short)}
	 */
	public static boolean sequence_greater_than(short s1, short s2){
        return ( ( s1 > s2 ) && ( s1 - s2 <= 32768 ) ) || 
               ( ( s1 < s2 ) && ( s2 - s1  > 32768 ) );
    }
	
	/**
	 * Checks if <i>s1</i> < <i>s2</i> in a wrap-around manner. 
	 * It isn't going to work for numbers such as <b><i>abs(s2-s1)>Short.MAX/2</i></b>. <br/>
	 * This method is designed to compare packet sequence numbers.
	 * @param s1 First number to be checked
	 * @param s2 Second number to be checked
	 * @return Returns true if s1 is sequentially less than s2
	 * @see {@link #sequence_greater_than(short, short)}
	 */
	public static boolean sequence_less_than(short s1, short s2) {
		return !sequence_greater_than(s1, s2);
	}
	
	/**
	 * Increases given byte by 1 and wraps around if needed.
	 * This method is created because of the lack of increment operator's
	 * boundary protection on byte values.
	 * @param num Byte value to be increased by 1
	 * @return Incremented byte value
	 */
	public static byte byteIncrement(byte num) {
		if(num == Byte.MAX_VALUE) return Byte.MIN_VALUE;
		return (byte) (num + 1);
	}

	/**
	 * Increases given short by 1 and wraps around if needed.
	 * This method is created because of the lack of increment operator's
	 * boundary protection on short values.
	 * @param num Short value to be increased by 1
	 * @return Incremented short value
	 */
	public static short shortIncrement(short num) {
		if(num == Short.MAX_VALUE) return Short.MIN_VALUE;
		return (short) (num + 1);
	}
}
