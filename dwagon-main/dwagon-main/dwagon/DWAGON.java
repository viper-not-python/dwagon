import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class DWAGON here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DWAGON extends Actor 
{
    double a = 1.0001;    //acceleration
    double v;   //velocity
    double x;    //x-value
    boolean pressed_shift = false;
    boolean count = false;
    boolean dash_enabled = true;   //enables dash
    static boolean dead = false;
    boolean touching_pipe_old = false;
    int t;  //counted ticks
    GreenfootImage dwagon_down = new GreenfootImage("images/dwagon_Wings_down.png");
    GreenfootImage dwagon_middle = new GreenfootImage("images/dwagon_Wings_middle.png");
    GreenfootImage dwagon_up = new GreenfootImage("images/dwagon_Wings_up.png");
    
    /**
     * Act - do whatever the dwagon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public DWAGON() {
        dwagon_down.scale(dwagon_down.getWidth()/2,dwagon_down.getHeight()/2);  //scaling the image
        dwagon_middle.scale(dwagon_middle.getWidth()/2,dwagon_middle.getHeight()/2);
        dwagon_up.scale(dwagon_up.getWidth()/2,dwagon_up.getHeight()/2);
    }
    
    public void act() 
    {                
        if (v <= -15) { //different images for different velocities
            setImage(dwagon_down);
        }
        
        if (v >-15 || v >= 15) {
            setImage(dwagon_middle);
        }
        
        if (v > 15) {
            setImage(dwagon_up);
        }
        
        v = (v + a);
        int vint = (int)v / 5; //converting double to int       
        setLocation(getX(), getY() + vint); //downwards movement
        
        if(touch(PIPE.class)){
            dead = true;
        }
        
        if (count == true) { 
            if (t == 60) {  //counts 1 sec ==> cooldown for dash()
                pressed_shift = false;  //resets bool pressed_shift
                count = false; //resets bool count
                t = 0;
            }
            t++;
        }
        
        if (Greenfoot.isKeyDown("space")) { //checks state of space button
            boost();
        }
        
        if (pressed_shift == false && dash_enabled == true) {   //checks booleans
            if (Greenfoot.isKeyDown("shift")) { //checks state of shift button
                dash();
                pressed_shift = true;
                count = true;
            }
        }
                
        if (isAtEdge()) {  //checks if touched the ground or flew to high
            dead = true;           
        }
        
        if (dead == true) { //checks for condition to stop the game
            Greenfoot.stop();   //stops the game
        }
        
        dead = false;     
    }
        
    private void boost() {
        v = -25;    //negative velocity for upwards movement
    }
    
    private void dash() {
        setLocation(getX(), getY() + 100);   //downwards movement
    }
    
    public static boolean isAlive(){
        return !dead;
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
    
    public boolean touch(Actor a_big)
    {
        Actor a_small;
        if(getImage().getWidth()*getImage().getHeight()>a_big.getImage().getHeight()*a_big.getImage().getWidth())
        {
            a_small=a_big;
            a_big=this;
        }
        else
            a_small=this;

        int i_hypot=(int)Math.hypot(a_small.getImage().getWidth(),a_small.getImage().getHeight());

        GreenfootImage i=new GreenfootImage(i_hypot,i_hypot);
        i.drawImage(a_small.getImage(),i_hypot/2-a_small.getImage().getWidth()/2,i_hypot/2-a_small.getImage().getHeight()/2);
        i.rotate(a_small.getRotation());
        int w=i_hypot;

        GreenfootImage Ai = a_big.getImage(),
        i2=new GreenfootImage(i_hypot=(int)Math.hypot(Ai.getWidth(),Ai.getHeight()),i_hypot);
        i2.drawImage(Ai,i2.getWidth()/2-Ai.getWidth()/2,i2.getHeight()/2-Ai.getHeight()/2);
        i2.rotate(a_big.getRotation());
        Ai=i2;

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
