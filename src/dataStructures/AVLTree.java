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

    public void setRoot(AVLNode<K> root) {
        this.root = root;
    }

    @Override
    public AVLNode<K> insert(AVLNode<K> AVLNode, K key) {
        if (AVLNode == null)
            return new AVLNode<K>(key);
        if (key.compareTo(AVLNode.getKey()) < 0)
            AVLNode.setLeft(insert(AVLNode.getLeft(), key));
        else if (key.compareTo(AVLNode.getKey()) > 0)
            AVLNode.setRight(insert(AVLNode.getRight(), key));
        else {
            return AVLNode;
        }
        AVLNode.setHeight(1 + maxHeight(height(AVLNode.getLeft()), height(AVLNode.getRight())));
        int balance = getBalance(AVLNode);
        if (balance > 1 && key.compareTo(AVLNode.getLeft().getKey()) < 0)
            return rightRotate(AVLNode);
        if (balance < -1 && key.compareTo(AVLNode.getRight().getKey()) > 0)
            return leftRotate(AVLNode);
        if (balance > 1 && key.compareTo(AVLNode.getLeft().getKey()) > 0) {
            AVLNode.setLeft(leftRotate(AVLNode.getLeft()));
            return rightRotate(AVLNode);
        }
        if (balance < -1 && key.compareTo(AVLNode.getRight().getKey()) < 0) {
            AVLNode.setRight(rightRotate(AVLNode.getRight()));
            return leftRotate(AVLNode);
        }
        return AVLNode;
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
    public AVLNode<K> delete(AVLNode<K> root, K key) {
        if (root == null)
            return root;
        if (key.compareTo(root.getKey()) < 0)
            root.setLeft(delete(root.getLeft(), key));
        else if (key.compareTo(root.getKey()) > 0)
            root.setRight(delete(root.getRight(), key));
        else {
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                AVLNode<K> temp = null;
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
                AVLNode<K> temp = minimum(root.getRight());
                root.setKey(temp.getKey());
                root.setRight(delete(root.getRight(), temp.getKey()));
            }
        }
        root.setHeight(maxHeight(height(root.getLeft()), height(root.getRight())) + 1);
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

    private int height(AVLNode<K> AVLNode) {
        if (AVLNode == null)
            return 0;
        return AVLNode.getHeight();
    }

    private int maxHeight(int a, int b) {
        return a > b ? a : b;
    }

    @Override
    public AVLNode<K> leftRotate(AVLNode<K> x) {
        AVLNode<K> y = x.getRight();
        AVLNode<K> T2 = y.getLeft();
        y.setLeft(x);
        x.setRight(T2);
        x.setHeight(maxHeight(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);
        return y;
    }

    @Override
    public AVLNode<K> rightRotate(AVLNode<K> y) {
        AVLNode<K> x = y.getLeft();
        AVLNode<K> T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(maxHeight(height(x.getLeft()), height(x.getRight())) + 1);
        return x;
    }

    public AVLNode<K> predecessor(AVLNode<K> AVLNode) {
		if (AVLNode.getLeft() != null) {
			return maximum(AVLNode.getLeft());
		}
		AVLNode<K> y = AVLNode.getParent();
		while (y != null && AVLNode == y.getLeft()) {
			AVLNode = y;
			y = y.getParent();
		}
		return y;
	}

    public AVLNode<K> successor(AVLNode<K> AVLNode) {
		if (AVLNode.getRight() != null) {
			return minimum(AVLNode.getRight());
		}
		AVLNode<K> y = AVLNode.getParent();
		while (y != null && AVLNode == y.getRight()) {
			AVLNode = y;
			y = y.getParent();
		}
		return y;
	}

    private int getBalance(AVLNode<K> AVLNode) {
        if (AVLNode == null)
            return 0;
        return height(AVLNode.getLeft()) - height(AVLNode.getRight());
    }

    private AVLNode<K> maximum(AVLNode<K> AVLNode) {
		while (AVLNode.getRight() != null)
			AVLNode = AVLNode.getRight();
		return AVLNode;
	}

    private AVLNode<K> minimum(AVLNode<K> AVLNode) {
        while (AVLNode.getLeft() != null)
            AVLNode = AVLNode.getLeft();
        return AVLNode;
    }

    @Override
    public String preOrder(AVLNode<K> AVLNode) {
        String keys = "";
        if (AVLNode != null) {
            keys += AVLNode.getKey() + " ";
            keys += preOrder(AVLNode.getLeft());
            keys += preOrder(AVLNode.getRight());
            return keys;
        }
        return null;
    }

    @Override
    public String inOrder(AVLNode<K> AVLNode) {
        String keys = "";
		if (AVLNode != null) {
			keys += inOrder(AVLNode.getLeft());
			keys += AVLNode.getKey() + " ";
			keys += inOrder(AVLNode.getRight());
            return keys;
		}
        return null;
	}

    @Override
    public String postOrder(AVLNode<K> AVLNode) {
        String keys = "";
		if (AVLNode != null) {
			keys += postOrder(AVLNode.getLeft());
			keys += postOrder(AVLNode.getRight());
			keys += AVLNode.getKey() + " ";
            return keys;
		}
        return null;
	}
}