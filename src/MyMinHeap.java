import java.util.EmptyStackException;
public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

    public int size() {
        return heap.size();
    }

    public T getMin() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return heap.get(0);
    }
    private void heapify(int index) {
        int smallest = index;
        int leftChildIndex = leftChildOf(index);
        int rightChildIndex = rightChildOf(index);

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }
    public T extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        T max = getMax();
        swap(0, size() - 1);
        heap.remove(size() - 1);
        heapify(0);
        return max;
    }
    public T getMax() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return heap.get(0);
    }

    public void insert(T element) {
        heap.add(element);
        traverseUp(heap.size() - 1);
    }

    private void traverseUp(int index) {
        int parentIndex = parentOf(index);
        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parentOf(index);
        }
    }

    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}