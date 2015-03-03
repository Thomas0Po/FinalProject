
package Factory;

import java.util.Collection;

public interface IFactory <T> extends IFactoryErrorConstants
{
    public T               buildOne();
    public Collection<T>   buildAll();
}
