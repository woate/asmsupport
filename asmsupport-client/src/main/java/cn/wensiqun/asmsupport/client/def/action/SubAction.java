package cn.wensiqun.asmsupport.client.def.action;

import cn.wensiqun.asmsupport.client.block.KernelProgramBlockCursor;
import cn.wensiqun.asmsupport.client.def.Param;
import cn.wensiqun.asmsupport.client.def.ParamPostern;
import cn.wensiqun.asmsupport.client.def.param.DummyParam;
import cn.wensiqun.asmsupport.core.operator.Operator;

public class SubAction extends AbstractBinaryAction {

    public SubAction(KernelProgramBlockCursor cursor) {
        super(cursor, Operator.SUB);
    }

    @Override
    public Param doAction(Param... operands) {
        return new DummyParam(cursor, cursor.getPointer().sub(ParamPostern.getTarget(operands[0]), 
                ParamPostern.getTarget(operands[1])));
    }

}
