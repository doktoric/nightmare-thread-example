package com.acme.junior.horrorhouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.acme.junior.player.Room;

public class House {

    private ArrayList<Room> roomList = new ArrayList<Room>(5);
    private List<Room> rooms = Collections.synchronizedList(roomList);

    public static Room poison = new Room("posion");

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public House() {
        init();
    }

    public synchronized Room nextRoom(Room room) {
        Room retRoom = null;
        int index = 0;
        try {
            index = rooms.indexOf(room) + 1;
            retRoom = rooms.get(index);
        } catch (Exception e) {
            retRoom = House.poison;
        }

        return retRoom;
    }

    public Room getFirstRoom() {
        return rooms.get(0);
    }

    public void init() {

        HorrorPlayer ricsi = new HorrorPlayer("ricsi");
        HorrorPlayer marci = new HorrorPlayer("marci");
        HorrorPlayer gergo = new HorrorPlayer("gergo");

        HorrorPlayer zoli = new HorrorPlayer("zoli");
        HorrorPlayer peti = new HorrorPlayer("peti");
        HorrorPlayer laci = new HorrorPlayer("laci");

        HorrorPlayer ambrus = new HorrorPlayer("ambrus");
        HorrorPlayer joska = new HorrorPlayer("joska");
        HorrorPlayer pityu = new HorrorPlayer("pityu");

        HorrorPlayer ronaldo = new HorrorPlayer("ronaldo");
        HorrorPlayer iniesta = new HorrorPlayer("iniesta");
        HorrorPlayer kanyizarez = new HorrorPlayer("kanyizarez");

        HorrorPlayer zorro = new HorrorPlayer("zorro");
        HorrorPlayer batman = new HorrorPlayer("batman");
        HorrorPlayer wonderwomen = new HorrorPlayer("wonderwomen");

        HorrorPlayer lo = new HorrorPlayer("lo");
        HorrorPlayer kutya = new HorrorPlayer("kutya");
        HorrorPlayer tigris = new HorrorPlayer("tigris");

        Room eloszoba=null;
        Room konyha=null;
        Room nappali=null;
        Room gyerekszoba=null;
        Room wc=null;
        Room kijaro=null;
        
        eloszoba = new Room("eloszoba", new HorrorGroup(new ArrayList<HorrorPlayer>(Arrays.asList(ricsi, marci, gergo))),null,konyha);
        konyha = new Room("konyha", new HorrorGroup(new ArrayList<HorrorPlayer>(Arrays.asList(zoli, peti, laci))),eloszoba,nappali);
        nappali = new Room("nappali", new HorrorGroup(new ArrayList<HorrorPlayer>(Arrays.asList(ambrus, joska, pityu))),konyha,gyerekszoba);
        gyerekszoba = new Room("gyerekszoba", new HorrorGroup(new ArrayList<HorrorPlayer>(Arrays.asList(ronaldo, iniesta, kanyizarez))),nappali,wc);
        wc = new Room("wc", new HorrorGroup(new ArrayList<HorrorPlayer>(Arrays.asList(zorro, batman, wonderwomen))),gyerekszoba,kijaro);
        kijaro = new Room("kijaro", new HorrorGroup(new ArrayList<HorrorPlayer>(Arrays.asList(lo, kutya, tigris))),wc,poison);
        poison= new Room("posion",null,kijaro,null);
        
        eloszoba.setNextRoom(konyha);
        eloszoba.setPreviousRoom(null);
        konyha.setNextRoom(nappali);
        konyha.setPreviousRoom(eloszoba);
        nappali.setNextRoom(gyerekszoba);
        nappali.setPreviousRoom(konyha);
        gyerekszoba.setNextRoom(wc);
        gyerekszoba.setPreviousRoom(nappali);
        wc.setNextRoom(kijaro);
        wc.setPreviousRoom(gyerekszoba);
        kijaro.setNextRoom(poison);
        kijaro.setPreviousRoom(wc);
        poison.setNextRoom(null);
        poison.setPreviousRoom(kijaro);

        ricsi.setRoom(eloszoba);marci.setRoom(eloszoba);gergo.setRoom(eloszoba);
        zoli.setRoom(konyha);peti.setRoom(konyha);laci.setRoom(konyha);
        ambrus.setRoom(nappali);joska.setRoom(nappali);pityu.setRoom(nappali);
        ronaldo.setRoom(gyerekszoba);iniesta.setRoom(gyerekszoba);kanyizarez.setRoom(gyerekszoba);
        zorro.setRoom(wc);batman.setRoom(wc);wonderwomen.setRoom(wc);
        lo.setRoom(kijaro);kutya.setRoom(kijaro);tigris.setRoom(kijaro);
        
        
        
        eloszoba.getGroup().updateLock(eloszoba.getLock());
        konyha.getGroup().updateLock(konyha.getLock());
        nappali.getGroup().updateLock(nappali.getLock());
        gyerekszoba.getGroup().updateLock(gyerekszoba.getLock());
        wc.getGroup().updateLock(wc.getLock());
        kijaro.getGroup().updateLock(kijaro.getLock());
        
        rooms.add(eloszoba);
        rooms.add(konyha);
        rooms.add(nappali);
        rooms.add(gyerekszoba);
        rooms.add(wc);
        rooms.add(kijaro);
        rooms.add(poison);
        
        
        eloszoba.getGroup().init();
        konyha.getGroup().init();
        nappali.getGroup().init();
        gyerekszoba.getGroup().init();
        wc.getGroup().init();
        kijaro.getGroup().init();
       
        
        

    }

}
