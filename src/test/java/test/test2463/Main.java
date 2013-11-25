package test.test2463;

import junit.framework.Assert;

import org.objectweb.asm.Opcodes;

import utils.Utils;
import cn.wensiqun.asmsupport.block.method.common.CommonMethodBody;
import cn.wensiqun.asmsupport.block.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public class Main {

	public static void main(String[] args) {
		ClassCreator creator = 
				new ClassCreator(Opcodes.V1_6, Opcodes.ACC_PUBLIC , "test.Test2463", AbstractClass.class, null);
        
		creator.createMethod("getMyObject", null, null, AClassFactory.getProductClass(MyObject.class),
				null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){
					@Override
					public void generateBody(LocalVariable... argus) {
		            	runReturn(invokeConstructor(AClassFactory.getProductClass(MyObject.class)));
					}
			
		});
		
		creator.createMethod("getMyObject", null, null, AClassFactory.getProductClass(MyObjectSuper.class),
				null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){
					@Override
					public void generateBody(LocalVariable... argus) {
		            	runReturn(invokeConstructor(AClassFactory.getProductClass(MyObject.class)));
					}
			
		});
		
		creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void generateBody(LocalVariable... argus) {
                invoke(invokeConstructor(getMethodOwner()), "getMyObject");
            	runReturn();
            }
        });
		Utils.generate(creator);
	}
	
	@org.junit.Test
	public void test() throws Exception{
		try{
			main(null);
		}catch(Throwable e){
			Assert.fail();
		}
	}

}