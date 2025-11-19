package ds.tree;


public class BSTNode<T> {
    private T value;
    private BSTNode<T> left;
    private BSTNode<T> right;
    public BSTNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
    public T getValue() { return value; }
    public BSTNode<T> getLeft() { return left; }
    public BSTNode<T> getRight() { return right; }
    public void setValue(T value) { this.value = value; }
    public void setLeft(BSTNode<T> left) { this.left = left; }
    public void setRight(BSTNode<T> right) { this.right = right; }
}