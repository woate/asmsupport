package cn.wensiqun.asmsupport.standard.utils;

import cn.wensiqun.asmsupport.standard.def.clazz.ClassHolder;
import cn.wensiqun.asmsupport.standard.def.clazz.IClass;

public abstract class AsmsupportClassLoader extends ClassLoader implements ClassHolder {
	
	private ClassLoader referenceClassLoader;
	
    public AsmsupportClassLoader() {
    }

    public AsmsupportClassLoader(ClassLoader parent) {
        super(parent);
        referenceClassLoader = parent;
    }

    /**
     * Define a class with a class name.
     * 
     * @param name
     * @param classBytes
     * @return
     * @throws Exception
     */
    public Class<?> defineClass(String name, byte[] classBytes, IClass itype) throws Exception {
    	Class<?> result = defineClass(name, classBytes);
    	afterDefineClass(result, itype);
    	return result;
    }

    protected abstract Class<?> afterDefineClass(Class<?> result, IClass itype) throws Exception;
    
    public abstract Class<?> defineClass(String name, byte[] classBytes) throws Exception;

	public ClassLoader getReferenceClassLoader() {
		return referenceClassLoader;
	}
	
}
