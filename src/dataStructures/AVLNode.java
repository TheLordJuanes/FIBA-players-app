package dataStructures;

public class AVLNode<K extends Comparable<K>> {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private K key;
    private int height;

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private AVLNode<K> parent;
    private AVLNode<K> left;
    private AVLNode<K> right;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public AVLNode(K key) {
        this.key = key;
        height = 1;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLNode<K> getParent() {
        return parent;
    }

    public void setParent(AVLNode<K> parent) {
        this.parent = parent;
    }

    public AVLNode<K> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<K> left) {
        this.left = left;
    }

    public AVLNode<K> getRight() {
        return right;
    }

    public void setRight(AVLNode<K> right) {
        this.right = right;
    }
}