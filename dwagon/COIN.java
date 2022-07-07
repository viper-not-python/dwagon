import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//handels coin animation and coin collection/destruction
public class COIN extends HITBOX_CHECK
{
    int ticks_old = 0;
    int tick_delay = 7;
    int image = 1;
    
    
    GreenfootImage coin_eins = new GreenfootImage("images/coin_eins.png");
    GreenfootImage coin_zwei = new GreenfootImage("images/coin_zwei.png");
    GreenfootImage coin_drei = new GreenfootImage("images/coin_drei.png");
    GreenfootImage coin_vier = new GreenfootImage("images/coin_vier.png");
    GreenfootImage coin_fuenf = new GreenfootImage("images/coin_fuenf.png");
    GreenfootImage coin_sechs = new GreenfootImage("images/coin_sechs.png");
    
    
    GreenfootSound coin = new GreenfootSound("coin.wav");
    
    public void act()
    {        
        if (image == 1 && TICK.ticks >= ticks_old + tick_delay) {
            setImage(coin_eins);
            image++;
            ticks_old = TICK.ticks;
        }
        if (image == 2 && TICK.ticks >= ticks_old + tick_delay) {
            setImage(coin_zwei);
            image++;
            ticks_old = TICK.ticks;
        }
        if (image == 3 && TICK.ticks >= ticks_old + tick_delay) {
            setImage(coin_drei);
            image++;
            ticks_old = TICK.ticks;
        }
        if (image == 4 && TICK.ticks >= ticks_old + tick_delay) {
            setImage(coin_vier);
            image++;
            ticks_old = TICK.ticks;
        }
        if (image == 5 && TICK.ticks >= ticks_old + tick_delay) {
            setImage(coin_fuenf);
            image++;
            ticks_old = TICK.ticks;
        }
        if (image == 6 && TICK.ticks >= ticks_old + tick_delay) {
            setImage(coin_sechs);
            image = 1;
            ticks_old = TICK.ticks;
        }
        
        
        if (DWAGON.isAlive())
            setLocation(getX() - GAMEWORLD.xspeed, getY());
        
        
        if (isTouching(DWAGON.class) || isTouching(PROJECTILE.class) || isAtEdge() == true) {
            if (isTouching(DWAGON.class)) {
                GAMEWORLD.coins_collected++;
                SCORE.score = SCORE.score + 50;
                coin.setVolume(60);
                coin.play();
            }
            getWorld().removeObject(this);
        }
    }
}
