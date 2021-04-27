package dataStructures;

public class BSTNode<K extends Comparable<K>, V> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private K key;
    private V value;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private BSTNode<K, V> parent;
    private BSTNode<K, V> left;
    private BSTNode<K, V> right;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public BSTNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BSTNode<K, V> getParent() {
        return parent;
    }

    public void setParent(BSTNode<K, V> parent) {
        this.parent = parent;
    }

    public BSTNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<K, V> left) {
        this.left = left;
    }

    public BSTNode<K, V> getRight() {
        return right;
    }

    public void setRight(BSTNode<K, V> right) {
        this.right = right;
    }
}