package igoodie.utils.structures;

import igoodie.utils.math.Vectorf;

public class Entity {

	public Vectorf position;
	
	public Entity() {}
	
	public Entity(float x, float y) {
		position = new Vectorf(x, y);
	}
}
