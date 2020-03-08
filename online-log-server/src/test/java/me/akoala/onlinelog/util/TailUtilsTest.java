package me.akoala.onlinelog.util;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

public class TailUtilsTest {

    @Test
    public void tail() {

        while (true) {
            try {
                TailUtils.tail("F:\\nginx\\nginx-1.16.0\\logs\\access.log", 3);
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testSeek() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("/Users/xhx-mac/Library/Mobile Documents/com~apple~CloudDocs/code/stock/log/common/2020-03-02.common.0.log", "r");
        long length = raf.length();

        raf.seek(length-1);

        byte aByte = raf.readByte();

        System.out.println(aByte);
        System.out.println(aByte == '\n');


        raf.seek(length-2);
        byte bByte = raf.readByte();
        System.out.println(bByte);
        System.out.println((char)bByte);
        System.out.println(bByte == '\r');



    }
}
