package dataStructures;

public interface AVLTreeInterface<K extends Comparable<K>> {

    public AVLNode<K> insert(AVLNode<K> AVLNode, K key);

    public AVLNode<K> search(K key);

    public AVLNode<K> delete(AVLNode<K> root, K key);

    public AVLNode<K> leftRotate(AVLNode<K> AVLNode);

    public AVLNode<K> rightRotate(AVLNode<K> AVLNode);

    public String preOrder(AVLNode<K> AVLNode);

    public String inOrder(AVLNode<K> AVLNode);

    public String postOrder(AVLNode<K> AVLNode);
}