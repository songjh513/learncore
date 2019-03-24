package com.songjh.learncore.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.proto.WatcherEvent;

import java.io.IOException;

/**
 * Created  by songjh on 2019-03-24 10:02.
 */
public class SyncPrimitive implements Watcher {

    static ZooKeeper zk = null;
    static final Object mutex = new Object();

    String root;

    SyncPrimitive(String address)throws  IOException {
        if(zk == null){
            System.out.println("Starting ZK:");
            zk = new ZooKeeper(address, 3000, this);
            System.out.println("Finished starting ZK: " + zk);
        }
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        synchronized (mutex) {
            mutex.notify();
        }
    }
}
