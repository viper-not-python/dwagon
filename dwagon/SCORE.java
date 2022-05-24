import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SCORE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SCORE extends Actor
{
    static int score = 0;
    static int p_score = 0;
    static int h_score = 0;
    /**
     * Act - do whatever the SCORE wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        World world = getWorld();
        world.showText("Score: " + String.valueOf(score), 300, 100);
        
        if (h_score <= score) {
            h_score = score;
        }
        
        world.showText("Highscore: " + String.valueOf(h_score), 300, 150);
    }    
}
