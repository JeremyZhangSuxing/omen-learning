package com.omen.learning.sample.cas;


import com.omen.learning.common.utils.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zhang.suxing
 * @date 2020/3/19 13:59
 **/
public class CasOriginDemo {

    public static void main(String[] args) {
        ExecutorService executorService = ThreadUtils.buildThreadPool();
        //测试aba
//        executorService.execute(CasOriginDemo::mainThread);
//        executorService.execute(CasOriginDemo::otherThread);
        //测试版版本号避免aba
        executorService.execute(CasOriginDemo::mainReferenceThread);
        executorService.execute(CasOriginDemo::otherReferenceThread);
    }

    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    private static AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<>(1, 1);

    private static void mainThread() {
        try {
            int expect = atomicInteger.get();
            System.out.println("操作线程---" + Thread.currentThread().getName() + "---当前值 = " + atomicInteger.get());
            int newCount = expect + 1;
            Thread.sleep(1000L);
            boolean result = atomicInteger.compareAndSet(expect, newCount);
            System.out.println("操作线程---" + Thread.currentThread().getName() + "---当前值 = " + atomicInteger.get() + "【cas操作】" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void otherThread() {
        try {
            Thread.sleep(20);
            atomicInteger.incrementAndGet();
            System.out.println("操作线程---" + Thread.currentThread().getName() + "---increment   当前值 = " + atomicInteger.get());
            atomicInteger.decrementAndGet();
            System.out.println("操作线程---" + Thread.currentThread().getName() + "---decrement    当前值 = " + atomicInteger.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void mainReferenceThread() {
        try {
            int expect = atomicReference.getReference();
            int newCount = expect + 1;
            int expectStamp = atomicReference.getStamp();
            int newStamp = expectStamp + 1;
            System.out.println("操作线程---mainReferenceThread" + "当前值 = " + expect + "【当前版本号】" + expectStamp);
            Thread.sleep(1000L);
            atomicReference.compareAndSet(expect, newCount, expectStamp, newStamp);
            boolean result = atomicInteger.compareAndSet(1, newCount);
            System.out.println("操作线程---mainReferenceThread" + "当前值 = " + atomicInteger.get() + "【cas操作】" + result + "---" + newCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void otherReferenceThread() {
        try {
            Thread.sleep(20);
            atomicReference.compareAndSet(atomicReference.getReference(), atomicReference.getReference() + 1, atomicReference.getStamp(), atomicReference.getStamp() + 1);
            System.out.println("操作线程---otherReferenceThread" + "当前值 = " + atomicReference.getReference() + "【当前版本号】" + atomicReference.getStamp());
            atomicReference.compareAndSet(atomicReference.getReference(), atomicReference.getReference() - 1, atomicReference.getStamp(), atomicReference.getStamp() + 1);
            System.out.println("操作线程---otherReferenceThread" + "当前值 = " + atomicReference.getReference() + "【当前版本号】" + atomicReference.getStamp());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

