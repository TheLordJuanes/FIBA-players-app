package dataStructures;

public class AVLNode<K extends Comparable<K>,V> {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private K key;
    private V value;
    private int height;
    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private AVLNode<K, V> parent;
    private AVLNode<K, V> left;
    private AVLNode<K, V> right;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public AVLNode(K key, V value) {
        this.key = key;
        this.value = value;
        height = 1;
    }

    /**
     * @return K return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * @return V return the value
     */
    public V getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * @return int return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return AVLNode<K, V> return the parent
     */
    public AVLNode<K, V> getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(AVLNode<K, V> parent) {
        this.parent = parent;
    }

    /**
     * @return AVLNode<K, V> return the left
     */
    public AVLNode<K, V> getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(AVLNode<K, V> left) {
        this.left = left;
    }

    /**
     * @return AVLNode<K, V> return the right
     */
    public AVLNode<K, V> getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(AVLNode<K, V> right) {
        this.right = right;
    }
}