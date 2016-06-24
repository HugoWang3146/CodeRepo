package wh.code.java.singletonpattern;
// 单例 枚举 线程安全
public enum Singleton04 {
	INSTANCE;
	
	public void show(){
		System.out.println("synchronized [sɪŋkrənaɪzd]");
	}
}

//Singleton04.INSTANCE.show()
