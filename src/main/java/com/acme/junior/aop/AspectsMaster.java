package com.acme.junior.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.acme.junior.App;
import com.acme.junior.horrorhouse.TheMaster;
import com.acme.junior.player.Group;


@Aspect
public class AspectsMaster {
   
    
    @Before("execution(* com.acme.junior.horrorhouse.TheMaster.removeGroupAsWinner(..))")
    public void removeGroupAsWinnerBefore(JoinPoint point) {
        Object[] args=point.getArgs();
        TheMaster master=(TheMaster) point.getTarget();
        
        App.logger.info("Master remove the "+master.getGroups().get(((Integer)args[0]))+" group to the list.");
    }
    
    
    @Before("execution(* com.acme.junior.horrorhouse.TheMaster.run())")
    public void runBefore(JoinPoint point) {
     
        App.logger.info("Master begin the game.");
    }
    
    @After("execution(* com.acme.junior.horrorhouse.TheMaster.run())")
    public void runAfter(JoinPoint point) {
       
       
        App.logger.info("Master end the game");
    }
    
    @Before("execution(* com.acme.junior.horrorhouse.TheMaster.setFirstRoom(..))")
    public void setFirstRoomBefore(JoinPoint point) {
       
        Object[] args=point.getArgs();
        Group group=(Group)args[0];
        App.logger.info("Master begin the "+ group.getName() +" group in the game");
    }
    
   
    
}
