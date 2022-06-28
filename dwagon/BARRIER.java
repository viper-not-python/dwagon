import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BARRIER here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BARRIER extends HITBOX_CHECK
{
    /**
     * Act - do whatever the BARRIER wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */    
    GreenfootImage barrier_image = new GreenfootImage("images/barrier.png");
    GreenfootSound destroy = new GreenfootSound("destroy.mp3");
    
    public BARRIER() {
        setImage(barrier_image);
    }
    
    public void act()
    {
        if (DWAGON.isAlive()){
            setLocation(getX() - MyWorld.xspeed, getY());
        }
        
        if (touch(PROJECTILE.class)) {
            getWorld().removeObject(this);
            destroy.setVolume(40);
            destroy.play();
        }
    }
}
