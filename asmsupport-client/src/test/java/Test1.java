package cn.wensiqun.asmsupport.client.demo;

import cn.wensiqun.asmsupport.client.DummyClass;
import cn.wensiqun.asmsupport.client.block.StaticBlockBody;
import cn.wensiqun.asmsupport.client.def.var.LocVar;
import cn.wensiqun.asmsupport.core.definition.variable.GlobalVariable;
import cn.wensiqun.asmsupport.standard.def.clazz.IClass;
import cn.wensiqun.asmsupport.standard.def.var.meta.Field;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by woate on 2016/5/9.
 * 测试创建一个实现类
 */
//@Ignore
public class Test1 {
    /**
     * 创建一个默认访问域的类Test1test1
     */
    @Test
    public void test1(){
        DummyClass dc = new DummyClass("Test1test1").setClassOutPutPath(".");
        Class cls = dc.build();
    }
    /**
     * 创建一个公开访问域的类Test1test2
     */
    @Test
    public void test2(){
        DummyClass dc = new DummyClass("Test1test2").public_().setClassOutPutPath(".");
        Class cls = dc.build();
    }
    /**
     * 创建一个默认访问域的抽象类Test1test3
     */
    @Test
    public void test3(){
        DummyClass dc = new DummyClass("Test1test3").abstract_().setClassOutPutPath(".");
        Class cls = dc.build();
    }

    /**
     * 创建一个默认访问域的抽象类Test1test4
     */
    @Test
    public void test4(){
        DummyClass dc = new DummyClass("Test1test4").setClassOutPutPath(".");
        dc.newStaticBlock(new StaticBlockBody() {
            @Override
            public void body() {
                Field out = getType(System.class).getField("out");
                call(out.getDeclaringClass(), "println", val("Hello ASMSupport"));

                return_();
            }
        });
        Class cls = dc.build();
    }
}
