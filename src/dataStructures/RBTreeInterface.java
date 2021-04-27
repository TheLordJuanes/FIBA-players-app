package dataStructures;

import java.util.List;

public interface RBTreeInterface<K extends Comparable<K>, V extends List<E>, E extends Number> {

    public void insert(RBNode<K, V> node, E index);

    public RBNode<K, V> search(K key);

    public boolean delete(RBNode<K, V> node);

    public String preOrder(RBNode<K, V> root);

    public String inOrder(RBNode<K, V> root);

    public String postOrder(RBNode<K, V> root);
}