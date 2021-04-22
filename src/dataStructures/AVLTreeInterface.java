package dataStructures;

public interface AVLTreeInterface<K extends Comparable<K>, V> {

    public void insert(AVLNode<K,V> node);

    public AVLNode<K,V> search(K key);

    public boolean delete(K key);

    public String preOrder(AVLNode<K, V> node);

    public String inOrder(AVLNode<K, V> node);

    public String postOrder(AVLNode<K, V> node);
}