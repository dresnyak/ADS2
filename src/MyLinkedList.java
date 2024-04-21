public class MyLinkedList<T> implements MyList<T> {
    private static class Node<T> {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public void add(T element) {
        addLast(element);
    }

    @Override
    public void set(int index, T element) {
        Node<T> node = getNode(index);
        node.data = element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<T> prev = getNode(index - 1);
            Node<T> newNode = new Node<>(element);
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    @Override
    public void addLast(T element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        return (T) getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return (T) head.data;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return (T) tail.data;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node current = getNode(index);
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    }

    @Override
    public void sort() {
        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> prev = null;
            while (current != null && current.next != null) {
                Comparable<T> currentData = (Comparable<T>) current.data;
                Comparable<T> nextData = (Comparable<T>) current.next.data;
                if (currentData.compareTo((T) nextData) > 0) {
                    if (prev == null) {
                        head = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    Node<T> nextNode = current.next.next;
                    current.next.next = current;
                    current.next = nextNode;
                    prev = current.next;
                    swapped = true;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
        } while (swapped);
    }

    @Override
    public int indexOf(T element) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(element)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        Node current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.data.equals(element)) {
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = (T) current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }
}