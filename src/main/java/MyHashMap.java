import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<T, R> implements MyCustomMap<T, R> {

    private R[] keys;
    private final int SIZE = 10000;
    int count = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        keys = (R[]) new Object[SIZE + 1];
    }


    @Override
    public synchronized R put(T t, R r) {
        R oldr = null;
        count++;

        if (containsKey(t)) {
            oldr = get(t);
            count--;
        }

        keys[getHashCode(t)] = r;
        return oldr;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public R get(T t) {
        return containsKey(t) ? keys[getHashCode(t)] : null;
    }

    @Override
    public boolean containsKey(T t) {
        return keys[getHashCode(t)] != null;
    }

    @Override
    public boolean containsValue(R r) {
        return Arrays.stream(keys).filter(Objects::nonNull).anyMatch(i -> i.equals(r));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {

        keys = (R[]) new Object[SIZE];
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public synchronized R remove(T t) {
        if (containsKey(t)) {
            R value = keys[getHashCode(t)];
            keys[getHashCode(t)] = null;
            count--;
            return value;
        }
        return null;
    }

    int getHashCode(T t) {
        return t.hashCode() % SIZE;
    }
}
