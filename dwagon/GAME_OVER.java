import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//handels gameover dispay and restarting gameworld 
public class GAME_OVER extends World
{
    boolean delay = true;
    
    public GAME_OVER()
    {    
        super(1080 , 720, 1);
        
        SCORE.score = SCORE.score - 1;
        
        if (SCORE.score >= SCORE.h_score) {
            showText("GAME OVER", 540, 200);
            showText("New Highscore!", 540, 250);
            showText("Highscore: " + String.valueOf(SCORE.h_score), 540, 300);
            showText("Score: " + String.valueOf(SCORE.score), 540, 330);
            showText("nur lizenzfreie, kostenlose Musik wurde benutzt (Quellen: mixkit.co, bensound.com)", 540, 380);
        }
        
        else {
            showText("Highscore: " + String.valueOf(SCORE.h_score), 540, 250);
            showText("Score: " + String.valueOf(SCORE.score - 1), 540, 280);
            showText("GAME OVER", 540, 200);
            showText("nur lizenzfreie, kostenlose Musik wurde benutzt (Quellen: mixkit.co, bensound.com)", 540, 380);
        }
    }
    
    public void act() {
        if (!delay) {
            if(Greenfoot.isKeyDown("space")) {
                GAMEWORLD.difficulty = 1;
                Greenfoot.setWorld(new GAMEWORLD());
            }
        }
        else {
            Greenfoot.delay(50);
            delay = false;
        }
    }
}
