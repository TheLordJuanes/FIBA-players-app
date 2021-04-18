package dataStructures;

public class AVLTree<K extends Comparable<K>> implements AVLTreeInterface<K> { // class adapted from GeeksForGeeks https://www.geeksforgeeks.org/avl-tree-set-2-deletion/

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private AVLNode<K> root;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public AVLTree(AVLNode<K> root) {
        this.root = root;
    }

    public AVLNode<K> getRoot() {
        return root;
    }

    @Override
    public AVLNode<K> insert(AVLNode<K> node, K key) {
        if (node == null)
            return new AVLNode<K>(key);
        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(insert(node.getLeft(), key));
        else if (key.compareTo(node.getKey()) > 0)
            node.setRight(insert(node.getRight(), key));
        else {
            return node;
        }
        node.setHeight(1 + maxHeight(height(node.getLeft()), height(node.getRight())));
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
    public AVLNode<K> search(K key) {
        if (root != null)
            return privateSearch(root, key);
        return null;
    }

    private AVLNode<K> privateSearch(AVLNode<K> current, K key) {
        AVLNode<K> objSearch = null;
        if (current.getKey().compareTo(key) == 0)
            objSearch = current;
        else if (key.compareTo(current.getKey()) < 0 && current.getLeft() != null)
            objSearch = privateSearch(current.getLeft(), key);
        else if (key.compareTo(current.getKey()) > 0 && current.getRight() != null)
            objSearch = privateSearch(current.getRight(), key);
        return objSearch;
    }

    @Override
    public AVLNode<K> delete(AVLNode<K> current, K key) {
        if (current == null)
            return current;
        if (key.compareTo(current.getKey()) < 0)
            current.setLeft(delete(current.getLeft(), key));
        else if (key.compareTo(current.getKey()) > 0)
            current.setRight(delete(current.getRight(), key));
        else {
            if ((current.getLeft() == null) || (current.getRight() == null)) {
                AVLNode<K> temp = null;
                if (temp == current.getLeft())
                    temp = current.getRight();
                else
                    temp = current.getLeft();
                if (temp == null) {
                    temp = current;
                    current = null;
                } else
                    current = temp;
            } else {
                AVLNode<K> temp = minimum(current.getRight());
                current.setKey(temp.getKey());
                current.setRight(delete(current.getRight(), temp.getKey()));
            }
        }
        current.setHeight(maxHeight(height(current.getLeft()), height(current.getRight())) + 1);
        int balance = getBalance(current);
        if (balance > 1 && getBalance(current.getLeft()) >= 0)
            return rightRotate(current);
        if (balance > 1 && getBalance(current.getLeft()) < 0) {
            current.setLeft(leftRotate(current.getLeft()));
            return rightRotate(current);
        }
        if (balance < -1 && getBalance(current.getRight()) <= 0)
            return leftRotate(current);
        if (balance < -1 && getBalance(current.getRight()) > 0) {
            current.setRight(rightRotate(current.getRight()));
            return leftRotate(current);
        }
        return current;
    }

    private int height(AVLNode<K> node) {
        if (node == null)
            return 0;
        return node.getHeight();
    }

    private int maxHeight(int a, int b) {
        return a > b ? a : b;
    }

    private AVLNode<K> leftRotate(AVLNode<K> node) {
        node.getRight().setLeft(node);
        node.setRight(node.getRight().getLeft());
        node.setHeight(maxHeight(height(node.getLeft()), height(node.getRight())) + 1);
        node.getRight().setHeight(maxHeight(height(node.getRight().getLeft()), height(node.getRight().getRight())) + 1);
        return node.getRight();
    }

    private AVLNode<K> rightRotate(AVLNode<K> node) {
        node.getLeft().setRight(node);
        node.setLeft(node.getLeft().getRight());
        node.setHeight(maxHeight(height(node.getLeft()), height(node.getRight())) + 1);
        node.getLeft().setHeight(maxHeight(height(node.getLeft().getLeft()), height(node.getLeft().getRight())) + 1);
        return node.getLeft();
    }

    public AVLNode<K> predecessor(AVLNode<K> node) {
		if (node.getLeft() != null) {
			return maximum(node.getLeft());
		}
		AVLNode<K> y = node.getParent();
		while (y != null && node.equals(y.getLeft())) {
			node = y;
			y = y.getParent();
		}
		return y;
	}

    public AVLNode<K> successor(AVLNode<K> node) {
		if (node.getRight() != null) {
			return minimum(node.getRight());
		}
		AVLNode<K> y = node.getParent();
		while (y != null && node.equals(y.getRight())) {
			node = y;
			y = y.getParent();
		}
		return y;
	}

    private int getBalance(AVLNode<K> node) {
        if (node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    private AVLNode<K> minimum(AVLNode<K> node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    private AVLNode<K> maximum(AVLNode<K> node) {
		while (node.getRight() != null)
            node = node.getRight();
		return node;
	}

    @Override
    public String preOrder(AVLNode<K> node) {
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
    public String inOrder(AVLNode<K> node) {
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
    public String postOrder(AVLNode<K> node) {
        String keys = "";
		if (node != null) {
			keys += postOrder(node.getLeft());
			keys += postOrder(node.getRight());
			keys += node.getKey() + " ";
            return keys;
		}
        return null;
	}
}