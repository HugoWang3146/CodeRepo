package wh.code.java.classloader;

import java.net.URI;
import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class MyClassLoader  
extends ClassLoader  
{  
@Override  
public Class<?> findClass(String str) throws ClassNotFoundException  
{  
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  
    //�������Դ����������Ķ���  
    DiagnosticCollector diagnostics = new DiagnosticCollector();  
    //�ڴ��е�Դ���뱣����һ����JavaFileObject�̳е�����   
    JavaFileObject file = new JavaSourceFromString("Temp", str.toString());  
    Iterable compilationUnits = Arrays.asList(file);  
    //����һ����������  
    JavaCompiler.CompilationTask task = compiler.getTask(null, null, null, null, null, compilationUnits);  
    //����Դ����  
    boolean result = task.call();  
    if (result)   
    {  
        return Class.forName("Temp");  
    }  
    return null;  
}  
}  

class JavaSourceFromString extends SimpleJavaFileObject   
{  
private String name;  
private String code;  
public JavaSourceFromString(String name, String code)  
{  
    super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);  
    this.code = code;  
}  

public CharSequence getCharContent(boolean ignoreEncodingErrors)  
{  
    return code;  
}  
}  