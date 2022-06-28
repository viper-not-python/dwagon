import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EGG here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EGG extends SCORE
{
    
    /**
     * Act - do whatever the EGG wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        GreenfootImage egg = new GreenfootImage("Egg.png");
        egg.scale(egg.getWidth()/5, egg.getHeight()/5);
        setImage(egg);
        
    }
}
