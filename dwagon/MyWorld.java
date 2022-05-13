import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    static int difficulty = 1;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 1080 x 720 cells with a cell size of 1x1 pixels.
        super(1080 , 720, 1);
        
        addObject(new DWAGON(), 300, 360);
        
        addObject(new SCORE(), 0, 0);
        
        addObject(new TICK(), 0, 0);
        
        addObject(new PIPE(), 480, 300);
        addObject(new PIPE(), 780, 300);
        addObject(new PIPE(), 1080, 300); 
    }
    
    public void act() {
        if (TICK.ticks == 600) {
            difficulty = 1;
        }
        
        if (TICK.ticks == 1200) {
            difficulty = 2;
        }
        
        if (TICK.ticks == 1800) {
            difficulty = 3;
        }
        
        if (TICK.ticks == 2400) {
            difficulty = 4;
        }
        
        if (TICK.ticks == 3000) {
            difficulty = 5;
        }
    }
}

