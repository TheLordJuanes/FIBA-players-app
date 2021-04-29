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

    /** Name: AVLTree <br>
	 * <br> Constructor method of a generic AVL tree. <br>
	*/
    public AVLTree() {
    }

    public AVLNode<K, V> getRoot() {
        return root;
    }

    /** Name: insert <br>
	 * <br> Method used to insert a generic AVL node in a generic AVL tree. <br>
     * <br> pre: Generic AVL tree already initialized. Generic AVLNode<K, V> object already created. <br>
     * <br> post: Generic AVL node inserted in the generic AVL tree. <br>
     * @param node - node to insert - node = AVLNode<K, V>
     * @param index - index of the list of keys from a node - index = E, index != null
	*/
    @Override
    public void insert(AVLNode<K, V> node, E index) {
        AVLNode<K, V> node1 = search(node.getKey());
        if (node1 == null)
            privateInsert(node);
        else
            node1.getValue().add(index);
    }

    /** Name: privateInsert <br>
	 * <br> Private method used to insert a generic AVL node in a generic AVL tree. <br>
     * <br> pre: Generic AVLNode<K, V> object already searched and doesn't exist yet in the generic AVL tree. <br>
     * <br> post: Generic AVL node inserted in the generic AVL tree. <br>
     * @param node - node to insert - node = AVLNode<K, V>
	*/
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

    /** Name: search <br>
	 * <br> Method used to search a generic AVL node in a generic AVL tree. <br>
     * <br> pre: Generic AVL tree already initialized. <br>
     * <br> post: Generic AVL node searched in the generic AVL tree. <br>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @return An AVLNode<K, V> with null if it wasn't found in the generic AVL tree, or with different from null otherwise.
	*/
    @Override
    public AVLNode<K, V> search(K key) {
        return privateSearch(root, key);
    }

    /** Name: privateSearch <br>
	 * <br> Private method used to search a generic AVL node in a generic AVL tree. <br>
     * <br> pre: Generic AVL tree already initialized. <br>
     * <br> post: Searching process of a generic AVL node in the generic AVL tree determined. <br>
     * @param r - root/current node - r = AVLNode<K, V>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @return An AVLNode<K, V> with null if it wasn't found in the generic AVL tree, or with different from null otherwise.
	*/
    private AVLNode<K, V> privateSearch(AVLNode<K, V> r, K key) {
        if (r == null || key.compareTo(r.getKey()) == 0)
            return r;
        if (key.compareTo(r.getKey()) < 0)
            return privateSearch(r.getLeft(), key);
        return privateSearch(r.getRight(), key);
    }

    /** Name: searchMajor <br>
	 * <br> Method used to search the generic AVL nodes in a generic AVL tree, that have a key greater than a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. <br>
     * <br> post: Generic AVL nodes that have a key greater than a specified number, searched in the generic AVL tree. <br>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @return A list with the generic AVL nodes found, or an empty list otherwise.
	*/
    public ArrayList<AVLNode<K, V>> searchMajor(K key) {
        ArrayList<AVLNode<K, V>> nodes = new ArrayList<>();
        pSearchMajor(root, key, nodes);
        return nodes;
    }

    /** Name: pSearchMajor <br>
	 * <br> Private method used to search the generic AVL nodes in a generic AVL tree, that have a key greater than a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. List of generic AVL nodes that have a key greater than a specified number, initialized. <br>
     * <br> post: Searching process of generic AVL nodes that have a key greater than a specified number in the generic AVL tree, determined. <br>
     * @param node - root/current node - node = AVLNode<K, V>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @param nodes - List of generic AVL nodes that have a key greater than a specified number - nodes = ArrayList<AVLNode<K, V>>, nodes != null
	*/
    private void pSearchMajor(AVLNode<K, V> node, K key, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            if (node.getKey().compareTo(key) > 0) {
                nodes.add(node);
                addNode(node.getRight(), nodes);
                pSearchMajor(node.getLeft(), key, nodes);
            } else
                pSearchMajor(node.getRight(), key, nodes);
        }
    }

    /** Name: searchMajorEqual <br>
	 * <br> Method used to search the generic AVL nodes in a generic AVL tree, that have a key greater or equal to a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. <br>
     * <br> post: Generic AVL nodes that have a key greater or equal to a specified number, searched in the generic AVL tree. <br>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @return A list with the generic AVL nodes found, or an empty list otherwise.
	*/
    public ArrayList<AVLNode<K, V>> searchMajorEqual(K key) {
        ArrayList<AVLNode<K, V>> nodes = new ArrayList<>();
        pSearchMajorEqual(root, key, nodes);
        return nodes;
    }

    /** Name: pSearchMajorEqual <br>
	 * <br> Private method used to search the generic AVL nodes in a generic AVL tree, that have a key greater or equal to a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. List of generic AVL nodes that have a key greater or equal to a specified number, initialized.<br>
     * <br> post: Generic AVL nodes that have a key greater or equal to a specified number, searched in the generic AVL tree. <br>
     * @param node - root/current node - node = AVLNode<K, V>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @param nodes - List of generic AVL nodes that have a key greater than a specified number - nodes = ArrayList<AVLNode<K, V>>, nodes != null
	*/
    private void pSearchMajorEqual(AVLNode<K, V> node, K key, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            if ((node.getKey().compareTo(key) > 0) || (node.getKey().compareTo(key) == 0)) {
                nodes.add(node);
                addNode(node.getRight(), nodes);
                pSearchMajorEqual(node.getLeft(), key, nodes);
            } else
                pSearchMajorEqual(node.getRight(), key, nodes);
        }
    }

    /** Name: searchMinor <br>
	 * <br> Method used to search the generic AVL nodes in a generic AVL tree, that have a key less than a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. <br>
     * <br> post: Generic AVL nodes that have a key less than a specified number, searched in the generic AVL tree. <br>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @return A list with the generic AVL nodes found, or an empty list otherwise.
	*/
    public ArrayList<AVLNode<K, V>> searchMinor(K key) {
        ArrayList<AVLNode<K, V>> nodes = new ArrayList<>();
        pSearchMinor(root, key, nodes);
        return nodes;
    }

    /** Name: pSearchMinor <br>
	 * <br> Private method used to search the generic AVL nodes in a generic AVL tree, that have a key less than a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. List of generic AVL nodes that have a key less than a specified number, initialized.<br>
     * <br> post: Generic AVL nodes that have a key less than a specified number, searched in the generic AVL tree. <br>
     * @param node - root/current node - node = AVLNode<K, V>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @param nodes - List of generic AVL nodes that have a key greater than a specified number - nodes = ArrayList<AVLNode<K, V>>, nodes != null
	*/
    private void pSearchMinor(AVLNode<K, V> node, K key, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            if (node.getKey().compareTo(key) < 0) {
                nodes.add(node);
                addNode(node.getLeft(), nodes);
                pSearchMinor(node.getRight(), key, nodes);
            } else
                pSearchMinor(node.getLeft(), key, nodes);
        }
    }

    /** Name: searchMinorEqual <br>
	 * <br> Method used to search the generic AVL nodes in a generic AVL tree, that have a key less than or equal to a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. <br>
     * <br> post: Generic AVL nodes that have a key less than or equal to a specified number, searched in the generic AVL tree. <br>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @return A list with the generic AVL nodes found, or an empty list otherwise.
	*/
    public ArrayList<AVLNode<K, V>> searchMinorEqual(K key) {
        ArrayList<AVLNode<K, V>> nodes = new ArrayList<>();
        pSearchMinorEqual(root, key, nodes);
        return nodes;
    }

    /** Name: pSearchMinorEqual <br>
	 * <br> Private method used to search the generic AVL nodes in a generic AVL tree, that have a key less than or equal to a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. List of generic AVL nodes that have a key less than or equal to a specified number, initialized.<br>
     * <br> post: Generic AVL nodes that have a key less than or equal to a specified number, searched in the generic AVL tree. <br>
     * @param node - root/current node - node = AVLNode<K, V>
     * @param key - AVL node key - key = K, k != null, k != ""
     * @param nodes - List of generic AVL nodes that have a key greater than a specified number - nodes = ArrayList<AVLNode<K, V>>, nodes != null
	*/
    private void pSearchMinorEqual(AVLNode<K, V> node, K key, ArrayList<AVLNode<K, V>> nodes) {
        if (node != null) {
            if ((node.getKey().compareTo(key) < 0) || (node.getKey().compareTo(key) == 0)) {
                nodes.add(node);
                addNode(node.getLeft(), nodes);
                pSearchMinorEqual(node.getRight(), key, nodes);
            } else
                pSearchMinorEqual(node.getLeft(), key, nodes);
        }
    }

    /** Name: addNode <br>
	 * <br> Private method used to add a generic AVL node in a list of generic AVL nodes that have a comparable key to a specified number. <br>
     * <br> pre: Generic AVL tree already initialized. List of generic AVL nodes that have a key less than or equal to a specified number, initialized.<br>
     * <br> post: Generic AVL nodes that have a key less than or equal to a specified number, searched in the generic AVL tree. <br>
     * @param node - current node - node = AVLNode<K, V>
     * @param nodes - List of generic AVL nodes that have a key greater than a specified number - nodes = ArrayList<AVLNode<K, V>>, nodes != null
	*/
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
            if (positions.size() > 1) {
                for (int i = 0; i < positions.size(); i++) {
                    if (positions.get(i).compareTo(expected) == 0)
                        positions.remove(i);
                }
            } else
                privateDelete(toErase);
            return true;
        }
        return false;
    }

    private void privateDelete(AVLNode<K, V> current) {
        if (current == root){
            AVLNode<K, V> nodeMostToLeft = successor(current);
            if (nodeMostToLeft != null) {
                privateDelete(nodeMostToLeft);
                nodeMostToLeft.setLeft(current.getLeft());
                nodeMostToLeft.setRight(current.getRight());
                nodeMostToLeft.setParent(null);
                root = nodeMostToLeft;
            } else {
                root = current.getRight();
                if (root != null)
                    current.setParent(null);
            }
        }
        else if (current.getLeft() == null && current.getRight() == null) {
            if (current == root)
                root = null;
            else if (current.getParent().getLeft() == current)
                current.getParent().setLeft(null);
            else if (current.getParent().getRight() == current) {
                current.getParent().setRight(null);
            }
            current.setParent(null);
        } else if (current.getLeft() == null || current.getRight() == null) {
            AVLNode<K, V> child = current.getLeft() != null ? current.getLeft() : current.getRight();
            if (current==root) {
                root = child;
                root.setParent(null);
            } else {
                if (current.getParent().getLeft()==current)
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
        while (y != null && node == y.getLeft()) {
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
        while (y != null && node == y.getRight()) {
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