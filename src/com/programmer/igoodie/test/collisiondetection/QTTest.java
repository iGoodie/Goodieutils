package com.programmer.igoodie.test.collisiondetection;

import java.util.ArrayList;

import com.programmer.igoodie.utils.structures.shapes.Rectangle;
import com.programmer.igoodie.utils.structures.spatial.QuadTree;

import processing.core.PApplet;

/**
 * Test case for quadtree.
 * {@link com.programmer.igoodie.utils.structures.spatial.QuadTree} can be used to store static entities.
 * In this case, there are N entities awaits to be rendered as if they collide or not. </br>
 * RMB will change the detection mode from BruteForce to QTree. </br>
 * LMB will insert a new entity at clicked position. </br>
 *  </br>
 * With N = 3_000 </br>
 * Average FPS with BruteForce = 25 </br>
 * Average FPS with QTree      = 42 </br>
 * 
 * @author iGoodie
 */
public class QTTest extends PApplet {
	
	public static final boolean HIGHLIGHT_QUERY = false;
	
	public static final boolean RANDOM_DISTRIBUTION = true;
	public static final int POINT_COUNT = 3_000;

	public static final float QUERY_RANGE = 100;
	
	ArrayList<Ball> entities;
	QuadTree<Ball> qtree;
	Rectangle query;
	
	int mode = 0;
	
	@Override
	public void settings() {
		size(500, 500, P2D);
	}
	
	@Override
	public void setup() {
		entities = new ArrayList<>();
		qtree = new QuadTree<>(0, 0, width-1, height-1);
		
		noFill();
		
		// Randomly distribute n points if 
		if(RANDOM_DISTRIBUTION) for(int i=0; i<POINT_COUNT; i++) {
			Ball v = new Ball(random(width), random(height));
			entities.add(v);
		}
		qtree.insertAll(entities);
	}
	
	@Override
	public void draw() {
		background(0xFF_FFFFFF); // White refresh
		
		surface.setTitle("FPS: " + (int)frameRate + " " + (mode==0?"BruteForce":"QTree"));
		
		// Detect collision according to current mode
		switch(mode) {
		case 0: collisionBruteForce(); break;
		case 1: collisionQtree(); break;
		}
	}
	
	public void collisionQtree() {
		//qtree.reset();
		//qtree.insertAll(entities);
		
		stroke(0);
		renderEntities(entities);
		
		stroke(0xFF_FF0000);
		for(int i=0; i<entities.size(); i++) {
			Ball p1 = entities.get(i);
			Rectangle query = new Rectangle();
			query.x = p1.position.x - 5;
			query.y = p1.position.y - 5;
			query.w = 10;
			query.h = 10;
			ArrayList<Ball> q = qtree.query(query);
			q.forEach((p2) -> {
				if(p1==p2) return;
				if(p1.position.dist(p2.position) <= 3) {
					ellipse(p1.position.x, p1.position.y, 3, 3);
					ellipse(p2.position.x, p2.position.y, 3, 3);
				}
			});
		}
		
		//stroke(0xFF_FF0000);
		//renderQtree(qtree);

		// Highlight mouse query
		if(HIGHLIGHT_QUERY) if(query!=null) {
			stroke(0xFF_0000FF);
			rect(query.x, query.y, query.w, query.h);
			
			stroke(0xFF_00FF00);
			highlightQuery(query);
		}
	}
	
	public void collisionBruteForce() {
		stroke(0);
		renderEntities(entities);
		
		stroke(0xFF_FF0000);
		for(int i=0; i<entities.size()-1; i++) {
			for(int j=i+1; j<entities.size(); j++) {
				Ball p1 = entities.get(i);
				Ball p2 = entities.get(j);
				if(p1.position.dist(p2.position) <= 3) {
					ellipse(p1.position.x, p1.position.y, 3, 3);
					ellipse(p2.position.x, p2.position.y, 3, 3);
				}
			}
		}
	}
	
	/* Helpers */
	public void renderEntities(ArrayList<Ball> entities) {
		for(Ball e : entities) {
			ellipse(e.position.x, e.position.y, 3, 3);
		}
	}
	
	public void renderQtree(QuadTree<Ball> qtree) {
		Rectangle boundary = qtree.boundary;
		rect(boundary.x, boundary.y, boundary.w, boundary.h);
		
		if(qtree.isDivided()) {
			renderQtree(qtree.northwest);
			renderQtree(qtree.northeast);
			renderQtree(qtree.southwest);
			renderQtree(qtree.southeast);
		}
	}
	
	public void highlightQuery(Rectangle query) {
		ArrayList<Ball> q = qtree.query(query);
		ArrayList<QuadTree<Ball>> qsub = qtree.querySubquads(query);
		
		// Hi-light points
		for(Ball e : q) {
			ellipse(e.position.x, e.position.y, 3, 3);
		}
		
		// Hi-light subquads
		for(QuadTree<Ball> quad : qsub) {
			rect(quad.boundary.x, quad.boundary.y, quad.boundary.w, quad.boundary.h);
		}
	}
	
	/* Handlers */
	@Override
	public void mousePressed() {
		if(mouseButton == RIGHT) {
			mode = (mode+1) % 2;
		}
		else if(mouseButton == LEFT) {
			Ball ball = new Ball(mouseX, mouseY);
			entities.add(ball);
			qtree.insert(ball);
		}
	}
	
	@Override
	public void mouseMoved() {
		if(!HIGHLIGHT_QUERY) return;
		float half_radius = QUERY_RANGE/2f;
		query = new Rectangle(mouseX-half_radius, mouseY-half_radius, 
				QUERY_RANGE, QUERY_RANGE);
	}
	
	/* Unique Main Method */
	public static void main(String[] args) {
		PApplet.main(QTTest.class.getName());
	}
}
