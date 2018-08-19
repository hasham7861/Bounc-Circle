package bouncingcircle.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public abstract class GameObject { // Super class for all of the objects 

	protected float x,y;
	protected int Width,Height;
	protected ObjectId id;
	protected float velX = 0 , velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean collisionRight = false;
	protected boolean collisionLeft = false;

	private static int R1;// integer for the red color
	private static int G1;//  integer value of green color
	private static int B1;// integer value of blue color
	
	public GameObject (float x , float y ,int Width,int Height, ObjectId id){

		this.x=x;
		this.y=y;
		this.id =id;
		this.Width=Width;
		this.Height=Height;
	}

	public abstract void tick (LinkedList <GameObject> object); // Method for collision detection
	public abstract void render (Graphics g); 
	public abstract Rectangle getBounds();

	public int getWidth() {
		return Width;
	}

	public void setWidth(int blockWidth) {
		this.Width = Width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int Height) {
		this.Height = Height;
	}

	public float getX(){
		return x;
	}
	public  float getY(){
		return y;
	}
	public  void setX (float x){
		this.x=x;
	}
	public  void setY (float y){
		this.y=y;
	}

	public  float getVelX(){
		return velX;
	}
	public  float getVelY(){
		return velY;
	}
	public  void setVelX (float velX){
		this.velX = velX;
	}
	public  void setVelY (float velY){
		this.velY = velY;
	}

	public  ObjectId getId(){
		return id;
	}
	
	//************ Methods for the R G B ***************
	public static void setColor(int R, int G,int B){ // This method is mostly used for changing the color of the blocks
		R1=R;
		G1=G;
		B1=B;
	}
	public static int getColorR(){// returns the integer value of red
		return R1;
	}

	public static int getColorG(){// returns the integer value of green
		return G1;
	}
	public static  int getColorB(){// returns the integer value of blue
		return B1;
	}

	/// ******** Returns the boolean for when the collision happens ************
	public boolean isCollisionRight() {
		return collisionRight;
	}

	public void setCollisionRight(boolean collisionRight) {
		this.collisionRight = collisionRight;
	}
	public boolean isCollisionLeft() {
		return collisionLeft;
	}

	public void setCollisionLeft(boolean collisionLeft) {
		this.collisionLeft = collisionLeft;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

}// end of the class
