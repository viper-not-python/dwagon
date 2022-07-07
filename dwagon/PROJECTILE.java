import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

//handels function of projectiles 
public class PROJECTILE extends HITBOX_CHECK
{ 
    int count = 0;
    
    
    GreenfootImage projectile = new GreenfootImage("images/projectile.png");
    GreenfootImage projectile_exploded = new GreenfootImage("images/projectile_exploded.png");
    
    
    public PROJECTILE() {
        projectile.scale(projectile.getWidth()/5,projectile.getHeight()/5); //scaling the images 
        projectile_exploded.scale(projectile_exploded.getWidth()/10,projectile_exploded.getHeight()/10);
        
        setImage(projectile);
    }
    
    
    public void act()
    {
        if (count == 0) {
            if (DWAGON.isAlive())
                setLocation(getX() + 15, getY());
            
            
            if (isAtEdge() == true || touch(PIPE.class)){
                setImage(projectile_exploded);
                count = 5;
            }
        }
        else {
            if (count == 5) 
                getWorld().removeObject(this);
            else 
                count ++;
        }
    }
}
