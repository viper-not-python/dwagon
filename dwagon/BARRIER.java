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
    Actor c_pipe2;
    
    public BARRIER(Actor c_pipe) {
        c_pipe2 = c_pipe;
    }
    
    public void act()
    {
        if (DWAGON.isAlive()){
            setLocation(getX() - MyWorld.xspeed, getY());
        }
        
        if (touch(PROJECTILE.class)) {
            getWorld().removeObject(this);
        }
    }
}
