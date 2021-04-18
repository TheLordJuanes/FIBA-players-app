package dataStructures;

public class BSTree<K extends Comparable<K>, V> implements BSTreeInterface<K, V> {

    private BSTNode<K, V> root;

    public BSTree() {
    }

    @Override
    public void insert(BSTNode<K, V> node) {
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
    public BSTNode<K, V> search(BSTNode<K, V> r, K key) {
        if (r == null || key.compareTo(r.getKey()) == 0)
			return r;
		if (key.compareTo(r.getKey()) < 0)
			return search(r.getLeft(), key);
		return search(r.getRight(), key);
    }

    @Override
    public boolean delete(K key) {
		BSTNode<K,V> toErase = search(root, key);
        if (toErase != null) {
            privateDelete(toErase);
            return true;
        }
        return false;
    }

    private void privateDelete(BSTNode<K, V> nodeToErase) {
        if (nodeToErase.getLeft() == null && nodeToErase.getRight() == null) {
            if (nodeToErase.equals(root))
                root = null;
            else if (nodeToErase.getParent().getLeft().equals(nodeToErase))
                nodeToErase.getParent().setLeft(null);
            else if (nodeToErase.getParent().getRight().equals(nodeToErase)) {
                nodeToErase.getParent().setRight(null);
            }
            nodeToErase.setParent(null);
        } else if (nodeToErase.getLeft() == null || nodeToErase.getRight() == null) {
            BSTNode<K, V> child = nodeToErase.getLeft() != null ? nodeToErase.getLeft() : nodeToErase.getRight();
            if (nodeToErase.equals(root)) {
                root = child;
                root.setParent(null);
            } else {
                if (nodeToErase.getParent().getLeft().equals(nodeToErase))
                    nodeToErase.getParent().setLeft(child);
                else {
                    nodeToErase.getParent().setRight(child);
                }
                child.setParent(nodeToErase.getParent());
                nodeToErase.setRight(null);
                nodeToErase.setLeft(null);
            }
        } else {
            BSTNode<K, V> nodeMostToLeft = successor(nodeToErase);
            if (nodeMostToLeft != null ) {
                privateDelete(nodeMostToLeft);
                nodeToErase.getParent().setLeft(nodeMostToLeft);
            }
        }
    }

    private BSTNode<K, V> minimum(BSTNode<K,V> node) {
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
        if (node != null) {
            keys += node.getValue().toString() + " ";
            keys += preOrder(node.getLeft());
            keys += preOrder(node.getRight());
            return keys;
        }
        return null;
    }

    @Override
    public String inOrder(BSTNode<K, V> node) {
        String keys = "";
		if (node != null) {
			keys += inOrder(node.getLeft());
			keys += node.getValue().toString() + " ";
			keys += inOrder(node.getRight());
            return keys;
		}
        return null;
	}

    @Override
    public String postOrder(BSTNode<K, V> node) {
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