import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyArrayList<T> implements Iterable<T> {

    private final int SIZE = 50;
    private T[] list;
    private int current;

    public MyArrayList() {
        list = (T[]) new Object[SIZE];
        current = 0;
    }

    public boolean add(T obj) {

        if (current >= SIZE) {
            T[] array = (T[]) new Object[list.length + SIZE];
            System.arraycopy(array, 0, list, 0, list.length);
            list = array;
        }

        list[current++] = obj;

        return true;
    }

    public int size() {
        return current;
    }

    public T get(int num) {
        if (num > current)
            throw new IndexOutOfBoundsException("범위 초과");
        return list[num];
    }

    public int indexOf(T target) {
        for (int i = 0; i < current; i++) {
            if (list[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return current == 0;
    }

    public boolean contains(T target) {
        return indexOf(target) != -1;
    }

    public void clear() {
        current = 0;
        list = (T[]) new Object[SIZE];
    }

    public T remove(int targetNum) {
        T target = get(targetNum);

        for (int i = targetNum; i < current; i++) {
            list[i] = list[i + 1];
        }
        current--;
        return target;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(list).limit(current).iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
