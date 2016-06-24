package wh.code.java.singletonpattern;
//���� ���̰߳�ȫ
public class Singleton01 {
	private static Singleton01 uniqueInstance = null;

	private Singleton01() {

	};

	public Singleton01 getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new Singleton01();
		return uniqueInstance;
	}
}
