package wh.code.java.thread;

import java.util.ArrayList;
import java.util.LinkedList;

import wh.code.java.utils.FileUtils;

public class FileProcess2 {

	public static final int THREAD_POOL_SIZE = 5;

	private Thread[] pool = null;

	private LinkedList<String> filePaths = null;

	public FileProcess2() {
		this(THREAD_POOL_SIZE);
	}

	public FileProcess2(int poolSize) {
		pool = new Thread[poolSize];
	}

	public void setFilePaths(String filePath) {
		filePaths = new LinkedList<String>(FileUtils.getAllFilePath(filePath));
	}

	public void startProcess() {
		FileWorker2 fileWork2=new FileWorker2(filePaths);
		for (int i = 0; i < pool.length; i++) {
			pool[i] = new Thread(fileWork2, "Thread" + (i + 1));
			pool[i].start();
		}
	}

	public static void main(String[] args) {
		String filePath1 = "C:/Users/hanwa_000/Desktop/Tunnelier-Inst";
		String filePath2="E:/Ñ¸À×ÏÂÔØ";
		FileProcess2 fp = new FileProcess2(5);
		fp.setFilePaths(filePath2);
		fp.startProcess();
	}
}

class FileWorker2 implements Runnable {

	private LinkedList<String> filePaths = null;

	public FileWorker2() {

	}

	public FileWorker2(LinkedList<String> filePaths) {
		this.filePaths = filePaths;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			String file = null;
			synchronized (filePaths) {
				if (!filePaths.isEmpty()){
					file = filePaths.removeFirst();
					System.out.println(Thread.currentThread().getName() + "#" + file);
				}
				else
					break;
			}
			
		}

	}
}
