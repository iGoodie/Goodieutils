package igoodie.utils.structures.spatial;

import java.util.ArrayList;

import igoodie.utils.structures.Entity;
import igoodie.utils.structures.shapes.Circle;
import igoodie.utils.structures.shapes.Rectangle;

public class QuadTree<T extends Entity> {

	public static final int STD_CAPACITY = 4;
	
	public ArrayList<T> entities = new ArrayList<>();
	
	public QuadTree<T> northwest, northeast;
	public QuadTree<T> southwest, southeast;
	
	public Rectangle boundary;
	
	public QuadTree(float x, float y, float w, float h) {
		boundary = new Rectangle(x, y, w, h);
	}
	
	public boolean insert(T entity) {
		if(!boundary.containsPoint(entity)) { // Entity doesn't belong to this quad
			return false;
		}
		
		// If there is enough room, append
		if(entities.size() < STD_CAPACITY) {
			entities.add(entity);
			return true;
		}
		
		// Divide if not divided and insert sub quads
		if(!isDivided()) {
			subdivide();
		}
		
		if(northwest.insert(entity)) return true;
		if(northeast.insert(entity)) return true;
		if(southwest.insert(entity)) return true;
		if(southeast.insert(entity)) return true;
		
		// Unknown insertion error
		return false;
	}
	
	public boolean insertAll(ArrayList<T> entities) {
		boolean result = true;
		
		for(T entity : entities) {
			result &= insert(entity);
		}
		
		return result;
	}
	
	public void reset() {
		entities.clear();
	}
	
	public boolean isDivided() {
		return northwest != null;
	}
	
	public void subdivide() {
		float half_w = boundary.w / 2f;
		float half_h = boundary.h / 2f;
		float x = boundary.x;
		float y = boundary.y;
		
		northwest = new QuadTree<>(x, y, half_w, half_h);
		northeast = new QuadTree<>(x+half_w, y, half_w, half_h);
		southwest = new QuadTree<>(x, y+half_h, half_w, half_h);
		southeast = new QuadTree<>(x+half_w, y+half_h, half_w, half_h);
	}
	
	public ArrayList<T> query(Rectangle range) {
		ArrayList<T> result = new ArrayList<>();
		
		// If boundaries doesn't intersect
		if(!boundary.intersects(range)) {
			return result; // Empty list
		}
		
		// Traverse entities in this quad level, include intersecting ones
		for(T entity : entities) {
			if(range.containsPoint(entity)) {
				result.add(entity);
			}
		}
		
		// Query subquads if is subdivided
		if(isDivided()) {
			result.addAll(northwest.query(range));
			result.addAll(northeast.query(range));
			result.addAll(southwest.query(range));
			result.addAll(southeast.query(range));
		}
		
		return result;
	}
	
	public ArrayList<QuadTree<T>> querySubquads(Rectangle range) {
		ArrayList<QuadTree<T>> subquads = new ArrayList<>();
		
		if(isDivided()) {
			subquads.addAll(northwest.querySubquads(range));
			subquads.addAll(northeast.querySubquads(range));
			subquads.addAll(southwest.querySubquads(range));
			subquads.addAll(southeast.querySubquads(range));
		}
		else if(boundary.intersects(range)) {
			subquads.add(this);
		}
		
		return subquads;
	}
	
	public ArrayList<T> query(Circle range) {
		ArrayList<T> result = new ArrayList<>();
		
		// If boundaries doesn't intersect
		if(!range.intersects(boundary)) {
			return result; // Empty list
		}
		
		// Traverse entities in this quad level, include intersecting ones
		for(T entity : entities) {
			if(range.containsPoint(entity)) {
				result.add(entity);
			}
		}
		
		// Query subquads if is subdivided
		if(isDivided()) {
			result.addAll(northwest.query(range));
			result.addAll(northeast.query(range));
			result.addAll(southwest.query(range));
			result.addAll(southeast.query(range));
		}
		
		return result;
	}

	public int countQuad() {
		int count = 1;
		
		if(isDivided()) {
			count += northwest.countQuad();
			count += northeast.countQuad();
			count += southwest.countQuad();
			count += southeast.countQuad();
		}
		
		return count;
	}
}
