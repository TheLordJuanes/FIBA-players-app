package dataStructures;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> implements AVLTreeInterface<K, V>  { // class adapted from GeeksForGeeks https://www.geeksforgeeks.org/avl-tree-set-2-deletion/

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
    public void insert(AVLNode<K, V> node) {
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
        rebalance(node);
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

    @Override
    public boolean delete(K key) {
		AVLNode<K, V> toErase = privateSearch(root, key);
        if (toErase != null) {
            privateDelete(toErase);
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
            if (nodeMostToLeft != null ) {
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

    public void rebalance(AVLNode<K, V> node) {
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
        return (AVLNode<K, V>) node.getRight();
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
        if (node != null) {
            keys += node.getKey() + " ";
            keys += preOrder(node.getLeft());
            keys += preOrder(node.getRight());
            return keys;
        }
        return null;
    }

    @Override
    public String inOrder(AVLNode<K, V> node) {
        String keys = "";
        if (node != null) {
            keys += inOrder(node.getLeft());
            keys += node.getKey() + " ";
            keys += inOrder(node.getRight());
            return keys;
        }
        return null;
    }

    @Override
    public String postOrder(AVLNode<K, V> node) {
        String keys = "";
		if (node != null) {
			keys += postOrder(node.getLeft());
			keys += postOrder(node.getRight());
			keys += node.getValue().toString() + " ";
            return keys;
		}
        return null;
	}
}