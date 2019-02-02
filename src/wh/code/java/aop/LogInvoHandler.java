package wh.code.java.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;
/*
 * 
 * AOP 面向切面编程
 * 使用jdk的动态绑定机制，实现日志输出及程序运行时间统计
 * 
 * 实现InvocationHandler接口，使用map来存储不同的InvocationHandler对象，避免生成过多
 * 
 * jdk的动态代理要求实现接口，以保证代理及被代理者调用的方法的一致性。动态代理原理上并不一定需要实现接口，本质上是利
 * 用超类以保证调用的一致性
 */


public class LogInvoHandler implements InvocationHandler {

	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private Object target;	//被代理对象
	private Object proxy;	//代理
	//存储
	private static HashMap<Class<?>, LogInvoHandler> invoHandlers = new HashMap<Class<?>, LogInvoHandler>();

	private LogInvoHandler() {

	}
	
	/**
     * 通过Class来生成动态代理对象Proxy
     * @param clazz
     * @return
     */
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
