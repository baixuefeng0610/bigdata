package com.msb.zookeeper.lock;

import com.msb.zookeeper.config.ZKUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: 马士兵教育
 * @create: 2019-09-20 21:21
 */
public class TestLock {


    ZooKeeper zk ;

    @Before
    public void conn (){
        zk  = ZKUtils.getZK();
    }

    @After
    public void close (){
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lock(){
        //10个线程，线程之间相互抢锁，只能有一个线程获取锁运行。
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    WatchCallBack watchCallBack = new WatchCallBack();
                    watchCallBack.setZk(zk);
                    String threadName = Thread.currentThread().getName();
                    watchCallBack.setThreadName(threadName);
                    //每一个线程：
                    //抢锁
                    watchCallBack.tryLock();
                    //干活
                    System.out.println(threadName+" working...");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //释放锁
                    watchCallBack.unLock();


                }
            }.start();
        }
        while(true){

        }


    }




}
