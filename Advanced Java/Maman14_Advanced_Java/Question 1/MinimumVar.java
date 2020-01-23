import java.util.Iterator;

/**
 * This class returns the minimum variable in the given set.
 */
public class MinimumVar {
    public static <E extends Comparable> E minSet (GenericSet<E> set){
        Iterator<E> iterator = set.iterator();
        if (!iterator.hasNext())
            return null;
        E min = iterator.next();
        while (iterator.hasNext()) {
            E compare = iterator.next();
            if(compare.compareTo(min)<0)
                min = compare;
        }
        return min;
    }
}
