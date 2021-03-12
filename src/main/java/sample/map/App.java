package sample.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.Random;

import sample.map.task.GetTask;
import sample.map.task.PutTask;

/**
 * Hello world!
 *
 */
public class App {
    public static final int KEY_COUNT = 1000;

    public static final int SLEEP_TIME = 10;

    public static final int THREAD_COUNT = 2;

    public static void main( String[] args ) {
        System.out.println("Start.");
        System.out.println(KEY_COUNT * SLEEP_TIME + "ミリ秒待機。");

        // インスタンス生成
        Map<Integer,Integer> map;
        if(args[0].equals("HashMap")) {
            map = new HashMap<Integer,Integer>();
        } else if(args[0].equals("ConcurrentHashMap")) {
            map = new ConcurrentHashMap<Integer,Integer>();
        } else {
            throw new IllegalArgumentException("args is 'HashMap' or 'ConcurrentHashMap'");
        }

        // Mapの初期化
        // なんでもいいので適当な値を詰める。
        Random random = new Random();
        for(int i=0;i<KEY_COUNT;i++) {
            map.put(random.nextInt(), random.nextInt());
        }

        // 並行処理開始
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_COUNT);
        for(int i=0;i<THREAD_COUNT/2;i++){
            exec.execute(new GetTask(map, SLEEP_TIME));
            exec.execute(new PutTask(map, KEY_COUNT, SLEEP_TIME));
        }

        // 並列処理終了。
        exec.shutdown();
        try {
            if( !exec.awaitTermination(KEY_COUNT * SLEEP_TIME, TimeUnit.MILLISECONDS) ){
              exec.shutdownNow();
              if(!exec.awaitTermination(KEY_COUNT * SLEEP_TIME, TimeUnit.MILLISECONDS)){
                System.err.println("cannot shutdown");
              }
            }
          } catch (InterruptedException e){
            exec.shutdownNow();
            Thread.currentThread().interrupt();
          }
        }
}
