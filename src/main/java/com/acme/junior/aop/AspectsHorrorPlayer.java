package com.acme.junior.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.acme.junior.App;
import com.acme.junior.horrorhouse.HorrorPlayer;


@Aspect
public class AspectsHorrorPlayer {

    
   
    
    @Before("execution(* com.acme.junior.horrorhouse.HorrorPlayer.goToSmoking())")
    public void playergoToSmokingBefore(JoinPoint point) {
        HorrorPlayer player=(HorrorPlayer)point.getTarget();
        
        App.logger.info(player.getName()+" cigiszunetre ment ki");
    }
    
    @After("execution(* com.acme.junior.horrorhouse.HorrorPlayer.goToSmoking())")
    public void playergoToSmokingAfter(JoinPoint point) {
        HorrorPlayer player=(HorrorPlayer)point.getTarget();
        
        App.logger.info(player.getName()+" cigiszunetröl jött vissza");
    }
    
    @Before("execution(* com.acme.junior.horrorhouse.HorrorPlayer.run())")
    public void playerRunBefore(JoinPoint point) {
        HorrorPlayer player=(HorrorPlayer)point.getTarget();
        
        App.logger.info(player.getName()+" alkezdett ijesztegetni");
    }
    
    @After("execution(* com.acme.junior.horrorhouse.HorrorPlayer.run())")
    public void playerRunAfter(JoinPoint point) {
        HorrorPlayer player=(HorrorPlayer)point.getTarget();
        
        App.logger.info(player.getName()+" végzett az ijesztegetéssel");
    }
    
    
}
