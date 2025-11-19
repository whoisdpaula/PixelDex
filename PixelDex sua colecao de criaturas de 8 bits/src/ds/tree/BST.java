package ds.tree;

import domain.Pixel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BST<T> {
    private BSTNode<T> root;
    private int size = 0;
    private final Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    public void insert(T value) {
        root = insert(root, value);
    }

    private BSTNode<T> insert(BSTNode<T> node, T value) {
        if (node == null) {
            size++;
            return new BSTNode<>(value);
        }
        int cmp = comparator.compare(value, node.getValue());
        if (cmp < 0) {
            node.setLeft(insert(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(insert(node.getRight(), value));
        }
        return node;
    }
    public T search(T value) {
        BSTNode<T> node = searchNode(root, value);
        return (node != null) ? node.getValue() : null;
    }
    private BSTNode<T> searchNode(BSTNode<T> node, T value) {
        if (node == null) return null;
        Pixel searchPixel = (Pixel) value;
        Pixel currentNodePixel = (Pixel) node.getValue();
        int nameCompare = searchPixel.getName().compareToIgnoreCase(currentNodePixel.getName());
        if (nameCompare < 0) {
            return searchNode(node.getLeft(), value);
        }
        if (nameCompare > 0) {
            return searchNode(node.getRight(), value);
        }
        return node;
    }
    public T remove(T value) {
        BSTNode<T> tempNode = searchNode(root, value);
        if (tempNode == null) return null;

        root = removeNode(root, value);
        return tempNode.getValue();
    }
    private BSTNode<T> removeNode(BSTNode<T> node, T value) {
        if (node == null) return null;
        Pixel removePixel = (Pixel) value;
        Pixel currentNodePixel = (Pixel) node.getValue();
        int nameCompare = removePixel.getName().compareToIgnoreCase(currentNodePixel.getName());
        if (nameCompare < 0) {
            node.setLeft(removeNode(node.getLeft(), value));
        } else if (nameCompare > 0) {
            node.setRight(removeNode(node.getRight(), value));
        } else {
            if (node.getRight() == null) { size--; return node.getLeft(); }
            if (node.getLeft() == null) { size--; return node.getRight(); }
            BSTNode<T> min = getMinNode(node.getRight());
            node.setValue(min.getValue());
            node.setRight(removeNode(node.getRight(), min.getValue()));
        }
        return node;
    }
    private BSTNode<T> getMinNode(BSTNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
    public int size() {
        return size;
    }
    public int height() {
        return height(root);
    }
    private int height(BSTNode<T> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }
    private void inOrder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            inOrder(node.getLeft(), list);
            list.add(node.getValue());
            inOrder(node.getRight(), list);
        }
    }
    public List<T> preOrder() {
        List<T> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }
    private void preOrder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getValue());
            preOrder(node.getLeft(), list);
            preOrder(node.getRight(), list);
        }
    }
    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }
    private void postOrder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            postOrder(node.getLeft(), list);
            postOrder(node.getRight(), list);
            list.add(node.getValue());
        }
    }
    public List<T> range(T a, T b) {
        List<T> result = new ArrayList<>();
        range(root, a, b, result);
        return result;
    }
    private void range(BSTNode<T> node, T a, T b, List<T> list) {
        if (node == null) return;
        int cmpA = comparator.compare(node.getValue(), a);
        int cmpB = comparator.compare(node.getValue(), b);
        if (cmpA > 0) {
            range(node.getLeft(), a, b, list);
        }
        if (cmpA >= 0 && cmpB <= 0) {
            list.add(node.getValue());
        }
        if (cmpB < 0) {
            range(node.getRight(), a, b, list);
        }
    }
}