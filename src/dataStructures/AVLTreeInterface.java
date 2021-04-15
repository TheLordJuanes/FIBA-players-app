package dataStructures;

public interface AVLTreeInterface<K extends Comparable<K>> {

    public Node<K> insert(Node<K> node, K key);

    public Node<K> search(K key);

    public Node<K> deleteNode(Node<K> root, K key);

    public String preOrder(Node<K> node);
}