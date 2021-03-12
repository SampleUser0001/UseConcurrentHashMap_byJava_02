# UseConcurrentHashMap_byJava_02

MapでIteratorを使って読み込みしながら、Iteratorの取得元のMapにputする。

## 実行

``` sh
mvn clean compile exec:java -Dexec.mainClass="sample.map.App" -Dexec.args="HashMap"
```

``` sh
mvn clean compile exec:java -Dexec.mainClass="sample.map.App" -Dexec.args="ConcurrentHashMap"
```

### 実行結果

HashMapの場合

``` txt
Start.
10000ミリ秒待機。
[WARNING] 
java.util.ConcurrentModificationException
    at java.util.HashMap$HashIterator.nextNode (HashMap.java:1493)
    at java.util.HashMap$KeyIterator.next (HashMap.java:1516)
    at sample.map.task.GetTask.run (GetTask.java:18)
    at java.util.concurrent.ThreadPoolExecutor.runWorker (ThreadPoolExecutor.java:1128)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run (ThreadPoolExecutor.java:628)
    at java.lang.Thread.run (Thread.java:834)
```

ConcurrentHashMapの場合

``` txt
Start.
10000ミリ秒待機
```

