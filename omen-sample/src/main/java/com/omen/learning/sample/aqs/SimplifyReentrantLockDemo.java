package com.omen.learning.sample.aqs;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author suxing.zhang
 * @date 2021-05
 */
public class SimplifyReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        //condition 会使当前节点释放锁  await ---->>>> 当前线程挂起，
        Condition condition = lock.newCondition();
        AtomicReference<Integer> i = new AtomicReference<>(0);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("进入等待!");
                // 释放锁+i
                while (i.get() < 2) {
                    //对此代码块中的逻辑上锁，当前线程
                    System.out.println("i 的当前值是： ---" + i);
                    System.out.println("thread1 ---- 即将被挂起来 的当前值是： ---" + i);
                    //此处会释放锁，等待唤醒重新争抢锁资源
                    condition.await();
                    //被唤醒执行业务逻辑
                    i.getAndSet(i.get() + 1);

                }
                System.out.println("thread1 ------ >>>>  接收到通知！继续执行！");
                System.out.println("thread1 ---- 已经被唤醒 的当前值是： ---" + i);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            lock.unlock();
        }, "conditionAwaitThread").start();

        new Thread(() -> {
            try {
                i.getAndSet(i.get() + 1);
                System.out.println("thread2 ------ >>>>   模拟3秒后发送通知过！");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println("发送通知！");
            condition.signal();
            lock.unlock();
        }, "conditionSignalThread").start();
    }
}
