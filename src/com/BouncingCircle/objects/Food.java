package com.BouncingCircle.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import com.BouncingCircle.framework.GameObject;
import com.BouncingCircle.framework.ObjectId;

public class Food extends GameObject { // The color balls are assigned to be the food
		
	private Image Food;
	private ObjectId id;
	
	public Food(float x, float y,int Width,int Height, ObjectId id) {
		super(x, y,Width,Height,id);
		this.id = id;
		load();	
	}
	
	public void tick(LinkedList<GameObject> object) {
	}
	
	public void render(Graphics g) {
		if(id == ObjectId.Food){
		g.drawImage(Food,(int)x,(int)y,null);
		}			
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, Width+8,Height+7);
	}
	
	public void load(){
		Food = new ImageIcon("Resources/Graphics/Food.png").getImage();
	}

}
