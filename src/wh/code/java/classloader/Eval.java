package wh.code.java.classloader;

import java.lang.reflect.Method;

public class Eval   
{  
    public static Object eval(String str) throws Exception  
    {  
        StringBuffer sb = new StringBuffer();  
        sb.append("public class Temp");  
        sb.append("{");  
        sb.append("    public Object getObject()");  
        sb.append("    {");  
        sb.append("        " + str + "return new Object();");  
        sb.append("    }");  
        sb.append("}");  
        //�����Զ�������������ر������ڴ���class�ļ�  
        Class clazz = new MyClassLoader().findClass(sb.toString());  
        Method method = clazz.getMethod("getObject");  
        //ͨ��������÷���  
        return method.invoke(clazz.newInstance());  
    }  
  
    public static void main(String[] args) throws Exception  
    {  
        Object rval = eval("System.out.println(\"Hello World\");");  
        System.out.println(rval);  
    }  
}  
