package wh.code.java.singletonpattern;
//单例 双检锁
public class Singleton03 {
	
	private volatile static Singleton03  instance;
	private Singleton03(){
		
	}
	
	public Singleton03 getInstance(){
		if(instance==null){
			synchronized(Singleton03.class){
				if(instance==null)
					instance=new Singleton03();
			}
		}
		return instance;
	}
	public void show(){
		System.out.println("synchronized [sɪŋkrənaɪzd]");
	}
}
