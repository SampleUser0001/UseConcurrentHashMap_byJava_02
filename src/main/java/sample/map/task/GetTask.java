package sample.map.task;

import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;

public class GetTask implements Runnable {
    private Map<Integer, Integer> map;
    private int sleepTime;
    public GetTask(Map<Integer, Integer> map, int sleepTime){
        this.map = map;
        this.sleepTime = sleepTime;
    }
    @Override
    public void run(){
        // とりあえずkeyを読み込む
        System.out.println("---- read map ----");
        Iterator<Entry<Integer,Integer>> entrySet = map.entrySet().iterator();
        do {
            Entry<Integer,Integer> entry = entrySet.next();
            System.out.printf(
                "ThreadID:%d, key:%d, value:%d\n",
                Thread.currentThread().getId(),
                entry.getKey(),
                entry.getValue());
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e){}
        } while(entrySet.hasNext());
        System.out.println("--------");
    }
}
