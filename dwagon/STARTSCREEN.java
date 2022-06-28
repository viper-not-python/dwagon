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
    static boolean dev_mode = false;
    
    static GreenfootSound backgroundMusic = new GreenfootSound("background_music.wav");    //background music
    
    public STARTSCREEN()
    {    
        // Create a new world with 1080 x 720 cells with a cell size of 1x1 pixels.
        super(1080 , 720, 1); 
        
        backgroundMusic.playLoop();
        backgroundMusic.setVolume(25);
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("9")) {
            dev_mode = true;
        }
        
        if(Greenfoot.isKeyDown("8")) {
            dev_mode = false;
        }
        
        if(Greenfoot.isKeyDown("space")) {
            MyWorld.difficulty = 1;
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
