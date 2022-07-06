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
            if(A!=this  && intersects(A) && touch(A))
                return true;
        return false;
    }
    
    public boolean touch(Actor a_big) {
        //check which actor has the smaller Image to reduce workload
        Actor a_small;
        if(getImage().getWidth()*getImage().getHeight()>a_big.getImage().getHeight()*a_big.getImage().getWidth()){
            a_small=a_big;
            a_big=this;     
        } 
        else
                a_small=this;
          
        //topleft coordinate of the image in the main coord system 
        int topleft_a_small_x = a_small.getX()-(a_small.getImage().getWidth()/2);
        int topleft_a_small_y = a_small.getY()-(a_small.getImage().getHeight()/2);

        //conversion values to get coords of checking point in coord system of a_big 
        int conv_s_to_b_x = topleft_a_small_x - (a_big.getX()-(a_big.getImage().getWidth()/2));
        int conv_s_to_b_y = topleft_a_small_y - (a_big.getY()-(a_big.getImage().getHeight()/2));
        
        for(int x_check = 0; x_check < (a_small.getImage().getWidth()-1) && (x_check < a_big.getImage().getWidth()-1); x_check++){
            for(int y_check = 0; y_check < (a_small.getImage().getHeight()-1) && y_check < (a_big.getImage().getHeight()-1); y_check++){
                if((conv_s_to_b_x + x_check) >= 0 && (conv_s_to_b_y + y_check) >= 0){
                    if(a_small.getImage().getColorAt(x_check, y_check).getAlpha() > 0){
                        if((conv_s_to_b_x + x_check) < (a_big.getImage().getWidth()-2) && (conv_s_to_b_x + y_check) < (a_big.getImage().getHeight()-2)){
                            if(a_big.getImage().getColorAt((x_check + conv_s_to_b_x) , (y_check + conv_s_to_b_y)).getAlpha() > 0)
                                return true;
                            //Check each pixel of the smaller image if it is colored and if a pixel of the bigger picture in the same location is colored
                        }
                    }
                }
            }
        }
        return false;
        
    }
}
