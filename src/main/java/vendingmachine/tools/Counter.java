package vendingmachine.tools;


import vendingmachine.GraphicalUserInterface.ChooseItem;
import vendingmachine.Log.FileManager;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;


//usage:
//every time set a buying statement, use the constructor once.
//start counting time:
//Counter counter = new Coumter();
//counter.counting();
//
//stop counting:
//counter.killProgress();

public class Counter implements Runnable{


    int tick = 0;
    boolean counting;
    JFrame frame;
    String name, password;

    public Counter(JFrame frame, String name, String password) {
        this.frame = frame;
        this.name = name;
        this.password = password;
        this.counting = false;
    }

    @Override
    public void run() {
        counting = true;
        while(tick < 15 && counting){
            try {
                Thread.sleep(1000);
                System.out.println("transaction remaining time: " + (15 - tick));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tick++;
        }
        FileManager.cancelLog(this.name, "time out", "src/main/java/vendingmachine/Log/CancelLog.txt");
        cancelTran();
        this.frame.dispose();
    }

    public void cancelTran(){
        this.counting = false;
    }

    public void restart(){
        this.tick = 0;
    }
}

