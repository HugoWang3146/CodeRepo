package wh.code.java.singletonpattern;
//单列 线程安全 提前创建实例
public class Singleton02 {
	private static final Singleton02 uniqueInstance = new Singleton02();

	private Singleton02() {

	};

	public Singleton02 getInstance() {
		return uniqueInstance;
	}
}
