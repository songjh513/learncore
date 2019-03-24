package com.songjh.learncore.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created  by songjh on 2019-03-24 12:14.
 */
public class Queue extends SyncPrimitive {

    /**
     * Constructor of producer-consumer queue
     * @param address
     * @param name
     * @throws KeeperException
     * @throws InterruptedException
     * @throws IOException
     */
    Queue(String address, String name)
            throws KeeperException, InterruptedException, IOException {
        super(address);
        this.root = name;
        // Create ZK node name
        if (zk != null) {
            Stat s = zk.exists(root, false);
            if (s == null) {
                zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.fromFlag(0));
            }
        }
    }

    /**
     * Add element to the queue.
     * @param i
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    boolean produce(int i) throws KeeperException, InterruptedException{
        ByteBuffer b = ByteBuffer.allocate(4);
        byte[] value;
        // Add child with value i
        b.putInt(i);
        value = b.array();
        zk.create(root + "/element", value, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);

        return true;
    }

    /**
     * Remove first element from the queue.
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    int consume() throws KeeperException, InterruptedException{
        int retvalue = -1;
        Stat stat = null;

        // Get the first element available
        while (true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                if (list.isEmpty()) {
                    System.out.println("Going to wait");
                    mutex.wait();
                } else {
                    Integer min = new Integer(list.get(0).substring(7));
                    for(String s : list){
                        Integer tempValue = new Integer(s.substring(7));
                        if(tempValue < min) min = tempValue;
                    }
                    System.out.println("Temporary value: " + root + "/element" + min);
                    byte[] b = zk.getData(root + "/element" + min, false, stat);
                    zk.delete(root + "/element" + min, 0);
                    ByteBuffer buffer = ByteBuffer.wrap(b);
                    retvalue = buffer.getInt();

                    return retvalue;
                }
            }
        }
    }
}
