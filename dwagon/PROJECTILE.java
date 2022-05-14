import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class PROJECTILE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PROJECTILE extends HITBOX_CHECK
{
    /**
     * Act - do whatever the PROJECTILE wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
       
    GreenfootImage projectile = new GreenfootImage("images/projectile.png");
    GreenfootImage projectile_exploded = new GreenfootImage("images/projectile_exploded.png");
    
    int count = 0;
    
    public PROJECTILE() {
        projectile.scale(projectile.getWidth()/5,projectile.getHeight()/5);
        projectile_exploded.scale(projectile_exploded.getWidth()/10,projectile_exploded.getHeight()/10);
        setImage(projectile);
    }
    
    public void act()
    {
        if (count == 0) {
            if (DWAGON.isAlive()){
                setLocation(getX() + 15, getY());
            }
            
            if (isAtEdge() == true || touch(PIPE.class)){
                setImage(projectile_exploded);
                count = 5;
            }
        }
        else {
            if (count == 5) {
                getWorld().removeObject(this);
            }
            else {
                count ++;
            }
        }
    }
}
