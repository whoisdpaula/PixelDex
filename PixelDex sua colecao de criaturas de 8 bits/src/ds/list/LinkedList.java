package ds.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class LinkedList<T> implements Iterable<T> {
    private LinkedNode<T> head;
    private int size = 0;
    public void add(T data){
        addLast(data);
    }
    public void addLast(T data){
        if(head == null){
            head = new LinkedNode<>(data);
        }else{
            LinkedNode<T> curr = head;
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = new LinkedNode<>(data);
        }
        size++;
    }
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T data;
        if (index == 0) {
            data = head.data;
            head = head.next;
        } else {
            LinkedNode<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            data = prev.next.data;
            prev.next = prev.next.next;
        }
        size--;
        return data;
    }
    private LinkedNode<T> getLinkedNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        LinkedNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }
    public T get(int index) {
        return getLinkedNode(index).data;
    }
    public void insertAt(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            LinkedNode<T> newNode = new LinkedNode<>(data);
            newNode.next = head;
            head = newNode;
        } else {
            LinkedNode<T> prev = getLinkedNode(index - 1);
            LinkedNode<T> newNode = new LinkedNode<>(data);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }
    public void move(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) return;
        T dataToMove = removeAt(fromIndex);
        try {
            insertAt(toIndex, dataToMove);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("O índice de destino (" + toIndex + ") está fora dos limites.");
        }
    }
    public java.util.List<T> slice(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Índices inválidos. Certifique-se de que 0 <= from <= to < size.");
        }
        java.util.List<T> sublist = new ArrayList<>();
        LinkedNode<T> curr = getLinkedNode(fromIndex);

        for (int i = fromIndex; i <= toIndex; i++) {
            sublist.add(curr.data);
            if (i < toIndex) {
                curr = curr.next;
            }
        }
        return sublist;
    }

    public int size(){
        return size;
    }

    public void reverse() {
        LinkedNode<T> prev = null;
        LinkedNode<T> current = head;
        LinkedNode<T> next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public int unique() {
        if (head == null) return 0;

        int removedCount = 0;
        LinkedNode<T> current = head;

        while (current != null) {
            LinkedNode<T> runner = current;

            while (runner.next != null) {
                if (current.data.equals(runner.next.data)) {
                    runner.next = runner.next.next;
                    size--;
                    removedCount++;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
        return removedCount;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        LinkedNode<T> curr = head;
        int index = 0;
        while (curr != null) {
            sb.append(String.format("[%d] %s\n", index++, curr.data.toString()));
            curr = curr.next;
        }
        return sb.toString();
    }

    public Object[] toArray(){
        Object[] arr = new Object[size];
        LinkedNode<T> curr = head;
        int i = 0;

        while(curr != null){
            arr[i++] = curr.data;
            curr = curr.next;
        }
        return arr;
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private LinkedNode<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}