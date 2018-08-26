package com.programmer.igoodie.utils.math;

public class Vectorf {
	
	public static Vectorf add(Vectorf v1, Vectorf v2) {
		return new Vectorf(v1.x+v2.x, v1.y+v2.y);
	}
	
	public static Vectorf add(Vectorf v1, float n) {
		return new Vectorf(v1.x+n, v1.y+n);
	}

	public static Vectorf sub(Vectorf v1, Vectorf v2) {
		return new Vectorf(v1.x-v2.x, v1.y-v2.y);
	}

	public static Vectorf sub(Vectorf v1, float x, float y) {
		return new Vectorf(v1.x-x, v1.y-y);
	}

	public static Vectorf sub(float x, float y, Vectorf v) {
		return new Vectorf(x-v.x, y-v.y);
	}

	public static Vectorf mult(Vectorf v1, float n) {
		return new Vectorf(v1.x*n, v1.y*n);
	}

	public static Vectorf cross(Vectorf v1, Vectorf v2) {
		return new Vectorf(v1.x*v2.x, v1.y*v2.y);
	}

	public static float dot(Vectorf v1, Vectorf v2) {
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}

	public static Vectorf div(Vectorf v1, float n) {
		return new Vectorf(v1.x/n, v1.y/n);
	}

	public static float dist(Vectorf v1, Vectorf v2) {
		return v1.dist(v2);
	}

	static public float angleBetween(Vectorf v1, Vectorf v2) {
		if (v1.x == 0 && v1.y == 0 && v1.z == 0) return 0.0f;
		if (v2.x == 0 && v2.y == 0 && v2.z == 0) return 0.0f;

		double dot = v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
		double v1mag = Math.sqrt(v1.x * v1.x + v1.y * v1.y + v1.z * v1.z);
		double v2mag = Math.sqrt(v2.x * v2.x + v2.y * v2.y + v2.z * v2.z);
		double amt = dot / (v1mag * v2mag);
		if (amt <= -1) {
			return MathUtils.PI;
		} else if (amt >= 1) {
			return 0;
		}
		return (float) Math.acos(amt);
	}

	/* Fields */
	public float x, y, z;

	/* Constructors */
	public Vectorf() {
		super();
	}

	public Vectorf(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vectorf(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/* Set & Get */
	public Vectorf set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vectorf set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vectorf set(Vectorf v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}

	public float[] asArray() {
		return new float[]{x, y, z};
	}

	public float[] toArray(float[] target) {
		if(target==null) return asArray();
		if(target.length == 2) {
			target[0] = x;
			target[1] = y;
		}
		if(target.length >= 3) {
			target[2] = z;
		}
		return target;
	}

	public Vectorf copy() {
		return new Vectorf(x, y, z);
	}
	
	/* Random 2D & 3D */
	public Vectorf randomize2D() {
		return normalizeWithAngle(Randomizer.randomFloat() * MathUtils.PI * 2);
	}

	public Vectorf randomize3D() {
		float angle = Randomizer.randomFloat() * MathUtils.PI * 2;
		float vz = Randomizer.randomFloat(-1, 1);
		float vx = MathUtils.sqrt(1-vz*vz) * MathUtils.cos(angle);
		float vy = MathUtils.sqrt(1-vz*vz) * MathUtils.sin(angle);
		return set(vx, vy, vz);
	}

	/* Mag & MagSq */
	public float mag() {
		return MathUtils.sqrt(x*x + y*y + z*z);
	}

	public float magSq() {
		return (x*x + y*y + z*z);
	}

	/* Add & Sub */
	public Vectorf add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vectorf add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vectorf add(Vectorf v) {
		return add(v.x, v.y, v.z);
	}

	public Vectorf sub(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vectorf sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vectorf sub(Vectorf v) {
		return sub(v.x, v.y, v.z);
	}

	/* Mult & Div */
	public Vectorf mult(float n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}

	public Vectorf div(float n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}

	/* Dist */
	public float dist(Vectorf v) {
		float dx = x - v.x;
		float dy = y - v.y;
		float dz = z - v.z;
		return MathUtils.sqrt(dx*dx + dy*dy + dz*dz);
	}

	public float dist(float x, float y) {
		float dx = this.x - x;
		float dy = this.y - y;
		return MathUtils.sqrt(dx*dx + dy*dy);
	}

	public float dist(float x, float y, float z) {
		float dx = this.x - x;
		float dy = this.y - y;
		float dz = this.z - z;
		return MathUtils.sqrt(dx*dx + dy*dy + dz*dz);
	}

	/* Dot */
	public float dot(Vectorf v) {
		return x*v.x + y*v.y + z*v.z;
	}

	public float dot(float x, float y, float z) {
		return this.x*x + this.y*y + this.z*z;
	}

	public float dot(float x, float y) {
		return this.x*x + this.y*y;
	}

	/* Cross */
	public Vectorf cross(Vectorf v) {
		return cross(v.x, v.y);
	}
	
	public Vectorf cross(float x, float y) {
		return cross(x, y, 0);
	}
	
	public Vectorf cross(float x, float y, float z) {
		float crossX = this.y * z - y * this.z;
		float crossY = this.z * x - z * this.x;
		float crossZ = this.x * y - x * this.y;
		
		return new Vectorf(crossX, crossY, crossZ);
	}

	/* Normalize & Limiters */
	public Vectorf normalize() {
		float mag = mag();
		if(mag!=0 && mag!=1) {
			div(mag);
		}
		return this;
	}

	public Vectorf limit(float max) {
		if(magSq() > max*max) {
			normalize();
			mult(max);
		}
		return this;
	}

	public Vectorf len(float len) {
		normalize();
		mult(len);
		return this;
	}

	/* From Angle & Angle related */
	public Vectorf normalizeWithAngle(float angle) {
		this.x = MathUtils.cos(angle);
		this.y = MathUtils.sin(angle);
		this.z = 0;
		return this;
	}

	public float headingAngle() {
		return MathUtils.atan2(y, x);
	}

	public Vectorf rotate(float angle) {
		float temp = x;
		x = x*MathUtils.cos(angle) - y*MathUtils.sin(angle);
		y = temp*MathUtils.sin(angle) + y*MathUtils.cos(angle);
		return this;
	}

	public Vectorf lerp(Vectorf v, float amt) {
		x = MathUtils.lerp(x, v.x, amt);
		y = MathUtils.lerp(y, v.y, amt);
		z = MathUtils.lerp(z, v.z, amt);
		return this;
	}

	/* Object Overrides */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(x + ", ");
		sb.append(y + ", ");
		sb.append(z + "} ");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vectorf) {
			final Vectorf v = (Vectorf) obj;
			return x==v.x && y==v.y && z==v.z;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + Float.floatToIntBits(x);
		result = 31 * result + Float.floatToIntBits(y);
		result = 31 * result + Float.floatToIntBits(z);
		return result;
	}

	/* Special Definitions */
	public String toCastedString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append((int)x + ", ");
		sb.append((int)y + ", ");
		sb.append((int)z + "} ");
		return sb.toString();
	}

	public String toCastedString2D() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append((int)x + ", ");
		sb.append((int)y + "} ");
		return sb.toString();
	}
}