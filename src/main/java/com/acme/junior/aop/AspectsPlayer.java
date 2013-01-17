package com.acme.junior.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.acme.junior.App;
import com.acme.junior.player.Player;

@Aspect
public class AspectsPlayer {

    
    

    
    @Before("execution(* com.acme.junior.player.Player.run())")
    public void playerRunBefore(JoinPoint point) {
        Player player=(Player)point.getTarget();
        
        App.logger.info(player.getName()+" begin running");
    }
    
    @After("execution(* com.acme.junior.player.Player.run())")
    public void playerRunAfter(JoinPoint point) {
        Player player=(Player)point.getTarget();
        
        App.logger.info(player.getName()+" end running");
    }
    
    @Before("execution(* com.acme.junior.player.Player.moveBoring())")
    public void playerBoringBefore(JoinPoint point) {
        Player player=(Player)point.getTarget();
        
        App.logger.info(player.getName()+" is boring");
    }
    
    @After("execution(* com.acme.junior.player.Player.moveBoring())")
    public void playerBoringAfter(JoinPoint point) {
        Player player=(Player)point.getTarget();
        
        App.logger.info(player.getName()+" is in the game again");
    }
    
    
    @Before("execution(* com.acme.junior.player.Player.shocking())")
    public void playerShockingBefore(JoinPoint point) {
        Player player=(Player)point.getTarget();
        
        App.logger.info(player.getName()+" is shoocking");
    }
    
    @After("execution(* com.acme.junior.player.Player.shocking())")
    public void playerShockingAfter(JoinPoint point) {
        Player player=(Player)point.getTarget();
        
        App.logger.info(player.getName()+" he is fresh again");
    }
    
    
    
    
}
