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
    static int xspeed = 3;
    static int barrier = 0;
    static int vchange;    //change of velocity according to difficulty
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
        if  (STARTSCREEN.dev_mode == false) {
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
        
        else {
            if (Greenfoot.isKeyDown("1")) {
                difficulty = 1;
            }
            
            if (Greenfoot.isKeyDown("2")) {
                difficulty = 2;
            }
            
            if (Greenfoot.isKeyDown("3")) {
                difficulty = 3;
            }
            
            if (Greenfoot.isKeyDown("4")) {
                difficulty = 4;
            }
            
            if (Greenfoot.isKeyDown("5")) {
                difficulty = 5;
            }            
        }
        
        if (MyWorld.difficulty == 1) {  //speed according to difficulty
            xspeed = 3;
            barrier = 0;
        }
        
        if (MyWorld.difficulty == 2) {
            xspeed = 5;
            barrier = 10;
        }
        
        if (MyWorld.difficulty == 3) {
            xspeed = 7;
            barrier = 20;
        }
        
        if (MyWorld.difficulty == 4) {
            xspeed = 8;
            barrier = 35;
        }
        
        if (MyWorld.difficulty == 5) {
            xspeed = 9;
            barrier = 50;
        }
        
        if (MyWorld.difficulty <= 2){
            vchange = 0;    //negative velocity change for upwards movement and downward acceleration
        }
        
        if (MyWorld.difficulty == 3){
            vchange = -5;    //negative velocity change for upwards movement and downward acceleration
        }
        
        if (MyWorld.difficulty == 5){
            vchange = -7;    //negative velocity change for upwards movement and downward acceleration
        }
    }
}

