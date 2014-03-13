/**
 * 
 */
package cn.wensiqun.asmsupport.block.method.init;

import org.apache.commons.lang3.ArrayUtils;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.ProgramBlock;
import cn.wensiqun.asmsupport.block.method.GenericMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.operators.method.SuperConstructorInvoker;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;
import cn.wensiqun.asmsupport.utils.ASConstant;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class EnumInitBody extends GenericMethodBody implements IEnumInitBody {
    
	
	
    @Override
    public final void generateBody() {
    	if(getMethod().getMethodMeta().getName().equals(ASConstant.INIT)){
            OperatorFactory.newOperator(SuperConstructorInvoker.class, 
            		new Class<?>[]{ProgramBlock.class, AClass.class, Parameterized[].class}, 
            		getExecuteBlock(), getMethodOwner(), new Parameterized[]{argments[0], argments[1]});
            generateBody((LocalVariable[]) ArrayUtils.subarray(argments, 2, argments.length));
    	}else{
    		generateBody(argments);
    	}
    }
    
    public abstract void generateBody(LocalVariable... argus);

}
