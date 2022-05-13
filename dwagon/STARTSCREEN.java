import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class STARTSCREEN here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */




public class STARTSCREEN extends World
{
    static boolean dev_mode = false;
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
        if(Greenfoot.isKeyDown("9")) {
            dev_mode = true;
        }
        
        if(Greenfoot.isKeyDown("8")) {
            dev_mode = false;
        }
        
        if(Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
