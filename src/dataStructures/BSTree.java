package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class BSTree<K extends Comparable<K>, V extends List<E>, E extends Number & Comparable<E>> implements BSTreeInterface<K, V, E> { // class adapted from https://github.com/Bibeknam/algorithmtutorprograms/blob/11ef340f8c8e60839a9dff395dd52b8752c537a6/data-structures/red-black-trees/RedBlackTree.java#L298

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private int sizeNodes;
    private int sizeElements;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private BSTNode<K, V> root;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /** Name: BSTree <br>
	 * <br> Constructor method of a generic Binary Search Tree. <br>
	*/
    public BSTree() {
        sizeNodes = 0;
        sizeElements = 0;
    }

    public BSTNode<K, V> getRoot() {
        return root;
    }

    public int getSizeNodes() {
        return sizeNodes;
    }

    public int getSizeElements() {
        return sizeElements;
    }

    @Override
    public void insert(BSTNode<K, V> node, E index) {
        BSTNode<K, V> node1 = search(node.getKey());
        if (node1 == null) {
            privateInsert(node);
            sizeNodes++;
        } else {
            node1.getValue().add(index);
        }
        sizeElements++;
    }

    private void privateInsert(BSTNode<K, V> node) {
        BSTNode<K, V> y = null;
        BSTNode<K, V> x = root;
        while (x != null) {
            y = x;
            if (node.getKey().compareTo(x.getKey()) < 0)
                x = x.getLeft();
            else
                x = x.getRight();
        }
        node.setParent(y);
        if (y == null)
            root = node;
        else if (node.getKey().compareTo(y.getKey()) < 0)
            y.setLeft(node);
        else
            y.setRight(node);
    }

    @Override
    public BSTNode<K, V> search(K key) {
        return privateSearch(root, key);
    }

    private BSTNode<K, V> privateSearch(BSTNode<K, V> r, K key) {
        if (r == null || key.compareTo(r.getKey()) == 0)
            return r;
        if (key.compareTo(r.getKey()) < 0)
            return privateSearch(r.getLeft(), key);
        return privateSearch(r.getRight(), key);
    }

    public ArrayList<BSTNode<K, V>> searchMajor(K key) {
        ArrayList<BSTNode<K, V>> nodes = new ArrayList<>();
        pSearchMajor(root, key, nodes);
        return nodes;
    }

    private void pSearchMajor(BSTNode<K, V> node, K key, ArrayList<BSTNode<K, V>> nodes) {
        if (node != null) {
            if (node.getKey().compareTo(key) > 0) {
                nodes.add(node);
                addNode(node.getRight(), nodes);
                pSearchMajor(node.getLeft(), key, nodes);
            } else
                pSearchMajor(node.getRight(), key, nodes);
        }
    }

    public ArrayList<BSTNode<K, V>> searchMajorEqual(K key) {
        ArrayList<BSTNode<K, V>> nodes = new ArrayList<>();
        pSearchMajorEqual(root, key, nodes);
        return nodes;
    }

    private void pSearchMajorEqual(BSTNode<K, V> node, K key, ArrayList<BSTNode<K, V>> nodes) {
        if (node != null) {
            if ((node.getKey().compareTo(key) > 0) || (node.getKey().compareTo(key) == 0)) {
                nodes.add(node);
                addNode(node.getRight(), nodes);
                pSearchMajorEqual(node.getLeft(), key, nodes);
            } else
                pSearchMajorEqual(node.getRight(), key, nodes);
        }
    }

    public ArrayList<BSTNode<K, V>> searchMinor(K key) {
        ArrayList<BSTNode<K, V>> nodes = new ArrayList<>();
        pSearchMinor(root, key, nodes);
        return nodes;
    }

    private void pSearchMinor(BSTNode<K, V> node, K key, ArrayList<BSTNode<K, V>> nodes) {
        if (node != null) {
            if (node.getKey().compareTo(key) < 0) {
                nodes.add(node);
                addNode(node.getLeft(), nodes);
                pSearchMinor(node.getRight(), key, nodes);
            } else
                pSearchMinor(node.getLeft(), key, nodes);
        }
    }

    public ArrayList<BSTNode<K, V>> searchMinorEqual(K key) {
        ArrayList<BSTNode<K, V>> nodes = new ArrayList<>();
        pSearchMinorEqual(root, key, nodes);
        return nodes;
    }

    private void pSearchMinorEqual(BSTNode<K, V> node, K key, ArrayList<BSTNode<K, V>> nodes) {
        if (node != null) {
            if ((node.getKey().compareTo(key) < 0) || (node.getKey().compareTo(key) == 0)) {
                nodes.add(node);
                addNode(node.getLeft(), nodes);
                pSearchMinorEqual(node.getRight(), key, nodes);
            } else
                pSearchMinorEqual(node.getLeft(), key, nodes);
        }
    }

    private void addNode(BSTNode<K, V> node, ArrayList<BSTNode<K, V>> nodes) {
        if (node != null) {
            addNode(node.getLeft(), nodes);
            nodes.add(node);
            addNode(node.getRight(), nodes);
        }
    }

    @Override
    public boolean delete(K key, E expected) {
        BSTNode<K, V> toErase = privateSearch(root, key);
        if (toErase != null) {
            V positions = toErase.getValue();
            if (positions.size() > 1) {
                for (int i = 0; i < positions.size(); i++) {
                    if (positions.get(i).compareTo(expected) == 0){
                        positions.remove(i);
                        sizeElements--;
                        return true;
                    }
                }
            } else {
                root = deleteRec(root, key);
                sizeNodes--;
                sizeElements--;
                return true;
            }
        }
        return false;
    }

    private BSTNode<K, V> deleteRec(BSTNode<K, V> root, K key) {
        if (root == null)
            return root;
        if (key.compareTo(root.getKey())<0)
            root.setLeft(deleteRec(root.getLeft(), key));
        else if (key.compareTo(root.getKey())>0)
            root.setRight(deleteRec(root.getRight(), key));
        else {
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();
            
            BSTNode<K, V> temp = minValue(root.getRight());  
            root.setKey(temp.getKey());
            root.setValue(temp.getValue());
            root.setRight(deleteRec(root.getRight(), root.getKey()));
        }
        return root;
    }

    private BSTNode<K, V> minValue(BSTNode<K, V> root){
        while(root.getLeft()!=null){
            root = root.getLeft();
        }
        return root;
    }

    private BSTNode<K, V> minimum(BSTNode<K, V> node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    public BSTNode<K, V> maximum(BSTNode<K, V> node) {
        while (node.getRight() != null)
            node = node.getRight();
        return node;
    }

    public BSTNode<K, V> predecessor(BSTNode<K, V> x) {
        if (x.getLeft() != null) {
            return maximum(x.getLeft());
        }
        BSTNode<K, V> y = x.getParent();
        while (y != null && x.equals(y.getLeft())) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public BSTNode<K, V> successor(BSTNode<K, V> x) {
        if (x.getRight() != null) {
            return minimum(x.getRight());
        }
        BSTNode<K, V> y = x.getParent();
        while (y != null && x.equals(y.getRight())) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    @Override
    public String preOrder(BSTNode<K, V> node) {
        String keys = "";
        if (node == null) {
            return keys;
        }
        keys += node.getKey() + " ";
        keys += preOrder(node.getLeft());
        keys += preOrder(node.getRight());
        return keys;
    }

    @Override
    public String inOrder(BSTNode<K, V> node) {
        String keys = "";
        if (node == null) {
            return keys;
        }
        keys += inOrder(node.getLeft());
        keys += node.getKey() + " ";
        keys += inOrder(node.getRight());
        return keys;
    }

    @Override
    public String postOrder(BSTNode<K, V> node) {
        String keys = "";
        if (node == null) {
            return keys;
        }
        keys += postOrder(node.getLeft());
        keys += postOrder(node.getRight());
        keys += node.getKey() + " ";
        return keys;
    }
}