package dataStructures;

public class Node<K extends Comparable<K>> {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private K key;
    private int height;

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private Node<K> left;
    private Node<K> right;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public Node(K key) {
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

    public Node<K> getLeft() {
        return left;
    }

    public void setLeft(Node<K> left) {
        this.left = left;
    }

    public Node<K> getRight() {
        return right;
    }

    public void setRight(Node<K> right) {
        this.right = right;
    }
}