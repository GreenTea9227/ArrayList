public interface MyCustomList<T> {
    boolean add(T obj);
     T get(int num);
     int indexOf(T target);
     T remove(int targetNum);
     int size();
     boolean contains(T target);
     void clear();
     boolean isEmpty();

}
