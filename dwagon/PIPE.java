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
    int j = 0;
    
    public PIPE() {
        GreenfootImage pipes = new GreenfootImage("images/pipes.png");
        pipes.scale(pipes.getWidth()/2,pipes.getHeight()/2);
        setImage(pipes);
    }
    public void act()
    {       
        World world = getWorld();
        
        if(DWAGON.isAlive()){
            setLocation(getX() - 3, getY());
        }
        if(getX() <= 1){
            world.addObject(new PIPE(), 702, 250 + Greenfoot.getRandomNumber(250));
            getWorld().removeObject(this);
        }
    }
}


