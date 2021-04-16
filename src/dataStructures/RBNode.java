package dataStructures;

public class RBNode<K extends Comparable<K>> {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private K key;
    private int color; // 1 = Red | 0 = Black

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private RBNode<K> parent;
    private RBNode<K> left;
    private RBNode<K> right;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public RBNode(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public RBNode<K> getParent() {
        return parent;
    }

    public void setParent(RBNode<K> parent) {
        this.parent = parent;
    }

    public RBNode<K> getLeft() {
        return left;
    }

    public void setLeft(RBNode<K> left) {
        this.left = left;
    }

    public RBNode<K> getRight() {
        return right;
    }

    public void setRight(RBNode<K> right) {
        this.right = right;
    }
}