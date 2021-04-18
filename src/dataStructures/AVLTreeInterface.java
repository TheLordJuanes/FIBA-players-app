package dataStructures;

public interface AVLTreeInterface<K extends Comparable<K>> {

    public AVLNode<K> insert(AVLNode<K> node, K key);

    public AVLNode<K> search(K key);

    public AVLNode<K> delete(AVLNode<K> root, K key);

    public String preOrder(AVLNode<K> node);

    public String inOrder(AVLNode<K> node);

    public String postOrder(AVLNode<K> node);
}