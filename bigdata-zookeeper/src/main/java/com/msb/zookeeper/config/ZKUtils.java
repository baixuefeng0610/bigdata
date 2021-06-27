package com.msb.zookeeper.config;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author: 马士兵教育
 * @create: 2019-09-20 20:08
 */
public class ZKUtils {

    private  static ZooKeeper zk;

//    private static String address = "192.168.150.11:2181,192.168.150.12:2181,192.168.150.13:2181," +
//            "192.168.150.14:2181/testLock";
    private static String address = "192.168.217.131:2181,192.168.217.132:2181,192.168.217.133:2181/testConf";

    private static DefaultWatch watch = new DefaultWatch();

    private static CountDownLatch init  =  new CountDownLatch(1);
    public static ZooKeeper  getZK(){

        try {
            //zk创建是异步的过程，在此处创建后，会立马返回zk，但是zk的异步线程会创建连接以及session等，因此不等待直接返回，可能别人
            //拿到的zk还没有连接上，或者session没有创建完成。
            zk = new ZooKeeper(address,1000,watch);

            watch.setCc(init);
            //当init的值为0 的时候，这个等待才能通过
            init.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return zk;
    }


}
