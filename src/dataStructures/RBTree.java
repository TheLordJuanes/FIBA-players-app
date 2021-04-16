package dataStructures;

public class RBTree<K extends Comparable<K>> implements RBTreeInterface<K> { // class adapted from  https://github.com/Bibeknam/algorithmtutorprograms/blob/11ef340f8c8e60839a9dff395dd52b8752c537a6/data-structures/red-black-trees/RedBlackTree.java#L298

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private RBNode<K> root;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public RBTree() {
    }

    private void preOrderHelper(RBNode<K> node) {
        if (node != null) {
            System.out.print(node.getKey() + " ");
            preOrderHelper(node.getLeft());
            preOrderHelper(node.getRight());
        }
    }

    private void inOrderHelper(RBNode<K> node) {
        if (node != null) {
            inOrderHelper(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrderHelper(node.getRight());
        }
    }

    private void postOrderHelper(RBNode<K> node) {
        if (node != null) {
            postOrderHelper(node.getLeft());
            postOrderHelper(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

    private RBNode<K> searchTreeHelper(RBNode<K> node, K key) {
        if (node == null || key.compareTo(node.getKey()) == 0)
            return node;
        if (key.compareTo(node.getKey()) < 0)
            return searchTreeHelper(node.getLeft(), key);
        return searchTreeHelper(node.getRight(), key);
    }

    private void fixDelete(RBNode<K> x) {
        RBNode<K> s;
        while (x != root && x.getColor() == 0) {
            if (x == x.getParent().getLeft()) {
                s = x.getParent().getRight();
                if (s.getColor() == 1) {
                    s.setColor(0);
                    x.getParent().setColor(1);
                    leftRotate(x.getParent());
                    s = x.getParent().getRight();
                }
                if (s.getLeft().getColor() == 0 && s.getRight().getColor() == 0) {
                    s.setColor(1);
                    x = x.getParent();
                } else {
                    if (s.getRight().getColor() == 0) {
                        s.getLeft().setColor(0);
                        s.setColor(1);
                        rightRotate(s);
                        s = x.getParent().getRight();
                    }
                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(0);
                    s.getRight().setColor(0);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                s = x.getParent().getLeft();
                if (s.getColor() == 1) {
                    s.setColor(0);
                    x.getParent().setColor(1);
                    rightRotate(x.getParent());
                    s = x.getParent().getLeft();
                }
                if (s.getRight().getColor() == 0 && s.getRight().getColor() == 0) {
                    s.setColor(1);
                    x = x.getParent();
                } else {
                    if (s.getLeft().getColor() == 0) {
                        s.getRight().setColor(0);
                        s.setColor(1);
                        leftRotate(s);
                        s = x.getParent().getLeft();
                    }
                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(0);
                    s.getLeft().setColor(0);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(0);
    }

    private void rbTransplant(RBNode<K> u, RBNode<K> v) {
        if (u.getParent() == null)
            root = v;
        else if (u == u.getParent().getLeft())
            u.getParent().setLeft(v);
        else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    private void deleteNodeHelper(RBNode<K> node, K key) {
        RBNode<K> z = null;
        RBNode<K> x, y;
        while (node != null) {
            if (node.getKey() == key)
                z = node;
            if (node.getKey().compareTo(key) <= 0)
                node = node.getRight();
            else
                node = node.getLeft();
        }
        if (z == null) {
            System.out.println("Couldn't find key in the tree");
            return;
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

    private void fixInsert(RBNode<K> k) {
        RBNode<K> u;
        while (k.getParent().getColor() == 1) {
            if (k.getParent() == k.getParent().getParent().getRight()) {
                u = k.getParent().getParent().getLeft();
                if (u.getColor() == 1) {
                    u.setColor(0);
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getLeft()) {
                        k = k.getParent();
                        rightRotate(k);
                    }
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    leftRotate(k.getParent().getParent());
                }
            } else {
                u = k.getParent().getParent().getRight();
                if (u.getColor() == 1) {
                    u.setColor(0);
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getRight()) {
                        k = k.getParent();
                        leftRotate(k);
                    }
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    rightRotate(k.getParent().getParent());
                }
            }
            if (k == root)
                break;
        }
        root.setColor(0);
    }

    public void preorder() {
        preOrderHelper(this.root);
    }

    public void inorder() {
        inOrderHelper(this.root);
    }

    public void postorder() {
        postOrderHelper(this.root);
    }

    public RBNode<K> searchTree(K k) {
        return searchTreeHelper(this.root, k);
    }

    public RBNode<K> minimum(RBNode<K> node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    public RBNode<K> maximum(RBNode<K> node) {
        while (node.getRight() != null)
            node = node.getRight();
        return node;
    }

    public RBNode<K> successor(RBNode<K> x) {
        if (x.getRight() != null) {
            return minimum(x.getRight());
        }
        RBNode<K> y = x.getParent();
        while (y != null && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public RBNode<K> predecessor(RBNode<K> x) {
        if (x.getLeft() != null) {
            return maximum(x.getLeft());
        }
        RBNode<K> y = x.getParent();
        while (y != null && x == y.getLeft()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public void leftRotate(RBNode<K> x) {
        RBNode<K> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null)
            this.root = y;
        else if (x == x.getParent().getLeft())
            x.getParent().setLeft(y);
        else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    public void rightRotate(RBNode<K> x) {
        RBNode<K> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null)
            this.root = y;
        else if (x == x.getParent().getRight())
            x.getParent().setRight(y);
        else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    public void insert(K key) {
        RBNode<K> node = new RBNode<K>(key);
        node.setParent(null);
        node.setKey(key);
        node.setLeft(null);
        node.setRight(null);
        node.setColor(1);
        RBNode<K> y = null;
        RBNode<K> x = this.root;
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

    public RBNode<K> getRoot() {
        return this.root;
    }

    public void deleteNode(K key) {
		deleteNodeHelper(this.root, key);
	}
}