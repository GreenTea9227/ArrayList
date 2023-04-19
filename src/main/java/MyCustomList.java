public interface MyCustomList<T> {
    public boolean add(T obj);
    public T get(int num);
    public int indexOf(T target);
    public T remove(int targetNum);
    public int size();
    public boolean contains(T target);
    public void clear();
    public boolean isEmpty();
}
