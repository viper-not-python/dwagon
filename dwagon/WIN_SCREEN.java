import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//handels win display and output for the combination with other levels 
public class WIN_SCREEN extends World
{
    public WIN_SCREEN()
    {    
        super(1080 , 720, 1); 
        
        
        showText( String.valueOf(GAMEWORLD.coins_collected), 500, 540);
        showText("nur lizenzfreie, kostenlose Musik wurde benutzt (Quellen: mixkit.co, bensound.com)", 540, 600);
        
        
        System.out.println("1"); //output for other levels
    }
}
