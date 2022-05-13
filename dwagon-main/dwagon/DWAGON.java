import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
/**
 * Write a description of class DWAGON here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DWAGON extends Actor 
{
    double a = 1.0001;    //acceleration
    double v;   //velocity
    double x;    //x-value
    boolean pressed_shift = false;
    boolean count = false;
    boolean dash_enabled = true;   //enables dash
    static boolean dead = false;
    boolean touching_pipe_old = false;
    int t;  //counted ticks
    GreenfootImage dwagon_down = new GreenfootImage("images/dwagon_Wings_down.png");
    GreenfootImage dwagon_middle = new GreenfootImage("images/dwagon_Wings_middle.png");
    GreenfootImage dwagon_up = new GreenfootImage("images/dwagon_Wings_up.png");
    
    /**
     * Act - do whatever the dwagon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public DWAGON() {
        dwagon_down.scale(dwagon_down.getWidth()/2,dwagon_down.getHeight()/2);  //scaling the image
        dwagon_middle.scale(dwagon_middle.getWidth()/2,dwagon_middle.getHeight()/2);
        dwagon_up.scale(dwagon_up.getWidth()/2,dwagon_up.getHeight()/2);
    }
    
    public void act() 
    {              
        if (v <= -15) { //different images for different velocities
            setImage(dwagon_down);
        }
        
        if (v >-15 || v >= 15) {
            setImage(dwagon_middle);
        }
        
        if (v > 15) {
            setImage(dwagon_up);
        }
        
        v = (v + a);
        int vint = (int)v / 5; //converting double to int       
        setLocation(getX(), getY() + vint); //downwards movement
        
        boolean touching_pipe = false;
        
        for(PIPE pipe : getWorld().getObjects(PIPE.class)){
            if(Math.abs(pipe.getX() - getX()) < 90){
                dash_enabled = false;
                if(Math.abs(pipe.getY() - getY()) > 50){
                    if (STARTSCREEN.dev_mode == false) {
                        dead = true;
                    }
                }
            }
            dash_enabled = true;
            touching_pipe = true;
        }
        
        touching_pipe_old = touching_pipe;
        
        if (isAlive()) {    //adds score per act
            SCORE.score++;
        }
        
        if (touching_pipe_old == false || touching_pipe == true || isAlive()) {
            SCORE.p_score++;
        }
        
        if (count == true) { 
            if (t == 60) {  //counts 1 sec ==> cooldown for dash()
                pressed_shift = false;  //resets bool pressed_shift
                count = false; //resets bool count
                t = 0;
            }
            t++;
        }
        
        if (Greenfoot.isKeyDown("space")) { //checks state of space button
            boost();
        }
        
        if (pressed_shift == false && dash_enabled == true) {   //checks booleans
            if (Greenfoot.isKeyDown("shift")) { //checks state of shift button
                dash();
                pressed_shift = true;
                count = true;
            }
        }
                
        if (isAtEdge()) {  //checks if touched the ground or flew to high
            dead = true;           
        }
        
        if (dead == true) { //checks for condition to stop the game
            Greenfoot.stop();   //stops the game
        }
        
        dead = false;     
    }
        
    private void boost() {
        v = -25;    //negative velocity for upwards movement
    }
    
    private void dash() {
        setLocation(getX(), getY() + 100);   //downwards movement
    }
    
    public static boolean isAlive(){
        return !dead;
    }
}
