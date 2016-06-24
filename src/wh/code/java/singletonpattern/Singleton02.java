package wh.code.java.singletonpattern;
//���� �̰߳�ȫ ��ǰ����ʵ��
public class Singleton02 {
	private static final Singleton02 uniqueInstance = new Singleton02();

	private Singleton02() {

	};

	public Singleton02 getInstance() {
		return uniqueInstance;
	}
}
