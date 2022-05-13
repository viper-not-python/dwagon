import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class STARTSCREEN here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class STARTSCREEN extends World
{

    /**
     * Constructor for objects of class STARTSCREEN.
     * 
     */
    public STARTSCREEN()
    {    
        // Create a new world with 1080 x 720 cells with a cell size of 1x1 pixels.
        super(1080 , 720, 1); 
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
