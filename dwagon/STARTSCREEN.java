import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//handels dev mode, background music, start display and starting  gameworld 
public class STARTSCREEN extends World
{
    static boolean dev_mode = false;
    
    static GreenfootSound backgroundMusic = new GreenfootSound("background_music.wav");    //background music
    
    public STARTSCREEN()
    {    
        super(1080 , 720, 1); 
        
        
        backgroundMusic.playLoop();
        backgroundMusic.setVolume(25);
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("9"))    //enable or disable dev mode 
            dev_mode = true;
        if(Greenfoot.isKeyDown("8")) 
            dev_mode = false;
        
        
        if(Greenfoot.isKeyDown("space"))     //start game
            Greenfoot.setWorld(new GAMEWORLD());
    }
}
