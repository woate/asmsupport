/**    
 *  Asmsupport is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cn.wensiqun.asmsupport.client.block;

import cn.wensiqun.asmsupport.client.def.Param;
import cn.wensiqun.asmsupport.client.def.ParamPostern;
import cn.wensiqun.asmsupport.client.def.param.ArrayParam;
import cn.wensiqun.asmsupport.client.def.param.BoolParam;
import cn.wensiqun.asmsupport.client.def.param.DummyParam;
import cn.wensiqun.asmsupport.client.def.param.NumParam;
import cn.wensiqun.asmsupport.client.def.param.UncertainParam;
import cn.wensiqun.asmsupport.client.def.var.FieldVar;
import cn.wensiqun.asmsupport.client.def.var.LocVar;
import cn.wensiqun.asmsupport.client.def.var.Super;
import cn.wensiqun.asmsupport.client.def.var.This;
import cn.wensiqun.asmsupport.client.def.var.Var;
import cn.wensiqun.asmsupport.client.operations.action.AddAction;
import cn.wensiqun.asmsupport.client.operations.action.AndAction;
import cn.wensiqun.asmsupport.client.operations.action.ArrayLengthAction;
import cn.wensiqun.asmsupport.client.operations.action.BandAction;
import cn.wensiqun.asmsupport.client.operations.action.BorAction;
import cn.wensiqun.asmsupport.client.operations.action.BxorAction;
import cn.wensiqun.asmsupport.client.operations.action.DivAction;
import cn.wensiqun.asmsupport.client.operations.action.EqualAction;
import cn.wensiqun.asmsupport.client.operations.action.GreaterEqualAction;
import cn.wensiqun.asmsupport.client.operations.action.GreaterThanAction;
import cn.wensiqun.asmsupport.client.operations.action.InstanceofAction;
import cn.wensiqun.asmsupport.client.operations.action.LessEqualAction;
import cn.wensiqun.asmsupport.client.operations.action.LessThanAction;
import cn.wensiqun.asmsupport.client.operations.action.LogicAndAction;
import cn.wensiqun.asmsupport.client.operations.action.LogicOrAction;
import cn.wensiqun.asmsupport.client.operations.action.LogicXorAction;
import cn.wensiqun.asmsupport.client.operations.action.ModAction;
import cn.wensiqun.asmsupport.client.operations.action.MulAction;
import cn.wensiqun.asmsupport.client.operations.action.NegAction;
import cn.wensiqun.asmsupport.client.operations.action.NotAction;
import cn.wensiqun.asmsupport.client.operations.action.NotEqualAction;
import cn.wensiqun.asmsupport.client.operations.action.OrAction;
import cn.wensiqun.asmsupport.client.operations.action.PostDecAction;
import cn.wensiqun.asmsupport.client.operations.action.PostIncAction;
import cn.wensiqun.asmsupport.client.operations.action.PreDecAction;
import cn.wensiqun.asmsupport.client.operations.action.PreIncAction;
import cn.wensiqun.asmsupport.client.operations.action.ReverseAction;
import cn.wensiqun.asmsupport.client.operations.action.ShiftLeftAction;
import cn.wensiqun.asmsupport.client.operations.action.ShiftRightAction;
import cn.wensiqun.asmsupport.client.operations.action.SubAction;
import cn.wensiqun.asmsupport.client.operations.action.UnsignedShiftRightAction;
import cn.wensiqun.asmsupport.core.block.KernelProgramBlock;
import cn.wensiqun.asmsupport.core.definition.variable.IVariable;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.standard.action.ActionSet;
import cn.wensiqun.asmsupport.standard.def.clazz.AClass;

public class ProgramBlock<B extends KernelProgramBlock> implements ActionSet<
Param, Var, FieldVar,
IF, While, DoWhile, ForEach, Try, Sync> {

	B targetBlock;

	public LocalVariable[] getMethodArguments() {
		return targetBlock.getMethodArguments();
	}

	/**
	 * get current method owner.
	 * 
	 * @return
	 */
	public AClass getMethodOwner() {
		return targetBlock.getMethodOwner();
	}

	@Override
	public This this_() {
		return new This(targetBlock, targetBlock.this_());
	}

	@Override
	public FieldVar this_(String name) {
		return new FieldVar(targetBlock, targetBlock.this_(name));
	}

	@Override
	public Super super_() {
		return new Super(targetBlock, targetBlock.super_());
	}

	@Override
	public LocVar var(String name, Class<?> type, Param para) {
		return new LocVar(targetBlock, targetBlock.var(name, type, ParamPostern.getTarget(para)));
	}

	@Override
	public LocVar var(Class<?> type, Param para) {
		return new LocVar(targetBlock, targetBlock.var(type, ParamPostern.getTarget(para)));
	}

	@Override
	public LocVar var(String name, AClass type, Param para) {
		return new LocVar(targetBlock, targetBlock.var(name, type, ParamPostern.getTarget(para)));
	}

	@Override
	public LocVar var(AClass type, Param para) {
		return new LocVar(targetBlock, targetBlock.var(type, ParamPostern.getTarget(para)));
	}

	@Override
	public FieldVar field(String name) {
		return new FieldVar(targetBlock, targetBlock.field(name));
	}

	@Override
	public UncertainParam assign(Var variable, Param val) {
		return new UncertainParam(targetBlock, targetBlock.assign((IVariable) ParamPostern.getTarget(variable), ParamPostern.getTarget(val)));
	}

	@Override
	public UncertainParam call(Param objRef, String methodName, Param... arguments) {
		return new UncertainParam(targetBlock, targetBlock.call(ParamPostern.getTarget(objRef), methodName, ParamPostern.getTarget(arguments)));
	}

	@Override
	public UncertainParam call(String methodName, Param... args) {
		return new UncertainParam(targetBlock, targetBlock.call(methodName, ParamPostern.getTarget(args)));
	}

	@Override
	public UncertainParam call(AClass owner, String methodName, Param... arguments) {
		return new UncertainParam(targetBlock, targetBlock.call(owner, methodName, ParamPostern.getTarget(arguments)));
	}
    
	@Override
    public UncertainParam call(Class<?> owner, String methodName, Param... arguments) {
    	return new UncertainParam(targetBlock, targetBlock.call(owner, methodName, ParamPostern.getTarget(arguments)));
    }

	@Override
	public UncertainParam new_(Class<?> owner, Param... arguments) {
		return new UncertainParam(targetBlock, targetBlock.new_(owner, ParamPostern.getTarget(arguments)));
	}

	@Override
	public UncertainParam new_(AClass owner, Param... arguments) {
		return new UncertainParam(targetBlock, targetBlock.new_(owner, ParamPostern.getTarget(arguments)));
	}

	@Override
	public UncertainParam callOrig() {
		return new UncertainParam(targetBlock, targetBlock.callOrig());
	}

	@Override
	public ArrayParam makeArray(AClass aClass, Param... allocateDims) {
		return new ArrayParam(targetBlock, targetBlock.makeArray(aClass, ParamPostern.getTarget(allocateDims)));
	}

	@Override
	public ArrayParam makeArray(Class<?> arraytype, Param... dimensions) {
		return new ArrayParam(targetBlock, targetBlock.makeArray(arraytype, ParamPostern.getTarget(dimensions)));
	}

	@Override
	public ArrayParam newarray(AClass aClass, Object arrayObject) {
		return new ArrayParam(targetBlock, targetBlock.newarray(aClass, ParamPostern.getTarget(arrayObject)));
	}

	@Override
	public ArrayParam newarray(Class<?> type, Object arrayObject) {
		return new ArrayParam(targetBlock, targetBlock.newarray(type, ParamPostern.getTarget(arrayObject)));
	}

	@Override
	public UncertainParam arrayLoad(Param arrayReference, Param pardim, Param... parDims) {
		return new UncertainParam(targetBlock, targetBlock.arrayLoad(ParamPostern.getTarget(arrayReference), ParamPostern.getTarget(pardim), ParamPostern.getTarget(parDims)));
	}
	
	@Override
	public UncertainParam arrayStore(Param arrayReference, Param value, Param dim, Param... dims) {
		return new UncertainParam(targetBlock, targetBlock.arrayStore(ParamPostern.getTarget(arrayReference), 
		        ParamPostern.getTarget(value), ParamPostern.getTarget(dim), ParamPostern.getTarget(dims)));
	}

	@Override
	public NumParam arrayLength(Param arrayReference, Param... dims) {
	    Param[] operands = ParamPostern.unionParam(arrayReference, dims);
		return new NumParam(targetBlock, new ArrayLengthAction(targetBlock, operands.length), operands);
	}
	
	@Override
	public NumParam add(Param factor1, Param factor2) {
	    return new NumParam(targetBlock, new AddAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam sub(Param factor1, Param factor2) {
        return new NumParam(targetBlock, new SubAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam mul(Param factor1, Param factor2) {
        return new NumParam(targetBlock, new MulAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam div(Param factor1, Param factor2) {
        return new NumParam(targetBlock, new DivAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam mod(Param factor1, Param factor2) {
        return new NumParam(targetBlock, new ModAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam reverse(Param factor) {
		return new NumParam(targetBlock, new ReverseAction(targetBlock), factor);
	}

	@Override
	public NumParam band(Param factor1, Param factor2) {
		return new NumParam(targetBlock, new BandAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam bor(Param factor1, Param factor2) {
        return new NumParam(targetBlock, new BorAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam bxor(Param factor1, Param factor2) {
        return new NumParam(targetBlock, new BxorAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam shl(Param factor1, Param factor2) {
		return new NumParam(targetBlock, new ShiftLeftAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam shr(Param factor1, Param factor2) {
        return new NumParam(targetBlock, new ShiftRightAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam ushr(Param factor1, Param factor2) {
        return new NumParam(targetBlock, new UnsignedShiftRightAction(targetBlock), factor1, factor2);
	}

	@Override
	public NumParam predec(Param crement) {
        return new NumParam(targetBlock, new PreDecAction(targetBlock), crement);
	}

	@Override
	public NumParam postdec(Param crement) {
        return new NumParam(targetBlock, new PostDecAction(targetBlock), crement);
	}

	@Override
	public NumParam preinc(Param crement) {
        return new NumParam(targetBlock, new PreIncAction(targetBlock), crement);
	}

	@Override
	public NumParam postinc(Param crement) {
        return new NumParam(targetBlock, new PostIncAction(targetBlock), crement);
	}

	@Override
	public BoolParam gt(Param factor1, Param factor2) {
		return new BoolParam(targetBlock, new GreaterThanAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam ge(Param factor1, Param factor2) {
        return new BoolParam(targetBlock, new GreaterEqualAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam lt(Param factor1, Param factor2) {
        return new BoolParam(targetBlock, new LessThanAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam le(Param factor1, Param factor2) {
        return new BoolParam(targetBlock, new LessEqualAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam eq(Param factor1, Param factor2) {
        return new BoolParam(targetBlock, new EqualAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam ne(Param factor1, Param factor2) {
        return new BoolParam(targetBlock, new NotEqualAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam logicalAnd(Param factor1, Param factor2) {
		return new BoolParam(targetBlock, new LogicAndAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam logicalOr(Param factor1, Param factor2) {
        return new BoolParam(targetBlock, new LogicOrAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam logicalXor(Param factor1, Param factor2) {
        return new BoolParam(targetBlock, new LogicXorAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam and(Param factor1, Param factor2, Param... otherFactor) {
        return new BoolParam(targetBlock, new AndAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam or(Param factor1, Param factor2, Param... otherFactor) {
        return new BoolParam(targetBlock, new OrAction(targetBlock), factor1, factor2);
	}

	@Override
	public BoolParam no(Param factor) {
        return new BoolParam(targetBlock, new NotAction(targetBlock), factor);
	}

	@Override
	public UncertainParam checkcast(Param cc, AClass to) {
		return new UncertainParam(targetBlock, targetBlock.checkcast(ParamPostern.getTarget(cc), to));
	}

	@Override
	public UncertainParam checkcast(Param cc, Class<?> to) {
		return new UncertainParam(targetBlock, targetBlock.checkcast(ParamPostern.getTarget(cc), to));
	}

	@Override
	public NumParam neg(Param factor) {
		return new NumParam(targetBlock, new NegAction(targetBlock), factor);
	}

	@Override
	public DummyParam ternary(Param exp1, Param exp2,
	        Param exp3) {
		return new DummyParam(targetBlock, targetBlock.ternary(ParamPostern.getTarget(exp1), ParamPostern.getTarget(exp2), ParamPostern.getTarget(exp3)));
	}

	@Override
	public UncertainParam stradd(Param par1, Param... pars) {
		return new UncertainParam(targetBlock, targetBlock.stradd(ParamPostern.getTarget(par1), ParamPostern.getTarget(pars)));
	}

	@Override
	public BoolParam instanceof_(Param obj, AClass type) {
		return new BoolParam(targetBlock, new InstanceofAction(targetBlock, type), obj);
	}

	@Override
	public BoolParam instanceof_(Param obj, Class<?> type) {
        return new BoolParam(targetBlock, new InstanceofAction(targetBlock, getType(type)), obj);
	}

	@Override
	public void break_() {
		targetBlock.break_();
	}

	@Override
	public void continue_() {
		targetBlock.continue_();
	}

	@Override
	public void throw_(Param exception) {
		targetBlock.throw_(ParamPostern.getTarget(exception));
	}

	@Override
	public void return_() {
		targetBlock.return_();
	}

	@Override
	public void return_(Param param) {
		targetBlock.return_(ParamPostern.getTarget(param));
	}

	@Override
	public IF if_(IF ifBlock) {
		targetBlock.if_(ifBlock.targetBlock);
		return ifBlock;
	}

	@Override
	public While while_(While whileLoop) {
		targetBlock.while_(whileLoop.targetBlock);
		return whileLoop;
	}

	@Override
	public DoWhile dowhile(DoWhile doWhile) {
		targetBlock.dowhile(doWhile.targetBlock);
		return doWhile;
	}

	@Override
	public ForEach for_(ForEach forEach) {
		targetBlock.for_(forEach.targetBlock);
		return forEach;
	}

	@Override
	public Try try_(Try tryClient) {
		targetBlock.try_(tryClient.targetBlock);
		return tryClient;
	}

	@Override
	public Sync sync(Sync sync) {
		targetBlock.sync(sync.targetBlock);
		return sync;
	}

	@Override
	public DummyParam val(Integer val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(Short val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(Byte val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(Boolean val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(Long val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(Double val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(Character val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(Float val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(AClass val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(Class<?> val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam val(String val) {
		return new DummyParam(targetBlock, targetBlock.val(val));
	}

	@Override
	public DummyParam null_(AClass type) {
		return new DummyParam(targetBlock, targetBlock.null_(type));
	}

	@Override
	public DummyParam null_(Class<?> type) {
		return new DummyParam(targetBlock, targetBlock.null_(type));
	}

	@Override
	public AClass getType(Class<?> cls) {
		return targetBlock.getType(cls);
	}

	@Override
	public AClass getArrayType(Class<?> cls, int dim) {
		return targetBlock.getArrayType(cls, dim);
	}

	@Override
	public AClass getArrayType(AClass rootComponent, int dim) {
		return targetBlock.getArrayType(rootComponent, dim);
	}
	
    LocVar[] internalVar2ClientVar(LocalVariable... pars) {
        if(pars == null) {
            return null;
        }
        LocVar[] paras = new LocVar[pars.length];
        for(int i=0; i<pars.length; i++) {
            paras[i] = new LocVar(targetBlock, pars[i]);
        }
        return paras;
    } 
    
}