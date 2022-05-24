import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GAME_OVER here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GAME_OVER extends World
{

    /**
     * Constructor for objects of class GAME_OVER.
     * 
     */
    boolean delay = true;
    
    public GAME_OVER()
    {    
        // Create a new world with 1080 x 720 cells with a cell size of 1x1 pixels.
        super(1080 , 720, 1);
        
        SCORE.score = SCORE.score - 1;
        
        if (SCORE.score >= SCORE.h_score) {
            showText("GAME OVER", 540, 200);
            
            showText("New Highscore!", 540, 250);
            
            showText("Highscore: " + String.valueOf(SCORE.h_score), 540, 300);
        
            showText("Score: " + String.valueOf(SCORE.score), 540, 330);
        }
        
        else {
            showText("Highscore: " + String.valueOf(SCORE.h_score), 540, 250);
        
            showText("Score: " + String.valueOf(SCORE.score - 1), 540, 280);
        
            showText("GAME OVER", 540, 200);
        }
    }
    
    public void act() {
        if (delay == false) {
            if(Greenfoot.isKeyDown("space")) {
                MyWorld.difficulty = 1;
                Greenfoot.setWorld(new MyWorld());
            }
        }
        else {
            Greenfoot.delay(50);
            delay = false;
        }
    }
}
