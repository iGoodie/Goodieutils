package com.programmer.igoodie.utils.structures.spatial;

import java.util.ArrayList;

import com.programmer.igoodie.utils.structures.Entity;
import com.programmer.igoodie.utils.structures.shapes.Rectangle;

public class GridMap {
	
	public static class GridTile {
		ArrayList<Entity> entities = new ArrayList<>();
	}

	Rectangle boundaries;
	GridTile[][] grids;
	float gridWidth, gridHeight;
	
	public GridMap(Rectangle boundaries, int row, int col) {
		this.boundaries = boundaries;
		gridWidth = this.boundaries.w / row;
		gridHeight = this.boundaries.h / col;
		grids = new GridTile[row][col];
	}
	
	// TODO
}
