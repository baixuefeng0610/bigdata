package com.msb.zookeeper.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author: 马士兵教育  该watcher是和session有关的一个watcher
 * @create: 2019-09-20 20:12
 */
public class DefaultWatch  implements Watcher {

    CountDownLatch cc ;

    public void setCc(CountDownLatch cc) {
        this.cc = cc;
    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println(event.toString());

        switch (event.getState()) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                //当连接创建完成，count-1
                cc.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
        }


    }
}
