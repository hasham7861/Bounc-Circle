package bouncingcircle.window;

import bouncingcircle.objects.*;
import java.awt.Graphics;
import java.util.LinkedList;

import bouncingcircle.framework.*;
import bouncingcircle.objects.Player;

public class Handler { // Loops through all of the objects 

    public LinkedList<GameObject> object = new LinkedList<>();

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
    private Player player;

    public Handler(Camera cam) {
        Handler.cam = cam;
    }

    public void tick() {
        for (int i = 0; i < object.size(); i++) { // gets the size of the objects list
            tempObject = object.get(i);
            tempObject.tick(object);

            //** Blocks that follow the camera.
            yCamera = cam.getY();
            leftWall.setY(yCamera - 10);
            rightWall.setY(yCamera - 10);
            fallingBlock.setY(yCamera + 610);
        }
    }

    public void deleteEverything() { // Deletes every object on the screen
        object.clear();
        

    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) { // gets the size of the objects list
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) { // Adding objects from the list 
        this.object.add(object);
    }

    public void removeObject(GameObject object) {// Removing objects from the list 
        this.object.remove(object);

    }

    public void createLevel() { // Draws the blocks in the game

        GameObject.setColor(255, 255, 255);//White Color

        ///********* Hard coded Blocks ******
        leftWall = new Block(0, -10, 20, Game.HEIGHT + 10, ObjectId.Block);// Left wall
        addObject(leftWall);
        rightWall = new Block(Game.WIDTH - 20, -10, 20, Game.HEIGHT + 10, ObjectId.Block);// Right wall
        addObject(rightWall);
        tempBottom = new Block(20, Game.HEIGHT - 20, Game.WIDTH - 40, 20, ObjectId.Block);
        addObject(tempBottom);// Bottom floor
        GameObject.setColor(255, 255, 3);//Yellow Color
        fallingBlock = new Block(20, Game.HEIGHT - 10, Game.WIDTH - 40, 10, ObjectId.Block); // ObjectId.CollisionBlock
        addObject(fallingBlock);

	//**** Block used for the camera, so it moves when it first touched
        GameObject.setColor(232, 79, 79);//GreenColor
        firstBlock = new Block(200, Game.HEIGHT - 150, 60, 20, ObjectId.Block);
        addObject(firstBlock);// first step


        // randomly change the x based on the window with exclusive of the walls
        int randomBlockXLocation = 0;
        int blockYLocation = (int) (firstBlock.getY() - 50);
        randomBlockXLocation = (int) (Math.random() * (Game.WIDTH));
        Block block;

        // dyanmically creating blocks
        for (int i = 0; i < 30; i++) {
            // Check if block was created previously then based off that create random block
            
            do {
                randomBlockXLocation = (int) (Math.random() * (Game.WIDTH - rightWall.getWidth()));
            } while ((randomBlockXLocation >= leftWall.getWidth()) && (randomBlockXLocation + 60) >= (rightWall.getX()) );
            
            // Increase the block y location
            blockYLocation -= 70;
            
            block = new Block(randomBlockXLocation, blockYLocation, 60, 20, ObjectId.Block);

            addObject(block);
           
       
            // spawning food after every 5 block spawns
            if (i % 5 == 0) {
                addObject(new Food(block.getX(), block.getY() - 25, 10, 10, ObjectId.Food));
            }

        }
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
        Handler.firstBlock = firstBlock;
    }

}// End of the class
