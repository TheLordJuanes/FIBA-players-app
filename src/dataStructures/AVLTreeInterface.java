package dataStructures;

public interface AVLTreeInterface<K extends Comparable<K>, V> {

    public void insert(AVLNode<K,V> node);

    public AVLNode<K,V> search(AVLNode<K,V> r,K key);
    
    public AVLNode<K,V> search(AVLNode<K,V> r,K key,V value);

    public boolean delete(K key);

    public String preOrder(AVLNode<K, V> node);

    public String inOrder(AVLNode<K, V> node);

    public String postOrder(AVLNode<K, V> node);
}