package com.msb.zookeeper.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author: 马士兵教育
 * @create: 2019-09-20 20:21
 * 同时实现了监控watcher和回调，回调函数分为数据回调和状态回调
 */
public class WatchCallBack  implements Watcher ,AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    ZooKeeper zk ;
    MyConf conf ;
    CountDownLatch cc = new CountDownLatch(1);





    public void aWait(){
        //程序启动，由于一开始appConf节点不存在，节点也没发生状态变更，一直处于阻塞状态。

        //不论目录是否存在，都会回调这个回调函数，同时注册了监控watch到该节点上，当事件发生改变触发，再次回调函数
        zk.exists("/AppConf",this,this ,"ABC");
        try {
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    //数据回调函数
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {

        if(data != null ){
            String s = new String(data);
            conf.setConf(s);
            cc.countDown();
        }


    }

    @Override
    //状态回调函数
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        //获取状态不为空，当不为空的时候，就可以获取数据
        if(stat != null){
            zk.getData("/AppConf",this,this,"sdfs");
        }

    }

    @Override
    //watcher监控函数
    public void process(WatchedEvent event) {

        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData("/AppConf",this,this,"sdfs");

                break;
            case NodeDeleted:
                //容忍性
                conf.setConf("");
                cc = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zk.getData("/AppConf",this,this,"sdfs");

                break;
            case NodeChildrenChanged:
                break;
        }

    }

    public void setConf(MyConf conf) {
        this.conf = conf;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void setCc(CountDownLatch cc) {
        this.cc = cc;
    }
}
