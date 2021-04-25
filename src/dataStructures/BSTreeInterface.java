package dataStructures;

import java.util.List;

public interface BSTreeInterface<K extends Comparable<K>, V extends List<E>, E extends Number> {

    public void insert(BSTNode<K, V> node);

    public BSTNode<K, V> search(K key);

    public boolean delete(K key,E expected);

    public String preOrder(BSTNode<K,V> root);

    public String inOrder(BSTNode<K,V> root);

    public String postOrder(BSTNode<K,V> root);
}