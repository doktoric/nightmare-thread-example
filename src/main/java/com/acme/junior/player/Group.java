package com.acme.junior.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.acme.junior.horrorhouse.House;

public class Group {
    public static final int GROUP_MEMBER_COUNT = 3;
    private ArrayList<Player> playerList = new ArrayList<Player>(GROUP_MEMBER_COUNT);
    private List<Player> players = Collections.synchronizedList(playerList);
   
    
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private CountDownLatch latch = new CountDownLatch(GROUP_MEMBER_COUNT);

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public Group(String name) {

        this.name = name;
    }

    public Group() {

        this.name = "NONAME";
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGroup() {
        ExecutorService service = Executors.newFixedThreadPool(this.players.size());
        for (Player item : this.players) {
            service.submit(new Thread(item,item.getName()));
        }
        service.shutdown();
    }

    public void setGroupRoom(Room room) {

        for (Player player : players) {
            if (player.getActRoom() != House.poison) {
                player.setActRoom(room);
            }
        }

    }

    public Room getGroupActualRoom() {

        return this.players.get(0).getActRoom();
    }


    private void listAllPlayer() {
        System.out.println("-----------------" + name + "------------------------");

    }

    public boolean isEveryBodyInTheRoom(Room room) {
        boolean retValue=false;
        for (Player player : players) {
            if(player.getActRoom().equals(room))
            {
                retValue=true;
            }
        }
        return retValue;
    }

   

}
