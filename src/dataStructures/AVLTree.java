package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V extends List<E>, E extends Number & Comparable<E>> implements AVLTreeInterface<K, V, E> { // class adapted from GeeksForGeeks https://www.geeksforgeeks.org/avl-tree-set-2-deletion/

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private AVLNode<K, V> root;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public AVLTree() {
    }

    public AVLNode<K, V> getRoot() {
        return root;
    }

    @Override
    public void insert(AVLNode<K, V> node, E index) {
        AVLNode<K, V> node1 = search(node.getKey());
        if (node1 == null)
            privateInsert(node);
        else
            node1.getValue().add(index);
    }

    private void privateInsert(AVLNode<K, V> node) {
        AVLNode<K, V> y = null;
        AVLNode<K, V> x = root;
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
        else {
            y.setRight(node);
        }
        reBalance(node);
    }

    @Override
    public AVLNode<K, V> search(K key) {
        return privateSearch(root, key);
    }

    private AVLNode<K, V> privateSearch(AVLNode<K, V> r, K key) {
        if (r == null || key.compareTo(r.getKey()) == 0)
            return r;
        if (key.compareTo(r.getKey()) < 0)
            return privateSearch(r.getLeft(), key);
        return privateSearch(r.getRight(), key);
    }

    public ArrayList<AVLNode<K, V>> searchMajor(K key) {
        ArrayList<AVLNode<K, V>> nodes = new ArrayList<>();
        pSearchMajor(root, key, nodes);
        return nodes;
    }

    private void pSearchMajor(AVLNode<K, V> node, K key, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            if (node.getKey().compareTo(key) > 0) {
                nodes.add(node);
                addNode(node.getRight(), nodes);
                pSearchMajor(node.getLeft(), key, nodes);
            } else {
                pSearchMajor(node.getRight(), key, nodes);
            }
        }
    }

    public ArrayList<AVLNode<K, V>> searchMajorEqual(K key) {
        ArrayList<AVLNode<K, V>> nodes = new ArrayList<>();
        pSearchMajorEqual(root, key, nodes);
        return nodes;
    }

    private void pSearchMajorEqual(AVLNode<K, V> node, K key, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            if ((node.getKey().compareTo(key) > 0) || (node.getKey().compareTo(key) == 0)) {
                nodes.add(node);
                addNode(node.getRight(), nodes);
                pSearchMajorEqual(node.getLeft(), key, nodes);
            } else {
                pSearchMajorEqual(node.getRight(), key, nodes);
            }
        }
    }

    public ArrayList<AVLNode<K, V>> searchMinor(K key) {
        ArrayList<AVLNode<K, V>> nodes = new ArrayList<>();
        pSearchMinor(root, key, nodes);
        return nodes;
    }

    private void pSearchMinor(AVLNode<K, V> node, K key, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            if (node.getKey().compareTo(key) < 0) {
                nodes.add(node);
                addNode(node.getLeft(), nodes);
                pSearchMinor(node.getRight(), key, nodes);
            } else {
                pSearchMinor(node.getLeft(), key, nodes);
            }
        }
    }

    public ArrayList<AVLNode<K, V>> searchMinorEqual(K key) {
        ArrayList<AVLNode<K, V>> nodes = new ArrayList<>();
        pSearchMinorEqual(root, key, nodes);
        return nodes;
    }

    private void pSearchMinorEqual(AVLNode<K, V> node, K key, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            if ((node.getKey().compareTo(key) < 0) || (node.getKey().compareTo(key) == 0)) {
                nodes.add(node);
                addNode(node.getLeft(), nodes);
                pSearchMinorEqual(node.getRight(), key, nodes);
            } else {
                pSearchMinorEqual(node.getLeft(), key, nodes);
            }
        }
    }

    private void addNode(AVLNode<K, V> node, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            addNode(node.getLeft(), nodes);
            nodes.add(node);
            addNode(node.getRight(), nodes);
        }
    }

    @Override
    public boolean delete(K key, E expected) {
        AVLNode<K, V> toErase = privateSearch(root, key);
        if (toErase != null) {
            V positions = toErase.getValue();
            int length = positions.size();
            if (length > 1) {
                for (int i = 0; i < length; i++) {
                    if (positions.get(i).compareTo(expected) == 0) {
                        positions.remove(i);
                    }
                }
            } else {
                privateDelete(toErase);
            }
            return true;
        }
        return false;
    }

    private void privateDelete(AVLNode<K, V> current) {
        if (current.getLeft() == null && current.getRight() == null) {
            if (current.equals(root))
                root = null;
            else if (current.getParent().getLeft().equals(current))
                current.getParent().setLeft(null);
            else if (current.getParent().getRight().equals(current)) {
                current.getParent().setRight(null);
            }
            current.setParent(null);
        } else if (current.getLeft() == null || current.getRight() == null) {
            AVLNode<K, V> child = current.getLeft() != null ? current.getLeft() : current.getRight();
            if (current.equals(root)) {
                root = child;
                root.setParent(null);
            } else {
                if (current.getParent().getLeft().equals(current))
                    current.getParent().setLeft(child);
                else {
                    current.getParent().setRight(child);
                }
                child.setParent(current.getParent());
                current.setRight(null);
                current.setLeft(null);
            }
        } else {
            AVLNode<K, V> nodeMostToLeft = successor(current);
            if (nodeMostToLeft != null) {
                privateDelete(nodeMostToLeft);
                current.getParent().setLeft(nodeMostToLeft);
            }
        }
        current.setHeight(maxHeight(height(current.getLeft()), height(current.getRight())) + 1);
        int balance = getBalance(current);
        if (balance > 1 && getBalance(current.getLeft()) >= 0)
            rightRotate(current);
        if (balance > 1 && getBalance(current.getLeft()) < 0) {
            current.setLeft(leftRotate(current.getLeft()));
            rightRotate(current);
        }
        if (balance < -1 && getBalance(current.getRight()) <= 0)
            leftRotate(current);
        if (balance < -1 && getBalance(current.getRight()) > 0) {
            current.setRight(rightRotate((AVLNode<K, V>) current.getRight()));
            leftRotate(current);
        }
    }

    private void reBalance(AVLNode<K, V> node) {
        node.setHeight(1 + maxHeight(height(node.getLeft()), height(node.getRight())));
        int balance = getBalance(node);
        if (balance > 1 && node.getKey().compareTo(node.getLeft().getKey()) < 0)
            rightRotate(node);
        if (balance < -1 && node.getKey().compareTo(node.getRight().getKey()) > 0)
            leftRotate(node);
        if (balance > 1 && node.getKey().compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            rightRotate(node);
        }
        if (balance < -1 && node.getKey().compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            leftRotate(node);
        }
    }

    private int height(AVLNode<K, V> node) {
        if (node == null)
            return 0;
        return node.getHeight();
    }

    private int maxHeight(int a, int b) {
        return a > b ? a : b;
    }

    private AVLNode<K, V> leftRotate(AVLNode<K, V> node) {
        node.getRight().setLeft(node);
        node.setRight(node.getRight().getLeft());
        node.setHeight(maxHeight(height(node.getLeft()), height(node.getRight())) + 1);
        node.getRight().setHeight(maxHeight(height(node.getRight().getLeft()), height(node.getRight().getRight())) + 1);
        return node.getRight();
    }

    private AVLNode<K, V> rightRotate(AVLNode<K, V> node) {
        node.getLeft().setRight(node);
        node.setLeft(node.getLeft().getRight());
        node.setHeight(maxHeight(height(node.getLeft()), height(node.getRight())) + 1);
        node.getLeft().setHeight(maxHeight(height(node.getLeft().getLeft()), height(node.getLeft().getRight())) + 1);
        return (AVLNode<K, V>) node.getLeft();
    }

    public AVLNode<K, V> predecessor(AVLNode<K, V> node) {
        if (node.getLeft() != null) {
            return maximum((AVLNode<K, V>) node.getLeft());
        }
        AVLNode<K, V> y = (AVLNode<K, V>) node.getParent();
        while (y != null && node.equals(y.getLeft())) {
            node = y;
            y = (AVLNode<K, V>) y.getParent();
        }
        return y;
    }

    public AVLNode<K, V> successor(AVLNode<K, V> node) {
        if (node.getRight() != null) {
            return minimum((AVLNode<K, V>) node.getRight());
        }
        AVLNode<K, V> y = (AVLNode<K, V>) node.getParent();
        while (y != null && node.equals(y.getRight())) {
            node = y;
            y = (AVLNode<K, V>) y.getParent();
        }
        return y;
    }

    private int getBalance(AVLNode<K, V> node) {
        if (node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    private AVLNode<K, V> minimum(AVLNode<K, V> node) {
        while (node.getLeft() != null)
            node = (AVLNode<K, V>) node.getLeft();
        return node;
    }

    private AVLNode<K, V> maximum(AVLNode<K, V> node) {
        while (node.getRight() != null)
            node = (AVLNode<K, V>) node.getRight();
        return node;
    }

    @Override
    public String preOrder(AVLNode<K, V> node) {
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
    public String inOrder(AVLNode<K, V> node) {
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
    public String postOrder(AVLNode<K, V> node) {
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