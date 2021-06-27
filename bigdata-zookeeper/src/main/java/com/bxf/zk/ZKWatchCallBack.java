package com.bxf.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ZKWatchCallBack implements Watcher, AsyncCallback, AsyncCallback.StatCallback, AsyncCallback.DataCallback {
    ZooKeeper zooKeeper;
    MyData myData;
    CountDownLatch countDownLatch = new CountDownLatch(1);


    public void aWait() {
        //程序启动，由于一开始appConf节点不存在，节点也没发生状态变更，一直处于阻塞状态。

        //不论目录是否存在，都会回调这个回调函数，同时注册了监控watch到该节点上，当事件发生改变触发，再次回调函数
        zooKeeper.exists("/AppConf", this, this, "tt");
        try {
            //由于exits方法是异步执行，必须要阻塞住，等数据取回才能继续向下执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //数据回调函数
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {
            String s = new String(data);
            myData.setDate(s);
            countDownLatch.countDown();
        }
    }

    //状态回调函数
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zooKeeper.getData("/AppConf", this, this, "aa");
        }
    }

    //watcher监控函数
    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {

            case None:
                break;
            case NodeCreated:
                zooKeeper.getData("/AppConf", this, this, "cc");
                break;
            case NodeDeleted:
                //容忍性
                myData.setDate("");
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                //当节点数据变化，需要重新获取数据
                zooKeeper.getData("/AppConf", this, this, "dd");
                break;
            case NodeChildrenChanged:
                break;
        }

    }


    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void setMyData(MyData myData) {
        this.myData = myData;
    }
}


