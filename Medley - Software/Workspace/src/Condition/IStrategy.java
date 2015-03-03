/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Condition;

/**
 *
 * @author thoma_000
 */
public interface IStrategy <R, P>
{
    public R    execute(P param);
}
