package cn.wensiqun.asmsupport.sample.core.operators;


import cn.wensiqun.asmsupport.core.block.method.common.KernelStaticMethodBody;
import cn.wensiqun.asmsupport.core.builder.impl.ClassBuilderImpl;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.org.objectweb.asm.Opcodes;
import cn.wensiqun.asmsupport.sample.core.AbstractExample;
import cn.wensiqun.asmsupport.standard.def.clazz.IClass;

/**
 * return操作对应的就是java代码中的return关键字。分为两种
 * 一种是无返回类型的。一种是有返回类型的
 *
 */
public class ReturnOperatorGenerate extends AbstractExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassBuilderImpl creator = new ClassBuilderImpl(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.operators.ReturnOperatorGenerateExample", null, null);
		
		/* 
		 * 有返回类型的方法
		 */
		creator.createStaticMethod(Opcodes.ACC_PUBLIC, "commonMethod", null, null, classLoader.getType(String.class), null, new KernelStaticMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				return_(val("I'm from commonMethod"));
			}
		});
		
		/* 
		 * 无返回类型的方法
		 */
		creator.createStaticMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, 
				"main", new IClass[]{classLoader.getType(String[].class)}, new String[]{"args"}, null, null,
				new KernelStaticMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				call(systemOut, "println", call(getMethodDeclaringClass(), "commonMethod"));
				return_();
			}
        });
		generate(creator);
	}

}
