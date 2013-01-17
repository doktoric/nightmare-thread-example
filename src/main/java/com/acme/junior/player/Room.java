package com.acme.junior.player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.acme.junior.horrorhouse.HorrorGroup;

public class Room {

    private String name;
    private volatile HorrorGroup group;
    private volatile Room previousRoom;
    private volatile Room nextRoom;
    private Set<Player> playerList = new HashSet<Player>();
    private Set<Player> players = Collections.synchronizedSet(playerList);
    private AtomicInteger scares = new AtomicInteger(0);
    private Lock lock=null;
    private AtomicBoolean isScary=new AtomicBoolean(true);

    public AtomicBoolean getIsScary() {
        return isScary;
    }

    public void setIsScary(AtomicBoolean isScary) {
        this.isScary = isScary;
    }

    public AtomicInteger getScares() {
        return scares;
    }

    public void setScares(AtomicInteger scares) {
        this.scares = scares;
    }

    public synchronized boolean isAnyBodyInTheNextRoomWithMyGroup(Group group) {

        boolean retValue = false;
       
        for (Player player : group.getPlayers()) {
            if (nextRoom.getPlayers().contains(player)) {
                retValue = true;
            }
        }
        return retValue;

    }

    public boolean isSameNextRoomGroup(Group group) {

        boolean ret = true;
        synchronized (group) {
            if (!nextRoom.getPlayers().isEmpty()) {
                for (Player player : nextRoom.getPlayers()) {
                    if (!player.getGroup().equals(group)) {
                        ret = false;
                    }
                }
            }
        }
        return ret;
    }

    public  boolean isEmptyNextRoom() {
        return (nextRoom.getPlayers().size() == 0);
    }

    public  boolean isEmpty() {
        return (this.getPlayers().size() == 0);
    }

    public synchronized void playerIn(Player player) {
        if (previousRoom != null) {
            previousRoom.getPlayers().remove(player);
        }
        players.add(player);
      
    }
    
    
    public void shutdownHorrorPlayers()
    {
        try {
            group.shutdownHorrorPlayers();
        } catch (Exception e) {
            // TODO: handle exception
        }
      
    }
    
    public Player getActualPlayer(int index)
    {
        int i=0;
        for (Player player : players) {
            if(i== index)return player;
            i++;
        }
        return null;
    }
    
    

    public synchronized void playerOut(Player player) {
        players.remove(player);
        if (nextRoom != null) {
            nextRoom.getPlayers().add(player);
        }
        if (players.size() == 0) {
            scares.set(0);
        }
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(Room previousRoom) {
        this.previousRoom = previousRoom;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }

    public String getName() {
        return name;
    }

    public Room(String name, HorrorGroup group, Room prevRoom, Room nextRoom) {
        this.name = name;
        this.group = group;
        this.nextRoom = nextRoom;
        this.previousRoom = prevRoom;
        lock=new ReentrantLock();
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public HorrorGroup getGroup() {
        return group;
    }

    public void setGroup(HorrorGroup group) {
        this.group = group;
        init();
    }

    private void init() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public Room() {
        init();
    }

    public Room(String name) {

        this.name = name;
        init();
    }

}
