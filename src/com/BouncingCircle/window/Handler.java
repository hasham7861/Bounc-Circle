package com.BouncingCircle.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.BouncingCircle.framework.GameObject;
import com.BouncingCircle.framework.ObjectId;
import com.BouncingCircle.objects.Block;
import com.BouncingCircle.objects.Food;
import com.BouncingCircle.objects.Player;


public class Handler { // Loops through all of the objects 

	public LinkedList <GameObject> object = new LinkedList <GameObject>();

	private GameObject tempObject;
	private GameObject camObject;

	private static Camera cam;

	// **Hard coded Collision Blocks**
	private static Block firstBlock;
	private static Block leftWall;
	private static Block rightWall;
	private static Block tempBottom;
	private static Block fallingBlock;
	private static Block randomBlocks;

	private static float yCamera; 
	private  Player player;

	public Handler( Camera cam){
		this.cam=cam;
	}

	public void tick(){
		for (int i= 0 ; i<object.size(); i++ ){ // gets the size of the objects list
			tempObject = object.get(i);
			tempObject.tick(object);

			//** Blocks that follow the camera.
			yCamera = cam.getY();
			leftWall.setY(yCamera-10);
			rightWall.setY(yCamera-10);
			fallingBlock.setY(yCamera+610);
		}
	}


	public void deleteEverything (){ // Deletes every object on the screen
		object.clear();

	}

