package igoodie.utils.structures.shapes;

import igoodie.utils.structures.Entity;

public class Rectangle {

	public float x, y;
	public float w, h;
	
	public Rectangle(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Rectangle(float x, float y) {
		this(x, y, 0, 0);
	}
	
	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public boolean containsPoint(float x, float y) {
		return this.x < x && x < this.x + this.w
				&& this.y < y && y < this.y + this.h;
	}
	
	public boolean containsPoint(Entity entity) {
		return containsPoint(entity.position.x, entity.position.y);
	}

	public boolean intersects(float x, float y, float w, float h) {
		return (this.x + this.w >= x) && (x + w >= this.x)
				&& (this.y + this.h >= y) && (y + h >= this.y);
	}
	
	public boolean intersects(Rectangle rect) {
		return intersects(rect.x, rect.y, rect.w, rect.h);
	}
	
	@Override
	public String toString() {
		return "[x:" + x + ", y:" + y + ", w:" + w + ", h:" + h + "]";
	}
}
