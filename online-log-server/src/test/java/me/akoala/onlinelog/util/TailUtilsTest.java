package me.akoala.onlinelog.util;

import org.junit.Test;

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
}