	public void render (Graphics g){
		for (int i= 0 ; i<object.size(); i++ ){ // gets the size of the objects list
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject  (GameObject object){ // Adding objects from the list 
		this.object.add(object); 
	}

	public void removeObject (GameObject object){// Removing objects from the list 
		this.object.remove(object);

	}

	public void createLevel(){ // Draws the blocks in the game

		GameObject.setColor(255,255,255);//White Color

		///********* Hard coded Blocks ******
		leftWall =new Block (0,-10,20,Game.HEIGHT+10,ObjectId.Block);// Left wall
		addObject(leftWall);		
		rightWall = new Block (Game.WIDTH-20,-10,20,Game.HEIGHT+10,ObjectId.Block);// Right wall
		addObject (rightWall);		
		tempBottom = new Block (20,Game.HEIGHT-20,Game.WIDTH-40,20,ObjectId.Block);
		addObject(tempBottom);// Bottom floor
		GameObject.setColor(255,255,3);//Yellow Color
		fallingBlock =  new Block (20,Game.HEIGHT-10,Game.WIDTH-40,10,ObjectId.Block); // ObjectId.CollisionBlock
		//addObject(fallingBlock);
		
		//**** Block used for the camera, so it moves when it first touched
		GameObject.setColor(232,79,79);//GreenColor
		firstBlock = new Block (200,Game.HEIGHT-150,Game.WIDTH-300,20,ObjectId.Block);
		addObject(firstBlock);// first step

		addObject(new Block (20,Game.HEIGHT-250,Game.WIDTH-300,20,ObjectId.Block));// Second step
		addObject(new Block (Game.WIDTH-170,Game.HEIGHT-300,Game.WIDTH-400,20,ObjectId.Block));// Third step

		int yBlock = Game.HEIGHT-370;
		// **** Hard coded blocks - due to time
		// First 10 Steps
		addObject(new Block (40,yBlock,70,20,ObjectId.Block));
		addObject(new Block (200,yBlock-90,70,20,ObjectId.Block));
		addObject(new Block (100,yBlock-140,70,20,ObjectId.Block));				
		addObject(new Block (300,yBlock-180,70,20,ObjectId.Block));
		addObject(new Block (350,yBlock-250,70,20,ObjectId.Block));
		addObject(new Block (50,yBlock-290,70,20,ObjectId.Block));
		addObject(new Block (50,yBlock-290,70,20,ObjectId.Block));
		addObject(new Block (200,yBlock-320,70,20,ObjectId.Block));
		addObject(new Block (300,yBlock-390,70,20,ObjectId.Block));
		addObject(new Block (350,yBlock-440,70,20,ObjectId.Block));
		// Second 10 Steps
		addObject(new Block (120,yBlock-450,70,20,ObjectId.Block));
		addObject(new Block (60,yBlock-550,70,20,ObjectId.Block));
		addObject(new Block (160,yBlock-650,70,20,ObjectId.Block));
		addObject(new Block (220,yBlock-750,70,20,ObjectId.Block));
		addObject(new Block (290,yBlock-850,70,20,ObjectId.Block));
		addObject(new Block (340,yBlock-960,70,20,ObjectId.Block));
		addObject(new Block (280,yBlock-1080,70,20,ObjectId.Block));
		addObject(new Block (230,yBlock-1190,70,20,ObjectId.Block));
		addObject(new Block (290,yBlock-1300,70,20,ObjectId.Block));
		// Third 10 Steps
		addObject(new Block (260,yBlock-1400,70,20,ObjectId.Block));
		addObject(new Block (200,yBlock-1500,70,20,ObjectId.Block));
		addObject(new Block (100,yBlock-1600,70,20,ObjectId.Block));
		addObject(new Block (200,yBlock-1700,70,20,ObjectId.Block));
		addObject(new Block (350,yBlock-1800,70,20,ObjectId.Block));
		addObject(new Block (200,yBlock-1900,70,20,ObjectId.Block));
		addObject(new Block (180,yBlock-2000,70,20,ObjectId.Block));
		addObject(new Block (250,yBlock-2100,70,20,ObjectId.Block));
		addObject(new Block (290,yBlock-2200,70,20,ObjectId.Block));
		// Fourth 10 Blocks		
		addObject(new Block (120,yBlock-2300,70,20,ObjectId.Block));
		addObject(new Block (60,yBlock-2400,70,20,ObjectId.Block));
		addObject(new Block (160,yBlock-2500,70,20,ObjectId.Block));
		addObject(new Block (220,yBlock-2600,70,20,ObjectId.Block));
		addObject(new Block (290,yBlock-2700,70,20,ObjectId.Block));
		addObject(new Block (340,yBlock-2800,70,20,ObjectId.Block));
		addObject(new Block (280,yBlock-2900,70,20,ObjectId.Block));
		addObject(new Block (230,yBlock-3000,70,20,ObjectId.Block));
		addObject(new Block (290,yBlock-3100,70,20,ObjectId.Block));		
		// Fifth 10 Blocks		
		addObject(new Block (220,yBlock-3200,70,20,ObjectId.Block));
		addObject(new Block (150,yBlock-3300,70,20,ObjectId.Block));
		addObject(new Block (230,yBlock-3400,70,20,ObjectId.Block));
		addObject(new Block (100,yBlock-3500,70,20,ObjectId.Block));
		addObject(new Block (20,yBlock-3600,70,20,ObjectId.Block));
		addObject(new Block (100,yBlock-3700,70,20,ObjectId.Block));
		addObject(new Block (30,yBlock-3800,70,20,ObjectId.Block));
		addObject(new Block (100,yBlock-3900,70,20,ObjectId.Block));
		addObject(new Block (200,yBlock-4000,70,20,ObjectId.Block));

	}
	public void getfood(){ // Draws the colorful circles that is called food
		addObject(new Food (150,300,10, 10,ObjectId.Food));
		addObject(new Food (200,100,10, 10,ObjectId.Food));
		addObject(new Food(60,-100,10, 10,ObjectId.Food));
		addObject(new Food(60,-400,10, 10,ObjectId.Food));
		addObject (new Food (300,-700,10,10,ObjectId.Food));
		addObject (new Food (300,-900,10,10,ObjectId.Food));
		addObject (new Food (250,-1300,10,10,ObjectId.Food));
		addObject (new Food (300,-1800,10,10,ObjectId.Food));
		addObject (new Food (100,-2400,10,10,ObjectId.Food));
		addObject (new Food (200,-3000,10,10,ObjectId.Food));
		addObject (new Food (100,-3600,10,10,ObjectId.Food));

	}

	public static Block getTempBottom() { // Returns the ground block
		return tempBottom;
	}

	public static void setTempBottom(Block tempBottom) {
		Handler.tempBottom = tempBottom;
	}

	public static Block getFallingBlock() { 
		return fallingBlock;
	}

	public static void setFallingBlock(Block fallingBlock) {
		Handler.fallingBlock = fallingBlock;
	}

	public static float getyCamera() {
		return yCamera;
	}

	public static void setyCamera(float yCamera) {
		Handler.yCamera = yCamera;
	}

	public static Block getFirstBlock() {
		return firstBlock;
	}

	public void setFirstBlock(Block firstBlock) {
		this.firstBlock = firstBlock;
	}


}// End of the class
