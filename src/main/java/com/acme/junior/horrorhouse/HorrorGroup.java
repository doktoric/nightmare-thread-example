package com.acme.junior.horrorhouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

public class HorrorGroup {

    public final static int GROUP_MEMBER_COUNT = 3;

    private static List<HorrorPlayer> list_of_member = new ArrayList<HorrorPlayer>(GROUP_MEMBER_COUNT);
    private List<HorrorPlayer> members = Collections.synchronizedList(list_of_member);
    private ExecutorService ex = Executors.newFixedThreadPool(1);

    public List<HorrorPlayer> getMembers() {
        return members;
    }

    public void setMembers(List<HorrorPlayer> members) {
        this.members = members;
    }
    
    
    public void shutdownHorrorPlayers()
    {
        try {
            ex.shutdownNow();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
    
    
    

    public HorrorGroup(List<HorrorPlayer> members) {

        this.members = members;
        //init();
    }

    public HorrorGroup() {
        //init();
    }

    public void init() {
        if (members != null) {
            if (members.size() > 0) {
                ex = Executors.newFixedThreadPool(members.size());
                for (HorrorPlayer player : members) {
                    ex.submit(new Thread(player,player.getName()));
                }
            }
        }
    }
    
    
    public void updateLock(Lock lock)
    {
        for (HorrorPlayer  item : members) {
            item.setLock(lock);
        }
    }

}
