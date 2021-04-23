package dataStructures;

import dataStructures.RBNode.COLOR;

public class RBTree<K extends Comparable<K>, V> implements RBTreeInterface<K, V> { // class adapted from https://github.com/Bibeknam/algorithmtutorprograms/blob/11ef340f8c8e60839a9dff395dd52b8752c537a6/data-structures/red-black-trees/RedBlackTree.java#L298

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
    public void insert(RBNode<K, V> newNode) {
        if (root == null) {
            newNode.setColor(COLOR.BLACK);
            root = newNode;
        } else {
            RBNode<K, V> temp = newNode;
            newNode.setParent(temp);
            if (newNode.getKey().compareTo(temp.getKey()) < 0)
                temp.setLeft(newNode);
            else {
                temp.setRight(newNode);
            }
            fixDoubleRed(newNode);
        }
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
    public boolean delete(RBNode<K, V> v) {
        if (root == null) {
            return false;
        }
        privateDelete(v);
        return true;
    }

    private void privateDelete(RBNode<K, V> v) {
        RBNode<K, V> u = BSTReplace(v);
        boolean uvBlack = (u == null || u.getColor() == COLOR.BLACK) && (v.getColor() == COLOR.BLACK);
        RBNode<K, V> parent = v.getParent();
        if (u == null) {
            if (v.equals(root))
                root = null;
            else {
                if (uvBlack)
                    fixDoubleBlack(v);
                else {
                    if (v.sibling() != null)
                        v.sibling().setColor(COLOR.RED);
                }
                if (v.isOnLeft())
                    parent.setLeft(null);
                else
                    parent.setRight(null);
            }
            return;
        } else if (v.getLeft() == null || v.getRight() == null) {
            if (v.equals(root)) {
                v.setKey(u.getKey());
                v.setLeft(null);
                v.setRight(null);
            } else {
                if (v.isOnLeft())
                    parent.setLeft(u);
                else {
                    parent.setRight(u);
                }
                u.setParent(parent);
                if (uvBlack)
                    fixDoubleBlack(u);
                else
                    u.setColor(COLOR.BLACK);
            }
            return;
        }
        swapValues(u, v);
        privateDelete(u);
    }

    private void fixDoubleBlack(RBNode<K, V> x) {
        if (x.equals(root))
            return;
        RBNode<K, V> sibling = x.sibling();
        RBNode<K, V> parent = x.getParent();
        if (sibling == null)
            fixDoubleBlack(parent);
        else {
            if (sibling.getColor() == COLOR.RED) {
                parent.setColor(COLOR.RED);
                sibling.setColor(COLOR.BLACK);
                if (sibling.isOnLeft())
                    rightRotate(parent);
                else {
                    leftRotate(parent);
                }
                fixDoubleBlack(x);
            } else {
                if (sibling.hasRedChild()) {
                    if (sibling.getLeft() != null && sibling.getLeft().getColor() == COLOR.RED) {
                        if (sibling.isOnLeft()) {
                            sibling.getLeft().setColor(sibling.getColor());
                            sibling.setColor(parent.getColor());
                            rightRotate(parent);
                        } else {
                            sibling.getLeft().setColor(parent.getColor());
                            rightRotate(sibling);
                            leftRotate(parent);
                        }
                    } else {
                        if (sibling.isOnLeft()) {
                            sibling.getRight().setColor(parent.getColor());
                            leftRotate(sibling);
                            rightRotate(parent);
                        } else {
                            sibling.getRight().setColor(sibling.getColor());
                            sibling.setColor(parent.getColor());
                            leftRotate(parent);
                        }
                    }
                    parent.setColor(COLOR.BLACK);
                } else {
                    sibling.setColor(COLOR.RED);
                    if (parent.getColor() == COLOR.BLACK)
                        fixDoubleBlack(parent);
                    else
                        parent.setColor(COLOR.BLACK);
                }
            }
        }
    }

    private void fixDoubleRed(RBNode<K, V> x) {
        if (x.equals(root)) {
            x.setColor(COLOR.BLACK);
            return;
        }
        RBNode<K, V> parent = x.getParent();
        RBNode<K, V> grandparent = parent.getParent();
        RBNode<K, V> uncle = x.uncle();
        if (parent.getColor() != COLOR.BLACK) {
            if (uncle != null && uncle.getColor() == COLOR.RED) {
                parent.setColor(COLOR.BLACK);
                uncle.setColor(COLOR.BLACK);
                grandparent.setColor(COLOR.RED);
                fixDoubleRed(grandparent);
            } else {
                if (parent.isOnLeft()) {
                    if (x.isOnLeft())
                        swapColors(parent, grandparent);
                    else {
                        leftRotate(parent);
                        swapColors(x, grandparent);
                    }
                    rightRotate(grandparent);
                } else {
                    if (x.isOnLeft()) {
                        rightRotate(parent);
                        swapColors(x, grandparent);
                    } else {
                        swapColors(parent, grandparent);
                    }
                    leftRotate(grandparent);
                }
            }
        }
    }

    private void leftRotate(RBNode<K, V> node) {
        RBNode<K, V> nParent = node.getRight();
        if (node.equals(root)) {
            root = nParent;
        }
        node.moveDown(nParent);
        node.setRight(nParent.getLeft());
        if (nParent.getLeft() != null) {
            nParent.getLeft().setParent(node);
        }
        nParent.setLeft(node);
    }

    private void rightRotate(RBNode<K, V> node) {
        RBNode<K, V> nParent = node.getLeft();
        if (node.equals(root)) {
            root = nParent;
        }
        node.moveDown(nParent);
        node.setLeft(nParent.getRight());
        if (nParent.getRight() != null) {
            nParent.getRight().setParent(node);
        }
        nParent.setRight(node);
    }

    private void swapColors(RBNode<K, V> x1, RBNode<K, V> x2) {
        COLOR temp = x1.getColor();
        x1.setColor(x2.getColor());
        x2.setColor(temp);
    }

    private void swapValues(RBNode<K, V> u, RBNode<K, V> v) {
        K temp = u.getKey();
        u.setKey(v.getKey());
        v.setKey(temp);
    }

    private RBNode<K, V> BSTReplace(RBNode<K, V> x) {
        if (x.getLeft() != null && x.getRight() != null)
            return minimum(x.getRight());
        if (x.getLeft() == null && x.getRight() == null)
            return null;
        if (x.getLeft() != null)
            return x.getLeft();
        else
            return x.getRight();
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
        RBNode<K, V> temp = node;
        while (temp.getLeft() != null)
            temp = temp.getLeft();
        return temp;
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