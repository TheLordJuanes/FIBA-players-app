package dataStructures;

import exceptions.RBTreeException;

public class RBTree<K extends Comparable<K>, V> implements RBTreeInterface<K, V> { // class adapted from  https://github.com/Bibeknam/algorithmtutorprograms/blob/11ef340f8c8e60839a9dff395dd52b8752c537a6/data-structures/red-black-trees/RedBlackTree.java#L298

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private RBNode<K, V> root;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public RBTree() {
    }

    public RBNode<K, V> getRoot() {
        return root;
    }

    @Override
    public void insert(RBNode<K, V> node) {
        node.setColor(1);
        RBNode<K, V> y = null;
        RBNode<K, V> x = root;
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
        if (node.getParent() == null) {
            node.setColor(0);
            return;
        }
        if (node.getParent().getParent() == null) {
            return;
        }
        fixInsert(node);
    }

    private void fixInsert(RBNode<K, V> node) {
        RBNode<K, V> u;
        while (node.getParent().getColor() == 1) {
            if (node.getParent().equals(node.getParent().getParent().getRight())) {
                u = node.getParent().getParent().getLeft();
                if (u.getColor() == 1) {
                    u.setColor(0);
                    node.getParent().setColor(0);
                    node.getParent().getParent().setColor(1);
                    node = node.getParent().getParent();
                } else {
                    if (node.equals(node.getParent().getLeft())) {
                        node = node.getParent();
                        rightRotate(node);
                    }
                    node.getParent().setColor(0);
                    node.getParent().getParent().setColor(1);
                    leftRotate(node.getParent().getParent());
                }
            } else {
                u = node.getParent().getParent().getRight();
                if (u.getColor() == 1) {
                    u.setColor(0);
                    node.getParent().setColor(0);
                    node.getParent().getParent().setColor(1);
                    node = node.getParent().getParent();
                } else {
                    if (node.equals(node.getParent().getRight())) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().setColor(0);
                    node.getParent().getParent().setColor(1);
                    rightRotate(node.getParent().getParent());
                }
            }
            if (node.equals(root))
                break;
        }
        root.setColor(0);
    }

    @Override
    public RBNode<K, V> search(K key) {
        if (root != null)
            return privateSearch(root, key);
        return null;
    }

    private RBNode<K, V> privateSearch(RBNode<K, V> current, K key) {
        if (current == null || key.compareTo(current.getKey()) == 0)
			return current;
		else if (key.compareTo(current.getKey()) < 0)
			return privateSearch(current.getLeft(), key);
		return privateSearch(current.getRight(), key);
    }

