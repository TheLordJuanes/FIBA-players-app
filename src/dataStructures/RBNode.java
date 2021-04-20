package dataStructures;

public class RBNode<K extends Comparable<K>, V> {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private K key;
    private V value;
    private int color; // 1 = Red | 0 = Black

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private RBNode<K, V> parent;
    private RBNode<K, V> left;
    private RBNode<K, V> right;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public RBNode(K key, V value) {
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public RBNode<K, V> getParent() {
        return parent;
    }

    public void setParent(RBNode<K, V> parent) {
        this.parent = parent;
    }

    public RBNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(RBNode<K, V> left) {
        this.left = left;
    }

    public RBNode<K, V> getRight() {
        return right;
    }

    public void setRight(RBNode<K, V> right) {
        this.right = right;
    }
}