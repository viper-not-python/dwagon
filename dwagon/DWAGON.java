import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

//handels dwagon movement, dwagon animation, shooting, win condition, dwagon death and gameover condition
public class DWAGON extends HITBOX_CHECK 
{
    double a = 1.0001;    //y acceleration
    double v_y;   //y velocity
    double x;    //x-value
    boolean pressed_shift = false;
    boolean pressed_enter = false;
    boolean count_shift = false;
    boolean count_enter = false;
    boolean dash_enabled = true;   //enables dash
    static boolean dead = false;
    static boolean win = false; //true if either TICK.ticks == 3600 || SCORE.score >= 3600
    int h;  //counted ticks
    int t;  //counted ticks
    int dash;   //change of pixels dashed according to difficulty
    
    
    GreenfootImage dwagon_down = new GreenfootImage("images/dwagon_Wings_down.png");
    GreenfootImage dwagon_middle = new GreenfootImage("images/dwagon_Wings_middle.png");
    GreenfootImage dwagon_up = new GreenfootImage("images/dwagon_Wings_up.png");
    
    
    GreenfootSound wing_flap = new GreenfootSound("wing_flap.wav");
    GreenfootSound firesound = new GreenfootSound("fire.wav");
    

    public DWAGON() {
        dwagon_down.scale(dwagon_down.getWidth()/2,dwagon_down.getHeight()/2);  //scaling down the image
        dwagon_middle.scale(dwagon_middle.getWidth()/2,dwagon_middle.getHeight()/2);
        dwagon_up.scale(dwagon_up.getWidth()/2,dwagon_up.getHeight()/2);
    }
    
    
    public void act() {                      
        if (v_y <= -15)                   //wing flap animation
            setImage(dwagon_down);
        if (v_y >-15 || v_y >= 15) 
            setImage(dwagon_middle);
        if (v_y > 15) 
            setImage(dwagon_up);
        
        
        v_y = (v_y + a);    //accelerating the dwagon 
        int vint = (int)v_y / 5 - GAMEWORLD.vchange / 3; //converting double to int and adding vchange to match difficulty      
        setLocation(getX(), getY() + vint); //downwards movement
        
        
        boolean touching_pipe = false;
        for(PIPE pipe : getWorld().getObjects(PIPE.class)){
            if(Math.abs(pipe.getX() - getX()) < 90){
                dash_enabled = false;
            }
            dash_enabled = true;
            touching_pipe = true;
        }
        
        
        
        if (isAlive())     //adds a scorepoint per act
            SCORE.score++;
        
        
        
        if (touch(BARRIER.class) == true && STARTSCREEN.dev_mode == false) 
            dead = true;
        if (touch(PIPE.class) == true && STARTSCREEN.dev_mode == false)
            dead = true;
        if (isAtEdge() && STARTSCREEN.dev_mode == false)   //checks if touched the ground or flew to high
            dead = true;   
        
        
        if (TICK.ticks == 3600 || SCORE.score >= 3600)  //win condition 
            win = true;
        
        
        if (count_enter == true) { 
            if (h == 45) {  //counts 3/4 sec ==> cooldown for shoot()
                pressed_enter = false;  //resets bool pressed_shift
                count_enter = false; //resets bool count_shift
                h = 0;
            }
            h++;
        }
        if (count_shift == true) { 
            if (t == 60) {  //counts 1 sec ==> cooldown for dash()
                pressed_shift = false;  //resets bool pressed_shift
                count_shift = false; //resets bool count_shift
                t = 0;
            }
            t++;
        }
        if (pressed_enter == false && isAlive() == true) {  //checks booleans
            if (Greenfoot.isKeyDown("enter")) { //checks state of enter button
                shoot();
                pressed_enter = true;
                count_enter = true;
            }  
        }
        if (Greenfoot.isKeyDown("space") && isAlive() == true) //checks state of space button
            boost();
        if (pressed_shift == false && isAlive() == true && dash_enabled == true) {   //checks booleans
            if (Greenfoot.isKeyDown("shift")) { //checks state of shift button
                dash();
                pressed_shift = true;
                count_shift = true;
            }
        }
                
        
        if (win == true) 
            Greenfoot.setWorld(new WIN_SCREEN());
        if (dead == true)  //checks for condition to stop the game
            Greenfoot.setWorld(new GAME_OVER());    //gameover screen
        
        
        dead = false;     
    }
    
        
    private void shoot() {
        getWorld().addObject(new PROJECTILE(), getX() + 55, getY() + 18);
        firesound.setVolume(80);
        firesound.play();
    }
    
    
    private void boost() {        
        v_y = -25 + GAMEWORLD.vchange;
        wing_flap.play();
    }
    
    
    private void dash() {   //dash according to difficulty
        if (GAMEWORLD.difficulty <= 2)
            dash = 100;
        if (GAMEWORLD.difficulty >= 3 && GAMEWORLD.difficulty < 5)
            dash = 130;
        if (GAMEWORLD.difficulty == 5)
            dash = 150; 
        
        
        setLocation(getX(), getY() + dash);
    }
    
    
    public static boolean isAlive(){
        return !dead;
    }
}
