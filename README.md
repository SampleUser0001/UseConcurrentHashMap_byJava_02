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

## 追加調査

ConcurrentHashMapでputしながら、iteratorで取得するとどうなる？

### 実行結果

HashMapは落ちるので省略。

ConcurrentHashMap

```
Start.
100ミリ秒待機。
---- init ----
key:-1345654360,value:1896855897
key:1664809038,value:1873632814
key:209918395,value:-1641501656
key:1772221883,value:1642436463
key:-506740336,value:-1776172970
key:530066020,value:-365661110
key:-18943710,value:1700813522
key:-1473634810,value:-803490822
key:-312597936,value:1761575634
key:256659362,value:1287644833
--------
---- read map ----
ThreadID:15, key:-1345654360, value:1896855897
ThreadID:15, key:1664809038, value:1873632814
ThreadID:15, key:-1771164391, value:-1332654545
ThreadID:15, key:209918395, value:-1641501656
ThreadID:15, key:1772221883, value:1642436463
ThreadID:15, key:-506740336, value:-1776172970
ThreadID:15, key:-1473634810, value:-803490822
ThreadID:15, key:-18943710, value:1700813522
ThreadID:15, key:530066020, value:-365661110
ThreadID:15, key:-614316994, value:861802877
ThreadID:15, key:-1779817849, value:-687054975
ThreadID:15, key:-312597936, value:1761575634
cannot shutdown
ThreadID:15, key:256659362, value:1287644833
ThreadID:15, key:352211264, value:-959751030
ThreadID:15, key:1137465011, value:216167451
ThreadID:15, key:167750048, value:888084153
--------
```
