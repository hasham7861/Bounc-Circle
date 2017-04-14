package com.BouncingCircle.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.BouncingCircle.objects.Player;
import com.BouncingCircle.window.Handler;

public class KeyInput extends KeyAdapter {// All of the Key 
	Handler handler;

	private static boolean keyReleasedRight = true; 
	private static boolean keyReleasedLeft = true;

	public KeyInput (Handler handler){// Used to get the objects from the handler.
		this.handler = handler;
	}

	public void keyPressed (KeyEvent e ){
		int key = e.getKeyCode();
		for (int i=0; i <handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId()== ObjectId.Player){
				if (key == KeyEvent.VK_D ||  key == KeyEvent.VK_RIGHT) keyReleasedRight = false;						
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyReleasedLeft = false;
			}
			}

		if (key == KeyEvent.VK_ESCAPE)System.exit(0);
	}

	public void keyReleased (KeyEvent e ){
		int key = e.getKeyCode();

		for (int i=0; i <handler.object.size();i++){// Goes through all of the objects
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId()== ObjectId.Player){
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyReleasedRight = true;		
				if (key == KeyEvent.VK_A|| key == KeyEvent.VK_LEFT) keyReleasedLeft=true;
			}
		}
	}

	public static boolean isKeyReleasedRight() {
		return keyReleasedRight;
	}

	public static void setKeyReleasedRight(boolean keyReleasedRight) {
		KeyInput.keyReleasedRight = keyReleasedRight;
	}

	public static boolean isKeyReleasedLeft() {
		return keyReleasedLeft;
	}

	public static void setKeyReleasedLeft(boolean keyReleasedLeft) {
		KeyInput.keyReleasedLeft = keyReleasedLeft;
	}

}// end of the class





