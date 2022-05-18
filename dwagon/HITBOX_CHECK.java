import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class HITBOX_CHECK here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HITBOX_CHECK extends Actor
{
    /**
     * Act - do whatever the HITBOX_CHECK wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    public boolean touch(Class clss)
    {
        List<Actor> list =
            getWorld().getObjects(clss),
        list2 = new ArrayList();
        for(Actor A : list)
            if(A!=this && intersects(A) && touch(A))
                return true;
        return false;
    }
    
    public boolean touch(Actor a2) {
        Actor a1 = this;
        
        GreenfootImage a1_Image = a1.getImage();
        GreenfootImage a2_Image = a2.getImage();
        
        int x_offset = a2.getX() - a1.getX();
        int y_offset = a2.getY() - a1.getY();
        
        int x_size = a2_Image.getWidth()/2 + a1_Image.getWidth()/2 - x_offset;
        int y_size = a2_Image.getHeight()/2 + a1_Image.getHeight()/2 - y_offset;
        
        int a1_intersect_top_x = x_offset;
        int a1_intersect_top_y = Math.max(a2_Image.getHeight() - y_offset, 0);
        
        int a2_intersect_top_x = 0;
        int a2_intersect_top_y = Math.max(y_offset + a1_Image.getHeight()/2 - a2_Image.getHeight()/2, 0);
        System.out.println(a1_Image.getWidth() + "       " + a2_Image.getWidth());
        if(a1_intersect_top_x < a1_Image.getWidth())
        for(int x_check = 0; x_check < x_size; x_check++)
            for(int y_check = 0; y_check < y_size; y_check++)
                if(a1_Image.getColorAt(a1_intersect_top_x ,a1_intersect_top_y).getAlpha() > 0)
                    if(a2_Image.getColorAt(a2_intersect_top_x,a2_intersect_top_y - a2_Image.getHeight()).getAlpha() > 0)
                        return true;
        
        return false;
    }
}
