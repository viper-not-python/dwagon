import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PIPE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PIPE extends Actor
{
    /**
     * Act - do whatever the PIPE wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public PIPE() {
        GreenfootImage pipe_down = new GreenfootImage("images/pipe_down.png");
        pipe_down.scale(pipe_down.getWidth()/30,pipe_down.getHeight()/25);
        setImage(pipe_down);
    }
    public void act()
    {
        if(DWAGON.isAlive()){
            setLocation(getX() - 2, getY());
        }
        if(getX() <= 1){
            setLocation(getX() + 700, 720 + Greenfoot.getRandomNumber(500));
        }
    }
}
