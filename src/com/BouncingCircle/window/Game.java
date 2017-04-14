/*
 * ISC 4U0
 * MR.A.SAYED
 * Hasham Alam and Tahmid Mostafa
 * June 19, 2015
 * Bouncing Circle Game
 ****** This is how my program works****
The game is very simple.
As soon as you run the application, you see that game starts up right away. 
To Move Left and Right , use the left and right arrow keys.

The main objective of the game is two collect the colorful balls, also known as food,
when collected you them your score increases.

**** Future Update logs *** 
Since the bouncing circle game is still in beta.Due to lack of time, i couldn't get
the game to work I wanted to.
- Increase the camera faster when the ball collides a certain amount of blocks
- Make a random generator for the blocks and the food.
- Add a menu state
- Add a file that stores the scores forever.

Credits:
Hasham worked on making the collisions, the blocks
Tahmid worked on making the Camera to work, added the food
And the rest we worked on it together.

 */


package com.BouncingCircle.window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import com.BouncingCircle.framework.KeyInput;
import com.BouncingCircle.framework.ObjectId;
import com.BouncingCircle.objects.Player;

public class Game extends Canvas implements Runnable{ // Main window

	private static final long serialVersionUID = -8921419424614180143L;
	private boolean running = false;
	private Thread thread ;
	public static int WIDTH ,HEIGHT;
	Handler handler ;
	Camera cam;
	
	public void init (){
		WIDTH = getWidth();
		HEIGHT= getHeight();

		cam= new Camera(0,0);
		handler = new Handler (cam);
		handler.addObject(new Player (100,400,32,32,handler,ObjectId.Player)); // Bouncing ball initiated
		handler.getfood(); // Draws the food
		handler.createLevel();	// Draws all of the blocks and steps	
		this.addKeyListener(new KeyInput (handler));

	}
	public synchronized void start (){
		if (running) 
			return;
		running = true;
		thread = new Thread (this);
		thread.start();
	}

	private void tick() {// Updates everything in the game
		handler.tick();
		if(Player.isEndGame()==true){
				System.exit(0);
		}
		for(int i= 0; i<handler.object.size(); i++){
			if(handler.object.get(i).getId()== ObjectId.Player){
				cam.tick(handler.object.get(i));
			}

		}

	}
	private void render (){// Renders all the graphics

		BufferStrategy bs = this.getBufferStrategy();
		if ( bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics g2d= (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
	
		// Draw Between, 
		g2d.translate((int)-cam.getX(),(int)-cam.getY());// begin of camera
		// All of the objects will be affected between this

		handler.render(g);// Renders all of the objects 
		g.drawString("Left & Right arrow keys to move", 25, 400);
		g.setFont(new Font ("Arial",Font.PLAIN,30));	
		g.drawString("Score: " + Player.getScore(),170,(int)handler.getyCamera()+50);
		
		g2d.translate((int)cam.getX(),(int)cam.getY()); // end of camera
		
		////////////////////////////////////////////////
			
		g.dispose();
		bs.show();
	}

	public void run() { // GameLoop / the heart beat of the game
		init (); // this method would initiate everything
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				 System.out.println("FPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}
	
	public static void main (String args[]){ // Declares the window in the main
		new Window(450,600,"Bouncing Circle" , new Game());

	}

}
