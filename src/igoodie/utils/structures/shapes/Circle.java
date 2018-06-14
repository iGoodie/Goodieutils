package igoodie.utils.structures.shapes;

import igoodie.utils.math.MathUtils;
import igoodie.utils.structures.Entity;

public class Circle {

	public float x, y;
	public float radius;
	
	public Circle(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Circle(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public boolean containsPoint(float x, float y) {
		float dx = (this.x - x) * (this.x - x);
		float dy = (this.y - y) * (this.y - y);
		return (dx*dx + dy*dy) < this.radius * this.radius;
	}
	
	public boolean containsPoint(Entity entity) {
		return containsPoint(entity.position.x, entity.position.y);
	}
	
	//TODO : Check validity of the usage of clamp function
	public boolean intersects(float x, float y, float w, float h) {
		float dx = this.x - MathUtils.clamp(this.x, x, x+w);
		float dy = this.y - MathUtils.clamp(this.y, y, y+h);
		return (dx*dx + dy*dy) < this.radius * this.radius;
	}
	
	public boolean intersects(Rectangle rect) {
		return intersects(rect.x, rect.y, rect.w, rect.h);
	}
	
	public boolean intersects(float x, float y, float radius) {
		float dx = this.x - x;
		float dy = this.y - y;
		float dr = this.radius - radius;
		
		return dx*dx + dy*dy <= dr*dr;
	}
	
	public boolean intersects(Circle c) {
		return intersects(c.x, c.y, c.radius);
	}
}
