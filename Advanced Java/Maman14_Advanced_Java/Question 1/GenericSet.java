import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manipulates given sets.
 * @param <T> Set Type.
 */
public class GenericSet<T> {
    private ArrayList<T> array;

    public GenericSet() {
        array = new ArrayList<>();
    }

    public GenericSet(T[] arrayType) {
        this.array = new ArrayList<>();
        for (T var : arrayType)
            array.add(var);
        removeDuplicate(this);
    }

    private void removeDuplicate(GenericSet<T> set) {
        for (int i = 0; i < set.array.size(); i++) {
            for (int j = i+1; j < set.array.size(); j++) {
                if (set.array.get(i)==(set.array.get(j)))
                    set.array.remove(j);
            }
        }
    }

    public void union(GenericSet<T> set) {
        array.addAll(set.array);
        removeDuplicate(this);
    }

    public void intersect(GenericSet<T> set) {
        for (int i = 0; i < array.size(); i++) {
            if (!set.isMember(array.get(i))) {//If the set memeber is not a part of the second set.
                array.remove(array.get(i));
                i--;
            }
        }
    }

    public boolean isMember(T other) {
        for (T var : array) {//Passing all set values to find the other value in it.
            if (var.equals(other))
                return true;
        }
        return false;
    }

    public boolean isSubset(GenericSet<T> set) {
        for (int i = 0; i < set.array.size(); i++) {
            if (!this.isMember(set.array.get(i)))
                return false;
        }
        return true;
    }

    public void insert(T other) {
        if (!isMember(other))
            array.add(other);
    }

    public void delete(T other) {
        if (isMember(other))
            array.remove(other);
    }

    public Iterator<T> iterator() {
        return array.iterator();
    }

    public String toString() {
        String represent = "";
        for (T var : array) {
            represent += var.toString() + " ";
        }
        return represent;
    }
}
