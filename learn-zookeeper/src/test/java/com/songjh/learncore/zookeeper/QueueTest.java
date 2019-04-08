package com.songjh.learncore.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created  by songjh on 2019-03-24 12:21.
 */
public class QueueTest {

    public static void main(String[] args) {
        try {
            Queue queue = new Queue("47.96.122.221:2181", "/q1");
            queue.produce(1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}