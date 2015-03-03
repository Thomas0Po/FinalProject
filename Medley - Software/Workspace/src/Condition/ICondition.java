/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Condition;

/**
 *
 * @author thoma_000
 */
public interface ICondition <P> extends IStrategy <Boolean, P>
{
    public Boolean execute(P param);
}
