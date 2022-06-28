import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WIN_SCREEN here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WIN_SCREEN extends World
{

    /**
     * Constructor for objects of class WIN_SCREEN.
     * 
     */
    public WIN_SCREEN()
    {    
        // Create a new world with 1080 x 720 cells with a cell size of 1x1 pixels.
        super(1080 , 720, 1); 
        showText( String.valueOf(MyWorld.coins_collected), 500, 540);
        
        showText("nur lizenzfreie, kostenlose Musik wurde benutzt (Quellen: mixkit.co, bensound.com)", 540, 600);
        
    }
}
