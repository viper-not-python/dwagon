import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//egg icon behind the scores 
public class EGG extends SCORE{
    public void act(){
        GreenfootImage egg = new GreenfootImage("Egg.png");
        
        egg.scale(egg.getWidth()/5, egg.getHeight()/5); //scaling the image
        
        setImage(egg);
    }
}
