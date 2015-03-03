
package Dao;


import java.util.ArrayList;
import java.util.Collection;

import Condition.ICondition;
import Factory.IFactory;


public abstract class ADao<T> implements IDao<T>
{
    

    protected   String          lastError;
    protected   IFactory        factory;
    protected   Collection<T>   collection;
    

    public  ADao(IFactory factory)  {this.factory = factory;    build();}
    
    public T               single(ICondition<T> condition)
    {
        T   elemToRet = null;
        
        for(T elem : this.collection)
            if (condition.equals(elem) == true) 
            {
                if (elemToRet ==  null) elemToRet = elem;
                else                    return this.buildError(SINGLE_ERROR);
            }
        
       return elemToRet;
    }
    
    public T               first(ICondition<T> condition)
    {
        for(T elem : this.collection)
            if (condition.equals(elem) == true) return elem;
        return null;
    }
    
    public Collection<T>   all(ICondition condition)
    {
        Collection<T>   collect = new ArrayList<T>();
        
        for(T elem : this.collection)
            if (condition.equals(elem) == true) collect.add(elem);
        
        return collect;
    }
     
    public Collection<T>   all()                        {return this.collection;                        }

    public void            add(T obj)                   {this.collection.add(obj);                      }
    public void            delete(T obj)                {this.collection.remove(obj);                   }
    public void            update(T oldObj, T newObj)   {this.delete(oldObj); this.add(newObj);         }
    
    public String           getLastError()              {return this.lastError;                         }

    
    private void            build()                     {this.collection    = this.factory.buildAll();  }
    private T               buildError(String error)    {this.lastError     = error; return null;       }
}
