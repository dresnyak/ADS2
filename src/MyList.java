public interface MyList<T> {
    void add(T element);
    void set(int index, T element);
    void add(int index, T element);
    void addFirst(T element);
    void addLast(T element);
    T get(int index);
    T getFirst();
    T getLast();
    void remove(int index);
    void sort();
    int indexOf(T element);
    int lastIndexOf(T element);
    boolean exists(T element);
    T[] toArray();
    void clear();
    int size();
}