    @Override
    public void delete(RBNode<K, V> node, K key) throws RBTreeException {
        RBNode<K, V> z = null;
        RBNode<K, V> x, y;
        while (node != null) {
            if (node.getKey().compareTo(key) == 0)
                z = node;
            if (node.getKey().compareTo(key) <= 0)
                node = node.getRight();
            else
                node = node.getLeft();
        }
        if (z == null) {
            throw new RBTreeException("Couldn't find key in the tree");
        }
        y = z;
        int yOriginalColor = y.getColor();
        if (z.getLeft() == null) {
            x = z.getRight();
            rbTransplant(z, z.getRight());
        } else if (z.getRight() == null) {
            x = z.getLeft();
            rbTransplant(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z)
                x.setParent(y);
            else {
                rbTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            rbTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (yOriginalColor == 0)
            fixDelete(x);
    }

    private void rbTransplant(RBNode<K, V> u, RBNode<K, V> v) {
        if (u.getParent() == null)
            root = v;
        else if (u.equals(u.getParent().getLeft()))
            u.getParent().setLeft(v);
        else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    private void fixDelete(RBNode<K, V> node) {
        RBNode<K, V> s;
        while (!node.equals(root) && node.getColor() == 0) {
            if (node.equals(node.getParent().getLeft())) {
                s = node.getParent().getRight();
                if (s.getColor() == 1) {
                    s.setColor(0);
                    node.getParent().setColor(1);
                    leftRotate(node.getParent());
                    s = node.getParent().getRight();
                }
                if (s.getLeft().getColor() == 0 && s.getRight().getColor() == 0) {
                    s.setColor(1);
                    node = node.getParent();
                } else {
                    if (s.getRight().getColor() == 0) {
                        s.getLeft().setColor(0);
                        s.setColor(1);
                        rightRotate(s);
                        s = node.getParent().getRight();
                    }
                    s.setColor(node.getParent().getColor());
                    node.getParent().setColor(0);
                    s.getRight().setColor(0);
                    leftRotate(node.getParent());
                    node = root;
                }
            } else {
                s = node.getParent().getLeft();
                if (s.getColor() == 1) {
                    s.setColor(0);
                    node.getParent().setColor(1);
                    rightRotate(node.getParent());
                    s = node.getParent().getLeft();
                }
                if (s.getRight().getColor() == 0 && s.getRight().getColor() == 0) {
                    s.setColor(1);
                    node = node.getParent();
                } else {
                    if (s.getLeft().getColor() == 0) {
                        s.getRight().setColor(0);
                        s.setColor(1);
                        leftRotate(s);
                        s = node.getParent().getLeft();
                    }
                    s.setColor(node.getParent().getColor());
                    node.getParent().setColor(0);
                    s.getLeft().setColor(0);
                    rightRotate(node.getParent());
                    node = root;
                }
            }
        }
        node.setColor(0);
    }

    public void leftRotate(RBNode<K, V> node) {
        node.setRight(node.getRight().getLeft());
        if (node.getRight().getLeft() != null) {
            node.getRight().getLeft().setParent(node);
        }
        node.getRight().setParent(node.getParent());
        if (node.getParent() == null)
            root = node.getRight();
        else if (node.equals(node.getParent().getLeft()))
            node.getParent().setLeft(node.getRight());
        else {
            node.getParent().setRight(node.getRight());
        }
        node.getRight().setLeft(node);
        node.setParent(node.getRight());
    }

    public void rightRotate(RBNode<K, V> node) {
        node.setLeft(node.getLeft().getRight());
        if (node.getLeft().getRight() != null) {
            node.getLeft().getRight().setParent(node);
        }
        node.getLeft().setParent(node.getParent());
        if (node.getParent() == null)
            root = node.getLeft();
        else if (node.equals(node.getParent().getRight()))
            node.getParent().setRight(node.getLeft());
        else {
            node.getParent().setLeft(node.getLeft());
        }
        node.getLeft().setRight(node);
        node.setParent(node.getLeft());
    }

    public RBNode<K, V> predecessor(RBNode<K, V> x) {
        if (x.getLeft() != null) {
            return maximum(x.getLeft());
        }
        RBNode<K, V> y = x.getParent();
        while (y != null && x == y.getLeft()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public RBNode<K, V> successor(RBNode<K, V> x) {
        if (x.getRight() != null) {
            return minimum(x.getRight());
        }
        RBNode<K, V> y = x.getParent();
        while (y != null && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public RBNode<K, V> minimum(RBNode<K, V> node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    public RBNode<K, V> maximum(RBNode<K, V> node) {
        while (node.getRight() != null)
            node = node.getRight();
        return node;
    }

    @Override
    public String preOrder(RBNode<K, V> current) {
        String keys = "";
        if (current != null) {
            keys += current.getKey() + " ";
            keys += preOrder(current.getLeft());
            keys += preOrder(current.getRight());
            return keys;
        }
        return null;
    }

    @Override
    public String inOrder(RBNode<K, V> current) {
        String keys = "";
		if (current != null) {
			keys += inOrder(current.getLeft());
			keys += current.getKey() + " ";
			keys += inOrder(current.getRight());
            return keys;
		}
        return null;
	}

    @Override
    public String postOrder(RBNode<K, V> current) {
        String keys = "";
		if (current != null) {
			keys += postOrder(current.getLeft());
			keys += postOrder(current.getRight());
			keys += current.getKey() + " ";
            return keys;
		}
        return null;
	}
}