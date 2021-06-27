package com.bxf.zk;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKUtils {
    //1、定义zk，定义watcher
    private static  ZooKeeper zooKeeper;
    private static String address = "192.168.217.131:2181,192.168.217.132:2181,192.168.217.133:2181/testConf";
    private static DefaultWatch defaultWatch = new DefaultWatch();
    private static CountDownLatch init = new CountDownLatch(1);

    public static ZooKeeper getZK(){
        try {
            zooKeeper = new ZooKeeper(address,1000,defaultWatch);
            defaultWatch.setCountDownLatch(init);
            init.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  zooKeeper;
    }


}
