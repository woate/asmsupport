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
/**
 * 
 */
package cn.wensiqun.asmsupport.core.operator.logical;


import cn.wensiqun.asmsupport.core.block.KernelProgramBlock;
import cn.wensiqun.asmsupport.core.definition.KernelParam;
import cn.wensiqun.asmsupport.core.operator.Jumpable;
import cn.wensiqun.asmsupport.core.operator.Operator;
import cn.wensiqun.asmsupport.core.utils.memory.Stack;
import cn.wensiqun.asmsupport.org.objectweb.asm.Label;
import cn.wensiqun.asmsupport.org.objectweb.asm.MethodVisitor;
import cn.wensiqun.asmsupport.org.objectweb.asm.Opcodes;
import cn.wensiqun.asmsupport.org.objectweb.asm.Type;


/**
 * 
 * @author wensiqun at 163.com(Joe Wen)
 *
 */
public class KernelShortCircuitAnd extends ConditionOperator implements Jumpable {
    
    protected KernelShortCircuitAnd(KernelProgramBlock block, KernelParam leftFactor, KernelParam rightFactor) {
        super(block, leftFactor, rightFactor, Operator.CONDITION_AND);
    }
    
    
    @Override
    protected void executing() {
        Label andEndLbl = new Label();
        Label falseLbl = new Label();
        MethodVisitor mv = insnHelper.getMv();

        jumpNegative(this, andEndLbl, falseLbl);
        
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitJumpInsn(Opcodes.GOTO, andEndLbl);
        mv.visitLabel(falseLbl);
        mv.visitInsn(Opcodes.ICONST_0);
        mv.visitLabel(andEndLbl);
        
        Stack stack = block.getMethod().getStack();
        stack.pop();
        stack.pop();
        stack.push(Type.BOOLEAN_TYPE);
    }

    @Override
    public void jumpPositive(KernelParam from, Label posLbl, Label negLbl) {
        MethodVisitor mv = insnHelper.getMv();
        Label label4Or = new Label();
        if(leftFactor instanceof KernelShortCircuitOr) {
            ((Jumpable) leftFactor).jumpNegative(this, posLbl, label4Or);
        } else if(leftFactor instanceof Jumpable) {
            ((Jumpable) leftFactor).jumpNegative(this, posLbl, negLbl);
        } else {
            leftFactor.loadToStack(block);
            insnHelper.unbox(leftFactor.getResultType().getType());
            mv.visitJumpInsn(Opcodes.IFEQ, negLbl);
        }
        
        if(rightFactor instanceof Jumpable) {
            ((Jumpable) rightFactor).jumpPositive(this, posLbl, negLbl);
        } else {
            rightFactor.loadToStack(block);
            insnHelper.unbox(rightFactor.getResultType().getType());
            mv.visitJumpInsn(Opcodes.IFNE, posLbl);
        }
        insnHelper.mark(label4Or);
    }


    @Override
    public void jumpNegative(KernelParam from, Label posLbl, Label negLbl) {
        MethodVisitor mv = insnHelper.getMv();
        Label label4Or = new Label();
        if(leftFactor instanceof KernelShortCircuitOr) {
            ((Jumpable) leftFactor).jumpNegative(this, posLbl, label4Or);
        } else if(leftFactor instanceof Jumpable) {
            ((Jumpable) leftFactor).jumpNegative(this, posLbl, negLbl);
        } else {
            leftFactor.loadToStack(block);
            insnHelper.unbox(leftFactor.getResultType().getType());
            mv.visitJumpInsn(Opcodes.IFEQ, negLbl);
        }

        if(rightFactor instanceof Jumpable) {
            ((Jumpable) rightFactor).jumpNegative(this, posLbl, negLbl);
        } else {
            rightFactor.loadToStack(block);
            insnHelper.unbox(rightFactor.getResultType().getType());
            mv.visitJumpInsn(Opcodes.IFEQ, negLbl);
        }
        insnHelper.mark(label4Or);
    }

}
