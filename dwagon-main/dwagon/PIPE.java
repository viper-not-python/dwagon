import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PIPE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PIPE extends HITBOX_CHECK
{
    /**
     * Act - do whatever the PIPE wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int j = 0;
    int k = 0;
    boolean barrier_created = false;
    boolean coin_created = false;
    boolean checked_chance = false;
    boolean coin_checked_chance = false;
    
    public PIPE() {
        GreenfootImage pipes = new GreenfootImage("images/pipes.png");
        pipes.scale(pipes.getWidth()/2,pipes.getHeight()/2);
        setImage(pipes);
    }
    public void act()
    {       
        World world = getWorld();
        
        if (DWAGON.isAlive()){
            setLocation(getX() - MyWorld.xspeed, getY());
        }
        
        if (getX() <= 200){ //creates new pipe
            if (k == 0) {
                world.addObject(new PIPE(), 1200, 250 + Greenfoot.getRandomNumber(250));
                k++;
            }
        }
            
        if (getX() <=1){    //deletes itself and the created barrier
                world.removeObject(this);
        }
        
        if (Greenfoot.getRandomNumber(99) + 1 <= MyWorld.barrier && barrier_created == false && checked_chance == false) { //generates chance for barrier creation dependent on int barrier
            world.addObject(new BARRIER(), getX(), getY());
            barrier_created = true;
            checked_chance = true;
            GreenfootImage pipes_wood = new GreenfootImage("images/pipes_wood.png");
            pipes_wood.scale(pipes_wood.getWidth()/2,pipes_wood.getHeight()/2);
            setImage(pipes_wood);
        }
        else {
            checked_chance = true;
        }
        
        if (Greenfoot.getRandomNumber(99) + 1 <= MyWorld.coin && coin_created == false && coin_checked_chance == false && barrier_created == false) { //generates chance for barrier creation dependent on int barrier
            world.addObject(new COIN(), getX(), getY());
            coin_created = true;
            coin_checked_chance = true;
            GreenfootImage pipes_stone = new GreenfootImage("images/pipes_stone.png");
            pipes_stone.scale(pipes_stone.getWidth()/2,pipes_stone.getHeight()/2);
            setImage(pipes_stone);
        }
        else {
            coin_checked_chance = true;
        }
        
        
    }
}


