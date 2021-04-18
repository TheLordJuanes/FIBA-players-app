package dataStructures;

public interface BSTreeInterface<K extends Comparable<K>, V> {

    public void insert(BSTNode<K, V> node);

    public BSTNode<K, V> search(BSTNode<K, V> r, K key);

    public boolean delete(K key);

    public String preOrder(BSTNode<K,V> root);

    public String inOrder(BSTNode<K,V> root);

    public String postOrder(BSTNode<K,V> root);
}