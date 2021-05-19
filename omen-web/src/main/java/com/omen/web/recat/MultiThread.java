package com.omen.web.recat;

public class MultiThread {
 
	private static class Thread1 implements Runnable{		
		@Override
		public void run() {
			//由于 Thread1和下面Thread2内部run方法要用同一对象作为监视器，如果用this则Thread1和Threa2的this不是同一对象
			//所以用MultiThread.class这个字节码对象，当前虚拟机里引用这个变量时指向的都是同一个对象
			synchronized(MultiThread.class){
				System.out.println("thread1 创建...");
				System.out.println("thread1 就绪中");
				
				try{
					//释放锁有两种方式：(1)程序自然离开监视器的范围，即离开synchronized关键字管辖的代码范围
					//(2)在synchronized关键字管辖的代码内部调用监视器对象的wait()方法。这里使用wait方法
					MultiThread.class.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
				System.out.println("thread1 正在运行 ...");
				System.out.println("thread1 结束!");
			}
		}
		
	}
	
	private static class Thread2 implements Runnable{
		@Override
		public void run() {	
			//notify方法并不释放锁，即使thread2调用了下面的sleep方法休息10ms，但thread1仍然不会执行
			//因为thread2没有释放锁，所以Thread1得不到锁而无法执行
			synchronized(MultiThread.class){
				System.out.println("thread2 创建...");
				System.out.println("thread2 此刻唤醒其他wait线程notify other thread can release wait status ...");
//				MultiThread.class.notify();
			
				
				try{
					System.out.println("thread2 睡了...");
					Thread.sleep(1000000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
				System.out.println("thread2 正在运行...");
				System.out.println("thread2 结束!");
			}
		}		
	}
	
	public void demo() {
			for(int i = 0;i<5;i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("第"+i+"次输出");
			}
	}
	
	public static void main(String[] args) {
		new Thread(new Thread1()).start();
		try{
			Thread.sleep(10000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
 
		new Thread(new Thread2()).start();
 
		new MultiThread().demo();
	}
 
}