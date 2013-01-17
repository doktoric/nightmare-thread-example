package com.acme.junior.player;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.junior.horrorhouse.House;

public class Player implements Runnable {

    public static final int SCARY_INT = 5;
    public static final long WAIT_TIME = 200;

    private AtomicBoolean isShooked = new AtomicBoolean(false);
    private AtomicInteger scary = new AtomicInteger(SCARY_INT);
    private AtomicLong waitTime = new AtomicLong(0);
    private volatile Room actRoom = null;
    private String name = "NONAME";
    private volatile Group group = null;
    private volatile boolean isBoring = false;

    public Group getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Player(String name) {

        this.name = name;
    }

    public Player(String name, Group group) {
        this.group = group;
        this.name = name;
    }

    public Player() {

    }

    public synchronized Room getActRoom() {
        return actRoom;
    }

    public synchronized void setActRoom(Room actRoom) {
        this.actRoom = actRoom;
    }

    public synchronized AtomicBoolean getIsShooked() {
        return isShooked;
    }

    public synchronized void setIsShooked(AtomicBoolean isShooked) {
        this.isShooked = isShooked;
    }

    public synchronized AtomicInteger getScary() {
        return scary;
    }

    public synchronized void setScary(AtomicInteger scary) {
        this.scary = scary;
    }

    public synchronized AtomicLong getWaitTime() {
        return waitTime;
    }

    public synchronized void setWaitTime(AtomicLong waitTime) {
        this.waitTime = waitTime;
    }

    public void gotoNextRoom() {
        Room temp = actRoom;
       
        temp.playerOut(this);
        if(temp.getPlayers().size()==0)
        {
            temp.setScares(new AtomicInteger(0));
        }
        actRoom = temp.getNextRoom();
        actRoom.playerIn(this);
      
    }
    
   

    public void run() {

        //System.out.println("name: " + this.name);
        while (!(this.getActRoom().equals(House.poison))) {
            try {
                if(scary.get()<=0)
                {
                   
                    shocking();
                }
                if (group.isEveryBodyInTheRoom(actRoom)) {
                    if (notOtherGroupInTheNextRoom()) {
                        if (isBoring) {
                            gotoNextRoom();
                            NotBoring();
                        } else {
                            moveBoring();
                        }
                    }
                } else if (actRoom.isAnyBodyInTheNextRoomWithMyGroup(group)) {
                    if (isBoring) {
                       
                        gotoNextRoom();
                        NotBoring();
                    } else {
                        moveBoring();
                    }
                } else {
                    moveBoring();
                    //System.out.println("Unatkozom: " + name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(this.getName()+" szoba: "+this.actRoom.getName());
        }
        actRoom.playerOut(this);

      //  System.out.println("WIN: " + name);
    }

    private void shocking() throws InterruptedException {
        Thread.sleep(WAIT_TIME);
        scary=new AtomicInteger(SCARY_INT);
    }

    private void NotBoring() {
        isBoring = false;
    }

    private void moveBoring() throws InterruptedException {
        Thread.sleep(WAIT_TIME);
        isBoring = true;
        
    }

    private boolean notOtherGroupInTheNextRoom() {
        boolean retValue = false;

        if (actRoom.isEmptyNextRoom()) {
            retValue = true;

        } else if (actRoom.isSameNextRoomGroup(group)) {

            retValue = true;
        }

        return retValue;
    }

    @Override
    public String toString() {
        return "Player: " + name;
    }

    public void scare() {
        scary=new AtomicInteger(scary.decrementAndGet());
        
    }


}
