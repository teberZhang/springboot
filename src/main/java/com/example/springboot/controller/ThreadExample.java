package com.example.springboot.controller;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// 线程池
public class ThreadExample {

    // 定义一个阻塞队列，这个队列用来存储需要被执行的任务
    private static BlockingQueue<Runnable> tasks = new ArrayBlockingQueue<>(10);

    static {
        new Thread(new TaskConsumer()).start();
        new Thread(new TaskConsumer()).start();
        new Thread(new TaskConsumer()).start();
    }

    // 专门用来消费任务的线程（线程于线程池里面的工作线程）
    static class TaskConsumer implements Runnable {


        @Override
        public void run() {
            while (true) {
                // take 方法特性，如果tasks队列里面有待执行的任务，当前方法按照FIFO顺序返回
                // 如果tasks为空,则线程会被阻塞在task()方法中
                try {
                    Runnable runnable  = tasks.take();
                    System.out.println(Thread.currentThread().getName() + ": 收到一个任务");
                    // 把当前任务交给TaskConsumer这个工作线程来执行
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 生产者
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String task = scanner.nextLine();

            tasks.put(()->{
                System.out.println(Thread.currentThread().getName() + ": 我是待处理的任务: " + task);
            });
        }
    }
}
