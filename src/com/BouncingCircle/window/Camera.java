package com.BouncingCircle.window;
import com.BouncingCircle.framework.GameObject;
import com.BouncingCircle.objects.Block;
import com.BouncingCircle.objects.Player;
import com.BouncingCircle.window.Handler;


public class Camera  { // Shifts the ball up 
	private float x,y;	
	private Block firstStep ;

	public Camera(float x,float y) {
		this.x=y;
		this.y=y;
		firstStep = Handler.getFirstBlock();
			}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void tick(GameObject player) {
		if(Player.isFirstStepTouched()== true){
	   		y-=2;
	   		
	   				
		}
	}


}
