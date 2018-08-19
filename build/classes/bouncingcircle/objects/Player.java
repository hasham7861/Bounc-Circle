package bouncingcircle.objects;

import bouncingcircle.window.*;
import bouncingcircle.framework.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;


public class Player extends GameObject{ // Bouncing ball object

	private float width = 32, height =32;
	private float gravity = 1f;
	private final float MAX_SPEED = 15;
	private static int score;

	private Handler handler;
	private static boolean firstStepTouched = false; // Boolean to check if the ball hit 
	private static boolean endGame= false;

	//**** Hard coded Blocks used for collision  **********
	private Block firstStep; //  Location of the first block
	private Block CollisionBlock; 
	private Block tempBottom;
	GameObject ballObject ; 

	private Image playerBall;// Ball Image

	public Player(float x, float y,int width ,int height, Handler handler, ObjectId id) {
		super(x, y,width,height,id);
		this.handler=handler;

		load(); // displays the image of the ball
	}


	public void tick(LinkedList<GameObject> object) {
		x +=velX; // Increases the x value of the player
		y +=velY; // Increases the y value of the player

		for (int i=0; i<object.size();i++){
			GameObject tempObject = object.get(i);
		}
		if (falling  || jumping  ){
			velY +=gravity;
			if (velY > MAX_SPEED){
				velY= MAX_SPEED;
			}
		}	

		/// ****** Used For the keyInputs, to get rid of the sticky keys while moving right and left really fast **********

		if (KeyInput.isKeyReleasedRight() && !KeyInput.isKeyReleasedLeft() ){
			if (!isCollisionLeft() && getX()>20){
				setVelX(-5);
			}
		}
		else if (KeyInput.isKeyReleasedLeft() && !KeyInput.isKeyReleasedRight()){
			if(!isCollisionRight() && getX()<Game.WIDTH-20){
				setVelX(5);
			}
		}
		else if (KeyInput.isKeyReleasedLeft() && KeyInput.isKeyReleasedRight()){
			setVelX(0);
		}

		Collision (object);
	}

	private void Collision (LinkedList<GameObject> object){ // Method that constantly checks for the collision 

		for (int i=0; i<handler.object.size();i++){ // Goes through all of your Objects
			GameObject tempObject = handler.object.get(i); // Gets the number of the object

			if (tempObject.getId()==ObjectId.Food){ // Used to check the collision to collect food
				if(getBounds().intersects(tempObject.getBounds())){
					score++;
					handler.removeObject(tempObject);
				}
			}

			//// ********* When the Ball collides with the blocks	
			if(tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.InvBlock ){// Checks the collision with the steps

				firstStep = Handler.getFirstBlock(); // Hard coded first step
				CollisionBlock = Handler.getFallingBlock(); // Bottom collision when the ball falls from a height
				tempBottom = Handler.getTempBottom(); // Ground

				if(getBounds().intersects(CollisionBlock.getBounds()) || handler.getyCamera()== -4200){ // When ever the ball falls down, delete everything.
					handler.deleteEverything();
					endGame= true;
				}

				if(getBoundsTop().intersects(tempObject.getBounds())){// Top collision of a block , but bottom collision when the ball hits it.
					y=tempObject.getY()+height;
					velY=+17;
				}

				if(getBounds().intersects(tempObject.getBounds())){// Bottom Collision of the block, but top collision when the ball hits it/

					if(getBounds().intersects(firstStep.getBounds())){ // The camera only moves when the ball touches the first step
						firstStepTouched=true;
						handler.addObject(CollisionBlock);
						handler.removeObject(tempBottom);
					}

					y=tempObject.getY()-height;
					velY=-17;
				}				

				if(getBoundsRight().intersects(tempObject.getBounds())){// Right Collision
					setCollisionRight(true);
					x=tempObject.getX()-getWidth();
					setVelX(0);

				}
				if(getBoundsLeft().intersects(tempObject.getBounds())){// Left Collision
					setCollisionLeft(true);
					x=tempObject.getX()+tempObject.getWidth();
					setVelX(0);
				}

				if(!getBoundsRight().intersects(tempObject.getBounds())){
					setCollisionRight(false);
				}

				if(!getBoundsLeft().intersects(tempObject.getBounds())){
					setCollisionLeft(false);
				}

			}// End of the if statement		
		}// End of for loop


	}

	public void render(Graphics g) { 
		g.drawImage(playerBall,(int)getX(),(int)getY(),null);
		// To check the collison of the player
//		Graphics2D g2d = (Graphics2D) g ;
//		g.setColor(Color.red);
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsTop());
		

	}
	//******************************* Collision Boxes around the ball*******************************************

	public Rectangle getBounds() { // gets  the rectangle around the player
		return new Rectangle ((int) x+7,(int) y+16,17,13);
	}

	public Rectangle getBoundsTop() { // gets  the rectangle around the player
		return new Rectangle ((int) x+7,(int) y+2,17,13);
	}

	public Rectangle getBoundsRight() { // gets  the rectangle around the player
		return new Rectangle ((int)((int) x + width -6),(int) y+4,5,20);
	}

	public Rectangle getBoundsLeft() { // gets  the rectangle around the player
		return new Rectangle ((int)((int) x),(int) y+4,5,20);
	}

	

	//******************************* Source to the ball image *******************************************
	public void load (){
		playerBall = new ImageIcon("src/Graphics/Bouncing_Circle_Object.png").getImage();
	}

	public static int getScore() {// Total Score
		return score;
	}

	public static void setScore(int score) {
		Player.score = score;
	}

	public static boolean isEndGame() {// Gets the boolean, when the game has been ended
		return endGame;
	}

	public static void setEndGame(boolean endGame) {
		Player.endGame = endGame;
	}

	public static boolean isFirstStepTouched() {
		return firstStepTouched;
	}

	public static void setFirstStepTouched(boolean firstStepTouched) {
		Player.firstStepTouched = firstStepTouched;
	}






}// end of the class
