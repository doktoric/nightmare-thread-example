package com.acme.junior.horrorhouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.acme.junior.player.Group;
import com.acme.junior.player.Player;
import com.acme.junior.player.Room;

public class TheMaster implements Runnable {

    private House house = new House();
    private ArrayList<Group> groupsList = new ArrayList<Group>();
    private List<Group> groups = Collections.synchronizedList(groupsList);
    private BlockingQueue<Group> waitingGroups = new ArrayBlockingQueue<Group>(4);
    private Group poisonGroup = new Group("poison");

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public TheMaster() {
        init();
    }

    public void init() {

        Group group1 = new Group("heroes");
        Group group2 = new Group("luzers");
        Group group3 = new Group("simple");

        Player p1 = new Player("Batman", group1);
        Player p2 = new Player("Spiderman", group1);
        Player p3 = new Player("Superman", group1);

        Player p4 = new Player("luzer1", group2);
        Player p5 = new Player("luzer2", group2);
        Player p6 = new Player("luzer3", group2);

        Player p7 = new Player("jozsi", group3);
        Player p8 = new Player("pisti", group3);
        Player p9 = new Player("zoli", group3);

        group1.addPlayer(p1);
        group1.addPlayer(p2);
        group1.addPlayer(p3);

        group2.addPlayer(p4);
        group2.addPlayer(p5);
        group2.addPlayer(p6);

        group3.addPlayer(p7);
        group3.addPlayer(p8);
        group3.addPlayer(p9);

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(poisonGroup);

        try {
            waitingGroups.put(group1);
            waitingGroups.put(group2);
            waitingGroups.put(group3);
            waitingGroups.put(poisonGroup);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run() {

        Group g = null;
        try {
            g = waitingGroups.take();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        //System.out.println("Start");
        while (!g.equals(poisonGroup)) {

            //System.out.println(g.getName());
            setFirstRoom(g);
            g.startGroup();

            try {
                g = waitingGroups.take();

            } catch (InterruptedException e) {
                System.out.println("Interrupt");
            }

        }

        while (groups.size() != 1 && !groups.get(0).equals(poisonGroup)) {

            for (int i = 0; i < groups.size(); i++) {

                if (EverybodyInEnd(groups.get(i))) {
                    removeGroupAsWinner(i);
                    i = -1;
                }
            }
        }
        killAllRoom();
        
      

    }

    private void removeGroupAsWinner(int i) {
        groups.remove(groups.get(i));
    }

    private void killAllRoom() {
       for (Room room : house.getRooms()) {
           room.setIsScary(new AtomicBoolean(false));
           room.shutdownHorrorPlayers();
       }
        
    }

    private void InterruptAllPeople(Group group) {
        for (Player player : group.getPlayers()) {
            player.getActRoom().getPlayers().remove(player);
        }
    }

    private boolean notOtherGroupInTheNextRoom(Group groupItem) {

        boolean retValue = true;

        if (!groupItem.getPlayers().get(0).getActRoom().equals(House.poison)) {
            int index = house.getRooms().indexOf(groupItem.getPlayers().get(0).getActRoom());
            for (Group group : groups) {
                if (group.isEveryBodyInTheRoom(house.getRooms().get(index + 1))) {
                    retValue = false;
                }
            }

        }

        return retValue;
    }

    private boolean EverybodyInEnd(Group group) {
        boolean retValue = true;
        for (Player player : group.getPlayers()) {
            if (!player.getActRoom().equals(House.poison)) {
                retValue = false;
            }
        }
        if (group.equals(poisonGroup)) {
            retValue = false;
        }
        // System.out.println(group.getName()+" group: "+retValue);
        return retValue;
    }

    private void setFirstRoom(Group g) {
        // TODO Auto-generated method stub
        for (Player item : g.getPlayers()) {
            item.setActRoom(house.getFirstRoom());
        }
    }

}
