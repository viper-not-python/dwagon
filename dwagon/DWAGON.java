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
    float a;    //acceleration
    float v;    //velocity
    float x;    //x-value
    
    /**
     * Act - do whatever the dwagon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        a = 1;
        v = (v + a);
        
        setLocation(getX(), getY()+ Math.round(v / 2));
        a++;
    }   
    
    private void boost() {
    
    }
    
    private void dash() {
    
    }
}
