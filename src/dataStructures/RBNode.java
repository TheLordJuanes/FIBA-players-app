package dataStructures;
import java.io.Serializable;
public class RBNode<K extends Comparable<K>, V> {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public enum COLOR {
        RED, BLACK;
    }

    private COLOR color;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private K key;
    private V value;

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
        color = COLOR.RED;
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
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

    public RBNode<K, V> uncle() {
        if (parent == null || parent.getParent() == null)
            return null;
        if (parent.isOnLeft())
            return parent.getParent().getRight();
        else
            return parent.getParent().getLeft();
    }

    public boolean isOnLeft() {
        return this == parent.getLeft();
    }

    public RBNode<K, V> sibling() {
        if (parent == null)
            return null;
        if (isOnLeft())
            return parent.getRight();
        return parent.getLeft();
    }

    public void moveDown(RBNode<K, V> nParent) {
        if (parent != null) {
            if (isOnLeft())
                parent.setLeft(nParent);
            else
                parent.setRight(nParent);
        }
        nParent.setParent(parent);
        parent = nParent;
    }

    public boolean hasRedChild() {
        return (left != null && left.color == COLOR.RED) || (right != null && right.color == COLOR.RED);
    }
}