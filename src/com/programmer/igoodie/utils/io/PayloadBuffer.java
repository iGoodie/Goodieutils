package com.programmer.igoodie.utils.io;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class PayloadBuffer {

	private static final Charset UTF8 = Charset.forName("UTF-8");

	private int offset = 0;
	private ArrayList<Byte> buffer = new ArrayList<>();

	public PayloadBuffer() {}

	public PayloadBuffer(byte[] initialData) {
		for(byte b : initialData) buffer.add(b);
	}

	/* Writers */
	public void append(byte num) {
		buffer.add(num);
	}

	public void append(short num) {
		buffer.add((byte) (num >>> 8));
		buffer.add((byte) (num));
	}

	public void append(int num) {
		buffer.add((byte) (num >>> 24));
		buffer.add((byte) (num >>> 16));
		buffer.add((byte) (num >>> 8));
		buffer.add((byte) (num));
	}

	public void append(long num) {
		buffer.add((byte) (num >>> 56));
		buffer.add((byte) (num >>> 48));
		buffer.add((byte) (num >>> 40));
		buffer.add((byte) (num >>> 32));
		buffer.add((byte) (num >>> 24));
		buffer.add((byte) (num >>> 16));
		buffer.add((byte) (num >>> 8));
		buffer.add((byte) (num));
	}

	public void append(float num) {
		int i = Float.floatToIntBits(num);
		buffer.add((byte) (i >>> 24));
		buffer.add((byte) (i >>> 16));
		buffer.add((byte) (i >>> 8));
		buffer.add((byte) (i));
	}

	public void append(double num) {
		long l = Double.doubleToLongBits(num);
		buffer.add((byte) (l >>> 56));
		buffer.add((byte) (l >>> 48));
		buffer.add((byte) (l >>> 40));
		buffer.add((byte) (l >>> 32));
		buffer.add((byte) (l >>> 24));
		buffer.add((byte) (l >>> 16));
		buffer.add((byte) (l >>> 8));
		buffer.add((byte) (l));
	}

	public void appendWithLen(String str) {
		byte[] strBytes = str.getBytes(UTF8);

		append(strBytes.length);
		for(byte b : strBytes) append(b);
	}

	public void append(byte[] buffer) {
		for(byte b : buffer) this.buffer.add(b);
	}

	/* Readers */
	public byte read() {
		byte b = buffer.get(offset);
		offset += 1;
		return b;
	}

	public short readShort() {
		short s = (short) (buffer.get(offset) << 8
				| (buffer.get(offset+1) & 0xFF));
		offset += 2;
		return s;
	}

	public int readInt() {
		int i = buffer.get(offset) 				<< 24 
				| (buffer.get(offset+1) & 0xFF) << 16 
				| (buffer.get(offset+2) & 0xFF) << 8 
				| (buffer.get(offset+3) & 0xFF);
		offset += 4;
		return i;
	}

	public long readLong() {
		long l = (long)buffer.get(offset)			  << 56
				| (long)(buffer.get(offset+1) & 0xFF) << 48
				| (long)(buffer.get(offset+2) & 0xFF) << 40
				| (long)(buffer.get(offset+3) & 0xFF) << 32
				| (long)(buffer.get(offset+4) & 0xFF) << 24
				| (long)(buffer.get(offset+5) & 0xFF) << 16
				| (long)(buffer.get(offset+6) & 0xFF) << 8
				| (long)(buffer.get(offset+7) & 0xFF);
		offset += 8;
		return l;
	}

	public float readFloat() {
		float f = Float.intBitsToFloat(
				(buffer.get(offset) & 0xFF) 	<< 24
				| (buffer.get(offset+1) & 0xFF) << 16
				| (buffer.get(offset+2) & 0xFF) <<  8
				| (buffer.get(offset+3) & 0xFF));
		offset += 4;
		return f;
	}

	public double readDouble() {
		double d = Double.longBitsToDouble(
				(long)buffer.get(offset) 			  << 56
				| (long)(buffer.get(offset+1) & 0xFF) << 48
				| (long)(buffer.get(offset+2) & 0xFF) << 40
				| (long)(buffer.get(offset+3) & 0xFF) << 32
				| (long)(buffer.get(offset+4) & 0xFF) << 24
				| (long)(buffer.get(offset+5) & 0xFF) << 16
				| (long)(buffer.get(offset+6) & 0xFF) << 8
				| (long)(buffer.get(offset+7) & 0xFF));
		offset += 8;
		return d;
	}

	public String readString() {
		int len = readInt();

		byte[] strbytes = new byte[len];
		for(int i=0; i<len; i++) {
			strbytes[i] = buffer.get(offset+i);
		}

		offset += len;
		return new String(strbytes, UTF8);
	}

	/* Methods */
	public void reposition() {
		offset = 0;
	}

	public void position(int offset) {
		this.offset = offset;
	}

	public byte[] compose() {
		byte[] composed = new byte[buffer.size()];

		for (int i=0; i<buffer.size(); i++) {
			composed[i] = buffer.get(i);
		}

		return composed;
	}
}
