package dataStructures;

public class AVLTree<K extends Comparable<K>, V> { // class adapted from GeeksForGeeks https://www.geeksforgeeks.org/avl-tree-set-2-deletion/

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private AVLNode<K, V> root;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public AVLTree(AVLNode<K, V> root) {
        this.root = root;
    }


    public AVLNode<K, V> getRoot() {
        return root;
    }


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
        updateBalance(node);
    }

    private void updateBalance(AVLNode<K, V> node) {
		if (node.getBalanceFactor() < -1 || node.getBalanceFactor() > 1) {
			rebalance(node);
			return;
		}
		if (node.getParent() != null) {
            int balanceFactor = node.getParent().getBalanceFactor();
			if (node == node.getParent().getLeft())
				node.getParent().setBalanceFactor(balanceFactor - 1);
			if (node == node.getParent().getRight())
                node.getParent().setBalanceFactor(balanceFactor + 1);
			if (node.getParent().getBalanceFactor() != 0)
				updateBalance(node.getParent());
		}
	}

    private void rebalance(AVLNode<K, V> node) {
		if (node.getBalanceFactor() > 0) {
			if (node.getRight().getBalanceFactor() < 0) {
				rightRotate(node.getRight());
				leftRotate(node);
			} else
				leftRotate(node);
		} else if (node.getBalanceFactor() < 0) {
			if (node.getLeft().getBalanceFactor() > 0) {
				leftRotate(node.getLeft());
				rightRotate(node);
			} else
				rightRotate(node);
		}
	}

    private AVLNode<K, V> search(AVLNode<K, V> r, K key) {
		if (r == null || key.compareTo(r.getKey()) == 0)
			return r;
		if (key.compareTo(r.getKey()) < 0)
			return search(r.getLeft(), key);
		return search(r.getRight(), key);
	}


    /*public AVLNode<K, V> delete(AVLNode<K, V> current, K key) {
        if (current == null)
            return current;
        if (key.compareTo(current.getKey()) < 0)
            current.setLeft(delete(current.getLeft(), key));
        else if (key.compareTo(current.getKey()) > 0)
            current.setRight(delete(current.getRight(), key));
        else {
            if ((current.getLeft() == null) || (current.getRight() == null)) {
                AVLNode<K, V> temp = null;
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
                AVLNode<K, V> temp = minimum(current.getRight());
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
            current.setRight(rightRotate((AVLNode<K, V>)current.getRight()));
            return leftRotate(current);
        }
        return current;
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
        return (AVLNode<K, V>)node.getRight();
    }

    private AVLNode<K, V> rightRotate(AVLNode<K, V> node) {
        node.getLeft().setRight(node);
        node.setLeft(node.getLeft().getRight());
        node.setHeight(maxHeight(height(node.getLeft()), height(node.getRight())) + 1);
        node.getLeft().setHeight(maxHeight(height(node.getLeft().getLeft()), height(node.getLeft().getRight())) + 1);
        return (AVLNode<K, V>)node.getLeft();
    }
    **/

    public AVLNode<K, V> predecessor(AVLNode<K, V> node) {
		if (node.getLeft() != null) {
			return maximum((AVLNode<K, V>)node.getLeft());
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

    /*private int getBalance(AVLNode<K, V> node) {
        if (node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }*/

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

    public String postOrder(AVLNode<K, V> node) {
       String result=postOrder(node);
        return result;
	}
}