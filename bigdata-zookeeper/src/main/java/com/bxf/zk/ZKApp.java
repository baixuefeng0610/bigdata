package com.bxf.zk;

import com.msb.zookeeper.config.MyConf;
import com.msb.zookeeper.config.WatchCallBack;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZKApp {
    ZooKeeper zk;

    @Before
    public void conn(){
        zk = ZKUtils.getZK();
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
    public void testConf(){
        MyData myData = new MyData();
        ZKWatchCallBack zkWatchCallBack = new ZKWatchCallBack();
        zkWatchCallBack.setMyData(myData);
        zkWatchCallBack.setZooKeeper(zk);

        zkWatchCallBack.aWait();



        //分为两种情况，第一种情况：节点存在，第二种情况：节点不存在
        while(true){

            if(myData.getDate().equals("")){
                System.out.println("conf diu le ......");
                zkWatchCallBack.aWait();
            }else{
                System.out.println(myData.getDate());

            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {


    }
}
