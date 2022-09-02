package com.hdhxby.eshop;

import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockTest {

    private static ReentrantLock lock = new ReentrantLock();

    private static int j = 0;

    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i=0; i< 50; i++){
                        System.out.println(j++);
                    }
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread b =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i=0; i< 50; i++){
                        System.out.println(j++);
                    }
                } finally {
                    lock.unlock();
                }
            }
        });

        a.start(); // a线程
        b.start(); // b线程
    }
}
