package wh.code.java.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

public class LogInvoHandler implements InvocationHandler {

	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private Object target;
	private Object proxy;

	private static HashMap<Class<?>, LogInvoHandler> invoHandlers = new HashMap<Class<?>, LogInvoHandler>();

	private LogInvoHandler() {

	}

	public synchronized static <T> T getProxyInstance(Class<T> clazz) {
		LogInvoHandler invoHandler = invoHandlers.get(clazz);

		if (null == invoHandler) {
			invoHandler = new LogInvoHandler();
			try {
				T tar = clazz.newInstance();
				invoHandler.setTarget(tar);
				invoHandler.setProxy(Proxy.newProxyInstance(tar.getClass().getClassLoader(),
						tar.getClass().getInterfaces(), invoHandler));
			} catch (Exception e) {
				e.printStackTrace();
			}
			invoHandlers.put(clazz, invoHandler);
		}

		return (T) invoHandler.getProxy();
	}

	public Object getTraget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Object getProxy() {
		return proxy;
	}

	public void setProxy(Object proxy) {
		this.proxy = proxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Long startTime=System.nanoTime();
		Object result = method.invoke(target, args);
		Long endTime=System.nanoTime();
		logger.info("runtime: "+(endTime-startTime)+"ns;\n");
		logger.info("target: "+target.getClass().getSimpleName()+";\n invoke method: " + method.getName() + ";\n args: "
				+ (null == args ? "null" : Arrays.asList(args).toString()) + ";\n return: " + result);
		return null;
	}

}
