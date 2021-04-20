package dataStructures;

import exceptions.RBTreeException;

public interface RBTreeInterface<K extends Comparable<K>, V> {

    public void insert(RBNode<K, V> node);

    public RBNode<K, V> search(K key);

    public void delete(RBNode<K, V> root, K key) throws RBTreeException;

    public String preOrder(RBNode<K, V> root);

    public String inOrder(RBNode<K, V> root);

    public String postOrder(RBNode<K, V> root);
}