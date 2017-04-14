package com.BouncingCircle.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.BouncingCircle.framework.GameObject;
import com.BouncingCircle.framework.ObjectId;
import com.BouncingCircle.window.Handler;

public class Block extends GameObject{ // Block method is used for the collision blocks and the steps

	private  int R = GameObject.getColorR();
	private  int G = GameObject.getColorG();
	private  int B = GameObject.getColorB();
	
	private ObjectId id;
	
	public Block(float x, float y,int Width,int Height, ObjectId id) {
		super(x, y,Width,Height,id);
		this.id = id;
	}

	public void tick(LinkedList<GameObject> object) {
		for (int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
		}
	}

	public void render(Graphics g) { // to draw the object
		Color newColor = new Color (R,G,B);
		g.setColor (newColor);
		if(id == ObjectId.Block){
		g.fillRect((int) x, (int) y, Width,Height);
		}
	}

	public Rectangle getBounds() {// draws the collision box in java
		return new Rectangle((int)x,(int)y, Width,Height);
	}
}
