package dataStructures;

public class AVLTree<K extends Comparable<K>> implements AVLTreeInterface<K> { // class adapted from GeeksForGeeks https://www.geeksforgeeks.org/avl-tree-set-2-deletion/

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private Node<K> root;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public AVLTree(Node<K> root) {
        this.root = root;
    }

    public Node<K> getRoot() {
        return root;
    }

    public void setRoot(Node<K> root) {
        this.root = root;
    }

    @Override
    public Node<K> insert(Node<K> node, K key) {
        if (node == null)
            return (new Node<K>(key));
        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(insert(node.getLeft(), key));
        else if (key.compareTo(node.getKey()) > 0)
            node.setRight(insert(node.getRight(), key));
        else {
            return node;
        }
        node.setHeight(1 + max(height(node.getLeft()), height(node.getRight())));
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.getLeft().getKey()) < 0)
            return rightRotate(node);
        if (balance < -1 && key.compareTo(node.getRight().getKey()) > 0)
            return leftRotate(node);
        if (balance > 1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }

    @Override
    public Node<K> search(K key) {
        if (root != null)
            return privateSearch(root, key);
        return null;
    }

    private Node<K> privateSearch(Node<K> current, K key) {
        Node<K> objSearch = null;
        if (current.getKey().compareTo(key) == 0)
            objSearch = current;
        else if (key.compareTo(current.getKey()) < 0 && current.getLeft() != null)
            objSearch = privateSearch(current.getLeft(), key);
        else if (key.compareTo(current.getKey()) > 0 && current.getRight() != null)
            objSearch = privateSearch(current.getRight(), key);
        return objSearch;
    }

    @Override
    public Node<K> deleteNode(Node<K> root, K key) {
        if (root == null)
            return root;
        if (key.compareTo(root.getKey()) < 0)
            root.setLeft(deleteNode(root.getLeft(), key));
        else if (key.compareTo(root.getKey()) > 0)
            root.setRight(deleteNode(root.getRight(), key));
        else {
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                Node<K> temp = null;
                if (temp == root.getLeft())
                    temp = root.getRight();
                else
                    temp = root.getLeft();
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node<K> temp = minValueNode(root.getRight());
                root.setKey(temp.getKey());
                root.setRight(deleteNode(root.getRight(), temp.getKey()));
            }
        }
        root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rightRotate(root);
        if (balance > 1 && getBalance(root.getLeft()) < 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return leftRotate(root);
        if (balance < -1 && getBalance(root.getRight()) > 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
        return root;
    }

    private int height(Node<K> node) {
        if (node == null)
            return 0;
        return node.getHeight();
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public Node<K> rightRotate(Node<K> y) {
        Node<K> x = y.getLeft();
        Node<K> T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        return x;
    }

    public Node<K> leftRotate(Node<K> x) {
        Node<K> y = x.getRight();
        Node<K> T2 = y.getLeft();
        y.setLeft(x);
        x.setRight(T2);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        return y;
    }

    private int getBalance(Node<K> N) {
        if (N == null)
            return 0;
        return height(N.getLeft()) - height(N.getRight());
    }

    private Node<K> minValueNode(Node<K> node) {
        Node<K> current = node;
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public String preOrder(Node<K> node) {
        String keys = "";
        if (node != null) {
            keys += node.getKey() + " ";
            keys += preOrder(node.getLeft());
            keys += preOrder(node.getRight());
            return keys;
        }
        return null;
    }
}