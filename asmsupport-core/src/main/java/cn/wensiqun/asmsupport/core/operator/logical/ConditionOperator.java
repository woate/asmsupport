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
package cn.wensiqun.asmsupport.core.operator.logical;


import cn.wensiqun.asmsupport.core.block.KernelProgramBlock;
import cn.wensiqun.asmsupport.core.definition.KernelParam;
import cn.wensiqun.asmsupport.core.operator.Operator;
import cn.wensiqun.asmsupport.org.objectweb.asm.Label;


/**
 * 
 * @author wensiqun at 163.com(Joe Wen)
 *
 */
public abstract class ConditionOperator extends BinaryLogical {

    protected Label trueLbl;
    protected Label falseLbl;
    
    protected ConditionOperator(KernelProgramBlock block, KernelParam leftFactor, KernelParam rightFactor, Operator operator) {
        super(block, leftFactor, rightFactor, operator);
        falseLbl = new Label();
        trueLbl = new Label();
    }
    
    @Override
    protected void doExecute() {
        executing();
        block.getMethod().getStack().printState();
    }
}
