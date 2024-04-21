import java.util.EmptyStackException;

public class MyQueue<T> {
    private MyArrayList<T> list;

    public MyQueue() {
        list = new MyArrayList<>();
    }

    public void enqueue(T element) {
        list.add(element);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T firstItem = list.getFirst();
        list.remove(0);
        return firstItem;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }
}