package dataStructures;

import java.util.List;

public interface AVLTreeInterface<K extends Comparable<K>, V extends List<E>, E extends Number> {

    public void insert(AVLNode<K, V> node, E index);

    public AVLNode<K, V> search(K key);

    public boolean delete(K key, E expected);

    public String preOrder(AVLNode<K, V> node);

    public String inOrder(AVLNode<K, V> node);

    public String postOrder(AVLNode<K, V> node);
}