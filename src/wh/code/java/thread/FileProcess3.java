package wh.code.java.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

import wh.code.java.utils.FileUtils;

public class FileProcess3 {

	public static final int THREAD_POOL_SIZE = 5;

	private Thread[] pool = null;

	private static Integer filePaths = 0;
	
	synchronized private static int getFilePath(){
		return filePaths--;
	}

	public FileProcess3() {
		this(THREAD_POOL_SIZE);
	}

	public FileProcess3(int poolSize) {
		pool = new Thread[poolSize];
	}

//	public void setFilePaths(String filePath) {
//		filePaths = new LinkedList<String>(FileUtils.getAllFilePath(filePath));
//	}

	public void startProcess() {
		FileWorker3 fw=new FileWorker3();
		for (int i = 0; i < pool.length; i++) {
			pool[i] = new Thread(fw, "Thread" + (i + 1));
			pool[i].start();
		}
	}

	public static void main(String[] args) {
		String filePath1 = "C:/Users/hanwa_000/Desktop/Tunnelier-Inst";
		String filePath2="E:/Ñ¸À×ÏÂÔØ";
		FileProcess3 fp = new FileProcess3(2);
//		fp.setFilePaths(filePath1);
		fp.startProcess();
	}
}

class FileWorker3 implements Runnable {
	private Integer count=100;
	Object obj=new Object();
	private final ReentrantLock lock=new ReentrantLock();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Integer file = 0;
			synchronized(obj){
				if(count>0){
					file=count;
					System.out.println(Thread.currentThread().getName() + "#" + file);
					count--;
				}else{
					break;
				}
			}
			
		}

	}
}
