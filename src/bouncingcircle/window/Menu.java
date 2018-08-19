/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncingcircle.window;

import bouncingcircle.framework.ObjectId;
import bouncingcircle.objects.Player;
import bouncingcircle.window.Game.STATE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Hasham
 */
public class Menu extends MouseAdapter {

    private final Game game;
    private Handler handler;
    private String  menuString;
  
    private int menuStringX;
    private  Color menuStringColor;
    
    public Menu(Game game,Handler handler){
        this.game=game;
        this.handler = handler;
        this.menuString = "Bouncing Circle";
        this.menuStringX = 40;
        this.menuStringColor = Color.WHITE;
        
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        // If hovering over play button then play
        if(mouseOver(mx,my,100,200,250,64)){
            game.gameState = STATE.Game;
            handler.addObject(new Player(100, 400, 32, 32, handler, ObjectId.Player)); // Bouncing ball initiated
            handler.createLevel();
            
        } else if (mouseOver(mx,my,100,300,250,64)){
            System.exit(0);
        
        }
        

    }

    public void mousReleased(MouseEvent e) {

    }
    
    private boolean mouseOver(int mx, int my, int x,int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my <y + height){
                return true;
            }else{
                return false;
            }
        } else{
            return false;
        }
        
    }
    
    public void setGameOverString(){
        this.menuString = "GameOver";
        this.menuStringX = 100;
        this.menuStringColor= Color.RED;
        
    }
    public void render(Graphics g) {
        Font fnt = new Font("arial",1,50);
        Font fnt2 = new Font("arial",1,30);
        
        
        g.setFont(fnt);
        g.setColor(menuStringColor);
        g.drawString(this.menuString,menuStringX,100);
        
        g.setFont(fnt2);
        g.drawRect(100,200,250,64);
        g.drawString("Play",195,243);
        
        g.drawRect(100,300,250,64);
        g.drawString("Quit",195,343);
        
   
    }

    public void tick() {

    }

}
