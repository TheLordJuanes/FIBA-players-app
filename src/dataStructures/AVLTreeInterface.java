package dataStructures;

public interface AVLTreeInterface<K extends Comparable<K>, V> {

    public boolean insert(AVLNode<K,V> node, K key);

    public AVLNode<K,V> search(K key);

    public AVLNode<K, V> delete(AVLNode<K, V> root, K key);

    public String preOrder(AVLNode<K, V> node);

    public String inOrder(AVLNode<K, V> node);

    public String postOrder(AVLNode<K, V> node);
}