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
    double a = 1.00000000001;    //acceleration
    double v;   //velocity
    double x;    //x-value
    boolean pressed_shift = false;
    boolean count = false;
    boolean dash_enabled = true;   //enables dash
    boolean dead = false;
    int t;  //counted ticks
    
    /**
     * Act - do whatever the dwagon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {        
        if (v <= -15) {
            setImage("dwagon_Wings_down.png");
        }
        
        if (v >-15 || v >= 15) {
            setImage("dwagon_Wings_middle.png");
        }
        
        if (v > 15) {
            setImage("dwagon_Wings_up.png");
        }
        
        v = (v + a);
                
        if (count == true) { 
            if (t == 60) {  //counts 1 sec
                pressed_shift = false;  //resets bool pressed_shift
                count = false; //resets bool count
                t = 0;
            }
            t++;
        }
        
        int vint = (int)v / 5; //converting double to int
                
        setLocation(getX(), getY() + vint); //downwards movement
        
        if (Greenfoot.isKeyDown("space")) { //checks state of space button
            boost();
        }
        
        if (pressed_shift == false && dash_enabled == true) {
            if (Greenfoot.isKeyDown("shift")) { //checks state of shift button
                dash();
                pressed_shift = true;
                count = true;
            }
        }
                
        if (getY() >= 719 || getY() <= 1) {  //checks if touched the ground or flew to high
            dead = true;           
        }
        
        if (dead == true) { //checks for condition to stop the game
            Greenfoot.stop();   //stops the game
        }
    }
    
    private void boost() {
        v = -30;    //negative velocity for upwards movement
    }
    
    private void dash() {
        setLocation(getX(), getY() + 100);   //downwards movement
    }
}
