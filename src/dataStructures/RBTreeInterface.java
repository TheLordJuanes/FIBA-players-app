package dataStructures;

import exceptions.RBTreeException;

public interface RBTreeInterface<K extends Comparable<K>> {

    public void insert(K key);

    public RBNode<K> search(K key);

    public void delete(RBNode<K> root, K key) throws RBTreeException;

    public String preOrder(RBNode<K> root);

    public String inOrder(RBNode<K> root);

    public String postOrder(RBNode<K> root);
}