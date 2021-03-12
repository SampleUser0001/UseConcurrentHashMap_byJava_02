package sample.map.task;

import java.util.Map;
import java.util.Iterator;

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
        Iterator<Integer> keySet = map.keySet().iterator();
        while(keySet.hasNext()){
            keySet.next();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e){}
        }
    }
}
