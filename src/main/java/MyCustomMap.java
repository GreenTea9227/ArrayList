public interface MyCustomMap<T,R> {
    R put(T t,R r);
    int size();
    R get(T t);
    boolean containsKey(T t);
    boolean containsValue(R r);
    void clear();
    boolean isEmpty();
    R remove(T t);
}
