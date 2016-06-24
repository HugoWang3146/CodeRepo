package wh.code.java.thread;

import java.util.ArrayList;

import wh.code.java.utils.FileUtils;

public class FileProcess {

	public static final int THREAD_POOL_SIZE = 5;

	private Thread[] pool = null;
	
	private ArrayList<String> filePaths=null;

	public FileProcess() {
		this(THREAD_POOL_SIZE);
	}

	public FileProcess(int poolSize) {
		pool = new Thread[poolSize];
	}
	
	public void setFilePaths(String filePath){
		filePaths=FileUtils.getAllFilePath(filePath);
	}

	public void startProcess() {
		int poolSize=pool.length;
		int count=filePaths.size();
		int avg=count/pool.length;
		for (int i = 0; i < pool.length; i++) {
			pool[i] = new Thread(new FileWorker(filePaths,i*avg, avg), "Thread" + (i + 1));
			pool[i].start();
		}
//		pool[pool.length-1]=new Thread(new FileWorker(filePaths,count-(poolSize-2)*avg, avg), "Thread" + poolSize);
//		pool[pool.length-1].start();
	}
	
	public static void main(String[] args){
		String filePath="C:/Users/hanwa_000/Desktop/Job";
		FileProcess fp=new FileProcess(5);
		fp.setFilePaths(filePath);
		fp.startProcess();
	}
}

class FileWorker implements Runnable {

	private int start = 0;
	private int count = 0;
	private ArrayList<String> filePaths=null;

	public FileWorker() {

	}

	public FileWorker(ArrayList<String> filePaths,int start, int count) {
		this.start = start;
		this.count = count;
		this.filePaths=filePaths;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = start; i < start + count; i++) {
			System.out.println(Thread.currentThread().getName()+"##"+filePaths.get(i));
			//todo
		}

	}
}
