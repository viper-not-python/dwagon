import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//handeling scores and displays them
public class SCORE extends Actor
{
    static int score = 0;
    //static int p_score = 0;
    static int h_score = 0;
    
    
    public void act() 
    {
        if (h_score <= score) //setting highscore
            h_score = score;
        
            
        getWorld().showText("Score: " + String.valueOf(score), 1000, 50);
        getWorld().showText("Highscore: " + String.valueOf(h_score), 980, 70);
        getWorld().showText("Coins: " + String.valueOf(GAMEWORLD.coins_collected),995, 100);
    }    
}
