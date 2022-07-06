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
    static int difficulty_old = 1;
    static int xspeed = 3;
    static int barrier = 0;
    static int coin = 25;
    static int vchange;    //change of velocity according to difficulty
    static int coins_collected = 0;
    EGG egg_score = new EGG();
    EGG egg_highscore = new EGG();
    GreenfootSound levelup = new GreenfootSound("Prime_Propert_levelup.wav");
    
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 1080 x 720 cells with a cell size of 1x1 pixels.
        super(1080 , 720, 1);
        
        addObject(new DWAGON(), 300, 360);
        
        addObject(new SCORE(), 15, 15);
        
        
        addObject(new TICK(), 15, 15);
        
        addObject(new PIPE(), 480, 300);
        addObject(new PIPE(), 780, 300);
        addObject(new PIPE(), 1080, 300); 
        
        TICK.ticks = 0;
        
        SCORE.score = 0;
        //SCORE.p_score = 0;
        
        addObject(egg_score, 1070, 50);
        addObject(egg_highscore, 1070, 80);
        
        STARTSCREEN.backgroundMusic.playLoop();
        STARTSCREEN.backgroundMusic.setVolume(25);
    }
    
    public void act() {      
        if  (STARTSCREEN.dev_mode == false) {
            if (TICK.ticks <= 600) {
                difficulty = 1;
            }
            
            if (TICK.ticks <= 1200 && TICK.ticks > 600) {
                difficulty = 2;
            }
            
            if (TICK.ticks == 1800 && TICK.ticks > 1200) {
                difficulty = 3;
            }
            
            if (TICK.ticks == 2400 && TICK.ticks > 1800) {
                difficulty = 4;
            }
            
            if (TICK.ticks == 3000 && TICK.ticks > 2400) {
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
        
        if (MyWorld.difficulty == 1) {
            barrier = 0;
        }
        
        if (MyWorld.difficulty == 2) {
            barrier = 10;
        }
        
        if (MyWorld.difficulty == 3) {
            barrier = 20;
        }
        
        if (MyWorld.difficulty == 4) {
            barrier = 35;
        }
        
        if (MyWorld.difficulty == 5) {
            barrier = 50;
        }
        
        if(difficulty != difficulty_old){
            levelup.setVolume(20);
            levelup.play();
            difficulty_old = difficulty;
        }
        
        if(TICK.ticks/400 < 3)
            xspeed = 3;
        else 
            xspeed = TICK.ticks/400;
        
        if (MyWorld.difficulty <= 2){
            vchange = 0;    //negative velocity change for upwards movement and downward acceleration
        }
        
        if (MyWorld.difficulty == 3){
            vchange = -5;    //negative velocity change for upwards movement and downward acceleration
        }
        
        if (MyWorld.difficulty == 5){
            vchange = -7;    //negative velocity change for upwards movement and downward acceleration
        }
        removeObject(egg_score);
        removeObject(egg_highscore);
        addObject(egg_score,1070, 50);
        addObject(egg_highscore, 1070, 70);
    }
}
//That is usually caused by information tags being saved within your sound file.  Use a sound editor and re-save the file without the tags.

