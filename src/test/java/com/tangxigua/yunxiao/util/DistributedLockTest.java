/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/12
 *******************************************************/
package com.tangxigua.yunxiao.util;

import org.junit.Test;

import static org.junit.Assert.*;
public class DistributedLockTest {

    static int n = 500;

    public static void secskill() {
        System.out.println(--n);
    }

    @Test
    public void lockTest() {


        Runnable runnable = new Runnable() {
            public void run() {
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock("127.0.0.1:2181", "test1");
                    lock.lock();
                    secskill();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }

}