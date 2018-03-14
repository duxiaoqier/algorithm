package com.duxiaoqier.algorithm.current.join;

public class JoinDemo {
    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 1; i <= 10; i++) {
            Thread curThread = new JoinThread(previousThread);
            curThread.start();
            previousThread = curThread;
        }
    }

    static class JoinThread extends Thread {
        private Thread previousThread;

        public JoinThread(Thread previousThread) {
            this.previousThread = previousThread;
        }

        @Override
        public void run() {
            try {
                previousThread.join();
                System.out.println(previousThread.getName() + " terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}