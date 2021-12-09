package com.hc.utils;

/**
 * @author hancongcong
 */
public class MultiThreadTest {

    private int count;

    public void test() throws InterruptedException {
        Task task = new Task();
        task.start();
        task.await();
    }

    public class Task extends Thread{
        private Object lock = new Object();
        private boolean finished = false;

        @Override
        public void run(){
            synchronized (lock) {
                add();
                finished = true;
                lock.notifyAll();
            }
        }


        public void await() throws InterruptedException {
            synchronized (lock){
                if(finished){
                    return;
                }
                lock.wait();
            }
        }
    }

    private void add() {
        count++;
    }
}
