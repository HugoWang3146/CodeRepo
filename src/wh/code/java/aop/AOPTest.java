package wh.code.java.aop;

import java.util.logging.Logger;
/*
 * AOP 测试类
 */
public class AOPTest {
	public static Logger logger = Logger.getLogger(AOPTest.class.getSimpleName());

	public static void main(String[] args) {

		BussinessService bs = LogInvoHandler.getProxyInstance(BussinessServiceImpl.class);
		bs.login("zhangsan", "123456");
		bs.find();

		logger.info("--------------------------------------");

		BussinessService bss = LogInvoHandler.getProxyInstance(BussinessServiceImpl.class);
		bss.login("lisi", "654321");
		bss.find();

	}
}

interface BussinessService {
	public String login(String username, String password);

	public String find();
}

class BussinessServiceImpl implements BussinessService {
	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	@Override
	public String login(String username, String password) {
		return "login success";
	}

	@Override
	public String find() {
		return "find success";
	}

}