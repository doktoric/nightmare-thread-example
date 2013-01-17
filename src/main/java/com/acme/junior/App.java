package com.acme.junior;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.junior.horrorhouse.TheMaster;

/**
 * Hello world!
 *
 */

//Beletettem, hogy az ijesztgetők elfáradjanak, viszony csak bináris formában J kimennek cigizni, ha elfáradtak :D így ez már könnyen átírható lesz akár arra is, 
//hogy fáradjanak és akkor kevésbé legyenek ijesztőek J
//
//Feladat:
//- Horrorház (Nightmare in Budapest)
//- A horroház X szobából áll
//- A szobák szekvenciálisan követik egymást
//- Minden szobában van Y ijesztgető ember
//- Látogatók érkeznek véletlen időközönként (legyen valami injectorclass, ami szimulálja a megérkező embereket)
//- A látogatók érkezési sorrendben Z fős csoportokba szerveződnek, majd ha összejött egy csoport, akkor várnak a teremmester utasítására, aki elindítja őket
//- A csoportok tagjai csak előre haladhatnak
//- A csoportok tagjai bevárják egymást, így max 2 szobában helyezkedhetnek el
//- A csoportok tagjai csak akkor mehetnek tovább, ha 
//- a következő szobában nincs másik csapat (nem foglalt egy másik csapat által)
//- ha minden csapattárs vagy az aktuális vagy a következő szobában van (nem hagyják el egymást)
//- A csoportok tagjai a fenti csoportokra vonatkozó előírások mellett külön entitások, tehát külön kezelendők a csoportoktól
//- Egy ember T időt tölt egy szobában, utána unatkozni kezd “tovább akar menni”, viszont vár, amíg a csoportra vonatkozó előírások teljesülnek
//- Az emberek meghatározott sorrendben hagyják el a szobákat: aki hamarabb kezd unatkozni, az lép be hamarabb majd a köv szobába
//- Egy szobába belépő első két személyt megijesztik az ijesztgetők
//- Az ijesztgetők el tudnak fáradni: N ijesztés után kimennek cigizni J T2 időre
//- Egy személy egy meghatározott M-szer ijeszthető meg, ami után besokkol
//- Ha egy csoport egyik tagja besokkol a csoport az adott szobában lévő összes ijeszgetővel együtt kiviszi az adott személyt (az ijesztgetők ilyenkor annyi időt töltenek kint, mintha cigiznének)
//
//Úgy kéne megoldani, hogy ExecutorService-t, Scheduler-t, Future-t, Latch/Barrier-t, interruptot, volatile-t, Atomic*-ot, tehát minél több concurrency-s eszközt használjunk…
//

public class App {
  
    public static Logger logger =  LoggerFactory.getLogger("<<AspectLogger>>");
  

    public static void main(String[] args) {
  
        Thread masterThread=new Thread(new  TheMaster(),"Mester");
        masterThread.start();
        
        
    }
}
