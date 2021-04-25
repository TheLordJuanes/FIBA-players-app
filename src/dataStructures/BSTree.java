package dataStructures;

import java.util.ArrayList;

public class BSTree<K extends Comparable<K>, V> implements BSTreeInterface<K, V> { // class adapted from https://github.com/Bibeknam/algorithmtutorprograms/blob/11ef340f8c8e60839a9dff395dd52b8752c537a6/data-structures/red-black-trees/RedBlackTree.java#L298

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
    public BSTNode<K, V> search(K key) {
        return privateSearch(root, key);
    }

    private BSTNode<K, V> privateSearch(BSTNode<K, V> r, K key) {
        if (r == null || key.compareTo(r.getKey()) == 0)
            return r;
        if (key.compareTo(r.getKey()) < 0)
            return privateSearch(r.getLeft(), key);
        return privateSearch(r.getRight(), key);
    }

    public ArrayList<BSTNode<K, V>> searchMajor(K key){
        ArrayList<BSTNode<K, V>> nodes = new ArrayList<>();
        pSearchMajor(root, key, nodes);
        return nodes;
    }

    private void pSearchMajor(BSTNode<K, V> node, K key, ArrayList<BSTNode<K, V>> nodes){
        if(node!=null){
            if(node.getKey().compareTo(key)>0){
                nodes.add(node);
                addNode(node.getRight(), nodes);
                pSearchMajor(node.getLeft(), key, nodes);
            }else {
                pSearchMajor(node.getRight(), key, nodes);
            }
        }
    }

    public ArrayList<BSTNode<K, V>> searchMajorEqual(K key){
        ArrayList<BSTNode<K, V>> nodes = new ArrayList<>();
        pSearchMajorEqual(root, key, nodes);
        return nodes;
    }

    private void pSearchMajorEqual(BSTNode<K, V> node, K key, ArrayList<BSTNode<K, V>> nodes){
        if(node!=null){
            if((node.getKey().compareTo(key)>0) || (node.getKey().compareTo(key)==0)){
                nodes.add(node);
                addNode(node.getRight(), nodes);
                pSearchMajorEqual(node.getLeft(), key, nodes);
            }else {
                pSearchMajorEqual(node.getRight(), key, nodes);
            }
        }
    }

    public ArrayList<BSTNode<K, V>> searchMinor(K key){
        ArrayList<BSTNode<K, V>> nodes = new ArrayList<>();
        pSearchMinor(root, key, nodes);
        return nodes;
    }

    private void pSearchMinor(BSTNode<K, V> node, K key, ArrayList<BSTNode<K, V>> nodes){
        if(node!=null){
            if(node.getKey().compareTo(key)<0){
                nodes.add(node);
                addNode(node.getLeft(), nodes);
                pSearchMinor(node.getRight(), key, nodes);
            }else {
                pSearchMinor(node.getLeft(), key, nodes);
            }
        }
    }

    public ArrayList<BSTNode<K, V>> searchMinorEqual(K key){
        ArrayList<BSTNode<K, V>> nodes = new ArrayList<>();
        pSearchMinorEqual(root, key, nodes);
        return nodes;
    }

    private void pSearchMinorEqual(BSTNode<K, V> node, K key, ArrayList<BSTNode<K, V>> nodes){
        if(node!=null){
            if((node.getKey().compareTo(key)<0) || (node.getKey().compareTo(key)==0)){
                nodes.add(node);
                addNode(node.getLeft(), nodes);
                pSearchMinorEqual(node.getRight(), key, nodes);
            }else {
                pSearchMinorEqual(node.getLeft(), key, nodes);
            }
        }
    }

    private void addNode(BSTNode<K, V> node, ArrayList<BSTNode<K, V>> nodes){
        if(node != null){
			addNode(node.getLeft(), nodes);
			nodes.add(node);
			addNode(node.getRight(), nodes);
		}
    }

    @Override
    public boolean delete(K key) {
		BSTNode<K,V> toErase = privateSearch(root, key);
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

    public BSTNode<K, V> getRoot() {
        return root;
    }
}