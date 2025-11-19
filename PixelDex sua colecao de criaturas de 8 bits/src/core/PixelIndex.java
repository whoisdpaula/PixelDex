package core;

import domain.Pixel;
import domain.Raridade;
import ds.tree.BST;
import ds.hash.IntHashTable;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class PixelIndex {
    public static final Comparator<Pixel> BY_NAME_THEN_ID = (a, b) -> {
        int nameCompare = a.getName().compareToIgnoreCase(b.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }
        return Integer.compare(a.getId(), b.getId());
    };
    private final BST<Pixel> bstIndex;
    private final IntHashTable<Pixel> hashIndex;
    public PixelIndex() {
        this.bstIndex = new BST<>(BY_NAME_THEN_ID);
        this.hashIndex = new IntHashTable<>();
    }
    public void add(Pixel pixel) {
        bstIndex.insert(pixel);
        hashIndex.put(pixel.getId(), pixel);
    }
    public Pixel remove(String nome) {
        Pixel keyPixel = new Pixel(-1, nome, Raridade.COMUM, 0);
        Pixel removed = bstIndex.remove(keyPixel);
        if (removed != null) {
            hashIndex.remove(removed.getId());
        }
        return removed;
    }
    public Pixel remove(int id) {
        Pixel pixelToRemove = hashIndex.get(id);

        if (pixelToRemove == null) {
            throw new NoSuchElementException("Pixel com ID " + id + " não encontrado no índice.");
        }
        Pixel removed = bstIndex.remove(pixelToRemove);

        if (removed == null) {
            throw new RuntimeException("Erro interno: Pixel encontrado no hash, mas não na BST.");
        }
        hashIndex.remove(removed.getId());

        return removed;
    }
    public Pixel searchByName(String nome) {
        Pixel keyPixel = new Pixel(-1, nome, Raridade.COMUM, 0);
        return bstIndex.search(keyPixel);
    }
    public Pixel searchById(int id) {
        return hashIndex.get(id);
    }
    public List<Pixel> getInOrder() {
        return bstIndex.inOrder();
    }
    public List<Pixel> getPreOrder() {
        return bstIndex.preOrder();
    }
    public List<Pixel> getPostOrder() {
        return bstIndex.postOrder();
    }
    public List<Pixel> range(String nomeA, String nomeB) {
        Pixel a = new Pixel(-1, nomeA, Raridade.COMUM, 0);
        Pixel b = new Pixel(-1, nomeB, Raridade.COMUM, 0);
        return bstIndex.range(a, b);
    }
    public int getHeight() {
        return bstIndex.height();
    }
    public int bstSize() {
        return bstIndex.size();
    }
    public int size(){
        return bstSize();
    }
}