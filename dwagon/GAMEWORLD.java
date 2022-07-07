import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//handels fist creation of all objects, difficulty, dwagon x-speed, dev menu and egg icons
public class GAMEWORLD extends World
{
    static int difficulty = 1;      //current difficulty level
    static int difficulty_old = 1;  //difficulty level to check for levelups
    static int xspeed = 3;          //dwagon speed in x direction 
    static int barrier = 0;         //barrier level 
    static int coin = 25;           //score points awarded for collecting a coin 
    static int vchange;             //change of y velocity according to difficulty
    static int coins_collected = 0; 
          
    EGG egg_score = new EGG();      //egg icons behind (high-) score
    EGG egg_highscore = new EGG();
    
    GreenfootSound levelup = new GreenfootSound("Prime_Propert_levelup.wav");
    
    public GAMEWORLD()
    {    
        super(1080 , 720, 1);
        
        addObject(new DWAGON(), 300, 360);
        
        addObject(new SCORE(), 15, 15);
        
        addObject(new TICK(), 15, 15);
        
        addObject(new PIPE(), 480, 300);
        addObject(new PIPE(), 780, 300);
        addObject(new PIPE(), 1080, 300); 
        
        
        TICK.ticks = 0;
        SCORE.score = 0;
        coins_collected = 0;
        //SCORE.p_score = 0;
        
        addObject(egg_score, 1070, 50);
        addObject(egg_highscore, 1070, 80);
        
        STARTSCREEN.backgroundMusic.playLoop();     //playing background music
        STARTSCREEN.backgroundMusic.setVolume(25);
    }
    
    public void act() {      
        if  (STARTSCREEN.dev_mode == false) {
            if (TICK.ticks <= 600) //changing difficulty according to ticks passed 
                difficulty = 1;
            
            if (TICK.ticks <= 1200 && TICK.ticks > 600) 
                difficulty = 2;
            
            if (TICK.ticks == 1800 && TICK.ticks > 1200) 
                difficulty = 3;
            
            if (TICK.ticks == 2400 && TICK.ticks > 1800) 
                difficulty = 4;
            
            if (TICK.ticks == 3000 && TICK.ticks > 2400) 
                difficulty = 5;
        }
        
        else {
            if (Greenfoot.isKeyDown("1"))   //dev function to change difficulty level 
                difficulty = 1;
            
            if (Greenfoot.isKeyDown("2")) 
                difficulty = 2;
            
            if (Greenfoot.isKeyDown("3")) 
                difficulty = 3;
                
            if (Greenfoot.isKeyDown("4")) 
                difficulty = 4;
                
            if (Greenfoot.isKeyDown("5")) 
                difficulty = 5;
        }
        
        
        if (GAMEWORLD.difficulty == 1)  //changing amount of barriers according to difficulty 
            barrier = 0;
        if (GAMEWORLD.difficulty == 2) 
            barrier = 10;
        if (GAMEWORLD.difficulty == 3) 
            barrier = 20;
        if (GAMEWORLD.difficulty == 4) 
            barrier = 35;
        if (GAMEWORLD.difficulty == 5) 
            barrier = 50;
    
        
        if(difficulty != difficulty_old && difficulty != 1){
            levelup.setVolume(20);
            levelup.play();
            difficulty_old = difficulty;
        }
        
        
        if(TICK.ticks/400 < 3)      //changing x speed proportional to the ticks passed
            xspeed = 3;
        else 
            xspeed = TICK.ticks/400;
        
            
        if (GAMEWORLD.difficulty <= 2)  //y acceleration changed by difficulty
            vchange = 0;    
        if (GAMEWORLD.difficulty == 3)
            vchange = -5;
        if (GAMEWORLD.difficulty == 5)
            vchange = -7;   
        
            
        removeObject(egg_score);        //keeping egg icons on the top layer 
        removeObject(egg_highscore);
        addObject(egg_score,1070, 50);
        addObject(egg_highscore, 1070, 70);
    }
}