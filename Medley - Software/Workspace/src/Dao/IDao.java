/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Condition.ICondition;
import java.util.Collection;
/**
 *
 * @author thoma_000
 * 
 **/

public interface IDao <T> extends IDaoErrorConstant
{
    /**
     * return the T Object who validate the condition.
     * 
     * @param   condition
     * 
     * @return  If there is more than one object who valide the condition
     *          this method return null and the last error is set appropriately.
     *          Else return the T Object.
     * @see     ICondition
     */
    public T               single(ICondition<T> condition);
    
    /**
     * return the first T Object who validate the condition.
     * 
     * @param   condition
     * 
     * @return  Return null if there is no Object who validate the condition.
     *          Else return the T Object.
     * 
     * @see     ICondition
     */
    public T               first(ICondition<T> condition);
    
    /**
     * return all T Objects.
     * 
     * @return  return a Collection of T Object.
     * 
     * @see Collection
     */
    public Collection<T>   all();
    
    /**
     * return all T Objects who validate the condition.
     * 
     * @param   condition
     * 
     * @return    return all T Objects who validate the condition.
     * 
     * @see ICondition 
     * @see Collection
     */
    public Collection<T>   all(ICondition<T> condition);
    
    /**
     * Add the T Object to the current Collection.
     * You have to call the submit method if you want to save that change.
     * @param obj
     * @return Nothing.
     */
    public void            add(T obj);
    
    /**
     * changed the Object called oldObj by the Object called newObj.
     * You have to call the submit method if you want to save that change.
     * 
     * @param oldObj
     * @param newObj
     * 
     * @return Nothing.
     */
    public void            update(T oldObj, T newObj);
     
    /**
     * delete the Object from the current collection.
     * You have to call the submit method if you want to save that change.
     * 
     * @param obj
     * 
     * @return Nothing.
     */
    public void            delete(T obj);

}
