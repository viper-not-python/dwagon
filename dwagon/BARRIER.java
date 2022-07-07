import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//handels barrier destruction
public class BARRIER extends HITBOX_CHECK
{  
    GreenfootImage barrier_image = new GreenfootImage("images/barrier.png");
    
    
    GreenfootSound destroy = new GreenfootSound("destroy.wav");
    
    
    public BARRIER() {
        setImage(barrier_image);
    }
    
    public void act()
    {
        if (DWAGON.isAlive())
            setLocation(getX() - GAMEWORLD.xspeed, getY());
        
        
        if (isTouching(PROJECTILE.class)) {
            getWorld().removeObject(this);
            destroy.setVolume(40);
            destroy.play();
        }
    }
}
