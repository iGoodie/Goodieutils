package com.programmer.igoodie.utils.structures;

import com.programmer.igoodie.utils.math.Vectorf;

public class Entity {

	public Vectorf position;
	
	public Entity() {}
	
	public Entity(float x, float y) {
		position = new Vectorf(x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Entity)) return false;
		
		Entity e2 = (Entity) obj;
		return this.position.equals(e2.position);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Entity[");
		sb.append("Pos:" + position);
		sb.append("]");
		return sb.toString();
	}
}
