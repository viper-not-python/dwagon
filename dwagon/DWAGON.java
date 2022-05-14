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
    boolean pressed_enter = false;
    boolean count_shift = false;
    boolean count_enter = false;
    boolean dash_enabled = true;   //enables dash
    static boolean dead = false;
    boolean touching_pipe_old = false;
    int h;  //counted ticks
    int t;  //counted ticks
    int vchange;    //change of velocity according to difficulty
    int dash;   //change of pixels dashed according to difficulty
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
        int vint = (int)v / 5 - vchange / 3; //converting double to int and adding vchange to match difficulty      
        setLocation(getX(), getY() + vint); //downwards movement
        
        boolean touching_pipe = false;
        
        for(PIPE pipe : getWorld().getObjects(PIPE.class)){
            if(Math.abs(pipe.getX() - getX()) < 90){
                dash_enabled = false;
            }
            dash_enabled = true;
            touching_pipe = true;
        }
        
        touching_pipe_old = touching_pipe;
        
        if (isAlive()) {    //adds score per act
            SCORE.score++;
        }
        
        if (touching_pipe_old == false || touching_pipe == true || isAlive()) {
            SCORE.p_score++;
        }
        
        if(touch(PIPE.class) == true && STARTSCREEN.dev_mode == false){
            dead = true;
        }
        
        if (count_enter == true) { 
            if (h == 15) {  //counts 1/4 sec ==> cooldown for dash()
                pressed_enter = false;  //resets bool pressed_shift
                count_enter = false; //resets bool count_shift
                h = 0;
            }
            h++;
        }
        
        if (count_shift == true) { 
            if (t == 60) {  //counts 1 sec ==> cooldown for dash()
                pressed_shift = false;  //resets bool pressed_shift
                count_shift = false; //resets bool count_shift
                t = 0;
            }
            t++;
        }
        
        if (pressed_enter == false && isAlive() == true) {  //checks booleans
            if (Greenfoot.isKeyDown("enter")) { //checks state of enter button
                shoot();
                pressed_enter = true;
                count_enter = true;
            }  
        }
        
        if (MyWorld.difficulty <= 2){
            vchange = 0;    //negative velocity change for upwards movement and downward acceleration
        }
        
        if (MyWorld.difficulty == 3){
            vchange = -5;    //negative velocity change for upwards movement and downward acceleration
        }
        
        if (MyWorld.difficulty == 5){
            vchange = -7;    //negative velocity change for upwards movement and downward acceleration
        }
        
        if (Greenfoot.isKeyDown("space") && isAlive() == true) { //checks state of space button
            boost();
        }
        
        if (pressed_shift == false && isAlive() == true && dash_enabled == true) {   //checks booleans
            if (Greenfoot.isKeyDown("shift")) { //checks state of shift button
                dash();
                pressed_shift = true;
                count_shift = true;
            }
        }
                
        if (isAtEdge() && STARTSCREEN.dev_mode == false) {  //checks if touched the ground or flew to high
            dead = true;           
        }
        
        if (dead == true) { //checks for condition to stop the game
            Greenfoot.stop();   //stops the game
        }
        
        dead = false;     
    }
        
    private void shoot() {
        World world = getWorld(); //to access WORLD class methods
        world.addObject(new PROJECTILE(), getX() + 55, getY() + 18);
    }
    
    private void boost() {        
        v = -25 + vchange;
    }
    
    private void dash() {
        if (MyWorld.difficulty <= 2){
            dash = 100;
        }
        
        if (MyWorld.difficulty >= 3 && MyWorld.difficulty < 5){
            dash = 130;
        }
        
        if (MyWorld.difficulty == 5){
            dash = 150;
        }
        
        setLocation(getX(), getY() + dash);
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
