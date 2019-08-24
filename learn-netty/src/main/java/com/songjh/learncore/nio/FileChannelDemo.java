package com.songjh.learncore.nio;



import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created  by songjh on 2019-06-08 00:01.
 */
public class FileChannelDemo {

    public static void main(String[] args) {

//        FileChannelBufferTest();

        FileChannelBufferTransFromTest();
    }



    private static void FileChannelBufferTest() {
        try {
            RandomAccessFile aFile = new RandomAccessFile("C://java//data//nio-data.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {

                buf.flip();  //make buffer ready for read

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get()); // read 1 byte at a time
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static  void FileChannelBufferTransFromTest() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("C://java//data//fromFile.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("C://java//data//toFile.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();

            toChannel.transferFrom(fromChannel, position, count);

        } catch (Exception e) {

        }
    }
}
