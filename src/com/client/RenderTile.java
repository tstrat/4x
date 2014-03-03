package com.client;

import java.util.Arrays;

import com.googlecode.gwtgl.array.Float32Array;
import com.googlecode.gwtgl.binding.WebGLBuffer;
import com.googlecode.gwtgl.binding.WebGLRenderingContext;

public class RenderTile {
	public Coordinate position;
	private int index;
	private float size;
	private float depth;
	
	private LandType land;
	
	private WebGLRenderingContext gl;
	private Float32Array vertexBuffer, texCoordBuffer;
	
	public static void addTileToBuffer(float x, float y, float size, int index, LandType land, WebGLRenderingContext gl, Float32Array vertexBuffer, Float32Array texCoordBuffer){
		Coordinate position = new Coordinate(x, y);

		float depth = 0.0f;
		
		float delta = 0.25f;

		float startx, starty;
		
		switch(land){
		case Grass:		startx = 0.0f;
						starty = 0.0f;
						break;
		case Dirt: 		startx = 0.0f;
						starty = delta;
						break;
		case Water: 	startx = delta;
						starty = 0.0f;
						break;
		case Forest:	startx = delta;
						starty = delta;
						break;
		case Mountain:	startx = 2 * delta;
						starty = 0.0f;
						break;
		case Snow:		startx = 2 * delta;
						starty = delta;
						break;
		default:		startx = 2*delta;
						starty = 2*delta;
						break;
		}
		
		float[] texCoords = new float[] { 
				startx, starty, 
				startx + delta, starty,
				startx, starty + delta,
				
				startx, starty + delta,
				startx + delta, starty + delta,
				startx + delta, starty
		};
		
		texCoordBuffer.set(texCoords, index*texCoords.length);
		
		float[] verts = new float[] {
				// first triangle
				position.x, position.y, depth,
				position.x + size, position.y, depth,
				position.x, position.y + size, depth,
				
				// second triangle
				position.x, position.y + size, depth,
				position.x + size, position.y + size, depth,
				position.x + size, position.y, depth
		};
		vertexBuffer.set(verts, index*verts.length);
	}
}