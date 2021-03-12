package sample.map.task;

import java.util.Map;
import java.util.Random;

public class PutTask implements Runnable {
    private Map<Integer, Integer> map;
    private int keyCount;
    private int sleepTime;
    public PutTask(Map<Integer, Integer> map, int keyCount, int sleepTime){
        this.map = map;
        this.keyCount = keyCount;
        this.sleepTime = sleepTime;
    }
    @Override
    public void run(){
        Random random = new Random();
        // 再び適当な値をput
        for(int i=0;i<keyCount;i++) {
            map.put(random.nextInt(), random.nextInt());
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e){}
        }
    }
}
