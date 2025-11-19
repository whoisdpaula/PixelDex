package core;

import domain.Pixel;
import ds.list.LinkedList;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.List;

public class PixelCollection {
    private final LinkedList<Pixel> collection = new LinkedList<>();
    public void add(Pixel p){
        collection.addLast(p);
    }

    public Pixel removeAt(int index) {
        try {
            return collection.removeAt(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Erro: Índice fora dos limites para remoção.");
        }
    }
    public void print(){
        System.out.println(collection.display());
    }
    public int size(){
        return collection.size();
    }
    public boolean isEmpty(){
        return collection.size() == 0;
    }
    public void reverse() {
        collection.reverse();
    }
    public void move(int fromIndex, int toIndex) {
        collection.move(fromIndex, toIndex);
    }
    public List<Pixel> slice(int fromIndex, int toIndex) {
        return collection.slice(fromIndex, toIndex);
    }
    public int unique() {
        return collection.unique();
    }
    public Pixel[] toArray() {
        if (collection.size() == 0) {
            return new Pixel[0];
        }
        Pixel[] pixelsArray = new Pixel[collection.size()];
        int i = 0;
        Iterator<Pixel> iterator = collection.iterator();
        while (iterator.hasNext()) {
            pixelsArray[i] = iterator.next();
            i++;
        }
        return pixelsArray;
    }
}