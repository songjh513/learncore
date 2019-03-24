package com.songjh.learncore.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

/**
 * Created  by songjh on 2019-03-24 23:00.
 */
public class ZookeeperSession implements Watcher {

    private  static  CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args){

        try {
            ZooKeeper zooKeeper = new ZooKeeper("47.96.122.221:8199", 500, new ZookeeperSession());
            System.out.println(zooKeeper.getState());
            create(zooKeeper);
            connectedSemaphore.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean create(ZooKeeper zk) throws KeeperException, InterruptedException{
        ByteBuffer b = ByteBuffer.allocate(4);
        byte[] value;
        // Add child with value i
        b.putInt(1);
        value = b.array();
        zk.create( "/element", value, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);

        return true;
    }


    @Override
    public void process(WatchedEvent watchedEvent) {

        System.out.println("receive watched event"+watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
