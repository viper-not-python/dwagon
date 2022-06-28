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
    
    public boolean touch(Actor a_big) {
        Actor a_small;
        if(getImage().getWidth()*getImage().getHeight()>a_big.getImage().getHeight()*a_big.getImage().getWidth())
        //check wich of the pictures of the actors is smaller 
        {
            a_small=a_big;
            a_big=this;     //if the moving object(this-dwagon) is bigger it gets set to a_big 
                            //the object it´s checking if there is an collison with this object is now a_small  (PIPE)
        } 
        else
                a_small=this;   //if the moving object(this-dwagon) is smaller it gets set to a_small
                            //the other one is a_big    (PIPE)

        int i_hypot=(int)Math.hypot(a_small.getImage().getWidth(),a_small.getImage().getHeight());
        //lenght of the diagonal of the smaller object

        GreenfootImage i=new GreenfootImage(i_hypot,i_hypot);   
        //creates an transparent square with its side length being the length of the diagonal of the smaller object 
        i.drawImage(a_small.getImage(),i_hypot/2-a_small.getImage().getWidth()/2,i_hypot/2-a_small.getImage().getHeight()/2); 
        //create a new small image positioned a little inside the original a_small picture  
        i.rotate(a_small.getRotation());
        int w=i_hypot;
        //rotate the newly created picture i the same way a_small is rotated 

        GreenfootImage Ai = a_big.getImage(),
        i2=new GreenfootImage(i_hypot=(int)Math.hypot(Ai.getWidth(),Ai.getHeight()),i_hypot);
        i2.drawImage(Ai,i2.getWidth()/2-Ai.getWidth()/2,i2.getHeight()/2-Ai.getHeight()/2); 
        i2.rotate(a_big.getRotation());
        Ai=i2;
        //doing the same to the second bigger picture Ai is the moved version of a_big
        int
        x_Offset=a_big.getX()-a_small.getX()-(Ai.getWidth()/2-w/2),
        y_Offset=a_big.getY()-a_small.getY()-(Ai.getHeight()/2-w/2);

        boolean b = true;
        for(int yi =Math.max(0,y_Offset); yi<w && yi<i_hypot+y_Offset && b; yi++)
            for(int xi =Math.max(0,x_Offset); xi<w && xi<i_hypot+x_Offset && b; xi++)
                if(Ai.getColorAt(xi-x_Offset,yi-y_Offset).getAlpha()>0 && i.getColorAt(xi,yi).getAlpha()>0)
                    b=false;
        return !b;
    }
}
