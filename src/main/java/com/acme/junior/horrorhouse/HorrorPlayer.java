package com.acme.junior.horrorhouse;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

import com.acme.junior.player.Room;

public class HorrorPlayer implements Runnable {

    public static final int SCARY_INT = 3;
    public static final long SMOKE_TIME = 5000;
    private String name;
    private AtomicInteger scarys = new AtomicInteger(SCARY_INT);
    private volatile Room room = null;
    private Lock lock = null;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public HorrorPlayer(String name) {
        this.name = name;
    }

    public HorrorPlayer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized AtomicInteger getScarys() {
        return scarys;
    }

    public synchronized void setScarys(AtomicInteger scarys) {
        this.scarys = scarys;
    }

    public void run() {
        while (room.getIsScary().get()) {
            if (room.getPlayers().size() != 0) {
                if (room.getScares().get() < 2) {
                    if (lock.tryLock()) {
                        try {
                            if (room.getScares().get() == 1) {
                                try {
                                    if (room.getPlayers().size() > 1) {
                                        room.getActualPlayer(1).scare();
                                    } else {
                                        room.getActualPlayer(0).scare();
                                    }
                                } catch (Exception e) {
                                }
                            } else if (room.getScares().get() == 0) {
                                try {
                                    room.getActualPlayer(0).scare();
                                } catch (Exception e) {
                                }
                            }
                            room.setScares(new AtomicInteger(room.getScares().incrementAndGet()));
                            scarys = new AtomicInteger(scarys.decrementAndGet());
                            if (scarys.get() <= 0) {
                                goToSmoking();
                            }
                        } catch (Exception e) {
                        } finally {
                            lock.unlock();
                        }

                    }
                }
            }
        }
        
    }

    private void goToSmoking() throws InterruptedException {
        scarys = new AtomicInteger(SCARY_INT);
      
        this.wait(SMOKE_TIME);
       
    }

}
