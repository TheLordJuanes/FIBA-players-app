package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AVLTreeTest {

	private AVLTree<Double, ArrayList<Integer>, Integer> avlTree;

	public void setup1() {
		avlTree = new AVLTree<>();
	}

	public void setup2() {
		avlTree = new AVLTree<>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		AVLNode<Double, ArrayList<Integer>> node = new AVLNode<>(80.0, list);
		avlTree.insert(node, 0);
		list = new ArrayList<Integer>();
		list.add(1);
		node = new AVLNode<Double, ArrayList<Integer>>(88.8, list);
		avlTree.insert(node, 1);
		list = new ArrayList<Integer>();
		list.add(2);
		node = new AVLNode<Double, ArrayList<Integer>>(39.1, list);
		avlTree.insert(node, 2);
		list = new ArrayList<Integer>();
		list.add(3);
		node = new AVLNode<Double, ArrayList<Integer>>(45.0, list);
		avlTree.insert(node, 3);
		list = new ArrayList<Integer>();
		list.add(4);
		node = new AVLNode<>(35.5, list);
		avlTree.insert(node, 4);
		list = new ArrayList<Integer>();
		list.add(5);
		node = new AVLNode<>(83.7, list);
		avlTree.insert(node, 5);
		list = new ArrayList<Integer>();
		list.add(6);
		node = new AVLNode<>(90.4, list);
		avlTree.insert(node, 6);
	}

	@Test
	public void testInsert() {
		setup1();
		assertNull(avlTree.getRoot());
		assertEquals(0, avlTree.getSizeNodes());
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		AVLNode<Double, ArrayList<Integer>> node = new AVLNode<>(11.1, list);
		avlTree.insert(node, 0);
		assertNotNull(avlTree.getRoot());
		assertNotNull(avlTree.search(11.1));
		assertEquals(11.1, avlTree.search(11.1).getKey());
		assertNull(avlTree.getRoot().getParent());
		assertNull(avlTree.getRoot().getLeft());
		assertNull(avlTree.getRoot().getRight());
		assertEquals(1, avlTree.getRoot().getHeight());
		assertEquals(1, avlTree.getSizeNodes());
		assertEquals(1, avlTree.getSizeElements());
		setup2();
		assertNotNull(avlTree.getRoot());
		assertEquals(7, avlTree.getSizeNodes());
		assertEquals(7, avlTree.getSizeElements());
		list = new ArrayList<Integer>();
		list.add(4);
		node = new AVLNode<>(30.0, list);
		avlTree.insert(node, 4);
		AVLNode<Double, ArrayList<Integer>> objSearch = avlTree.search(30.0);
		assertNotNull(objSearch);
		assertEquals(8, avlTree.getSizeNodes());
		assertEquals(30.0, objSearch.getKey());
		assertNull(objSearch.getLeft());
		assertNull(objSearch.getRight());
		assertNotNull(objSearch.getParent());
		assertEquals(35.5, objSearch.getParent().getKey());
		assertEquals(30.0, objSearch.getParent().getLeft().getKey());
		assertEquals(80.0, avlTree.getRoot().getKey());
		setup2();
		list = new ArrayList<Integer>();
		list.add(4);
		node = new AVLNode<>(80.0, list);
		avlTree.insert(node, 4);
		assertEquals(2, avlTree.getRoot().getValue().size());
		assertEquals(7, avlTree.getSizeNodes());
		assertEquals(8, avlTree.getSizeElements());
	}

	@Test
	public void testSearch() {
		setup1();
		assertNull(avlTree.getRoot());
		assertNull(avlTree.search(11.1));
		setup2();
		assertNotNull(avlTree.getRoot());
		assertNotNull(avlTree.search(45.0));
	}

	@Test
	public void testDelete() {
		setup1();
		assertNull(avlTree.getRoot());
		assertEquals(0, avlTree.getSizeNodes());
		assertEquals(0, avlTree.getSizeElements());
		assertFalse(avlTree.delete(50.0, 0));
		assertEquals(0, avlTree.getSizeNodes());
		assertEquals(0, avlTree.getSizeElements());
		setup2();
		assertNotNull(avlTree.getRoot());
		assertEquals(80.0, avlTree.getRoot().getKey());
		assertEquals(7, avlTree.getSizeNodes());
		assertEquals(7, avlTree.getSizeElements());
		assertNotNull(avlTree.search(35.5));
		assertTrue(avlTree.delete(35.5, 0));
		assertNull(avlTree.search(35.5));
		assertEquals(6, avlTree.getSizeNodes());
		assertEquals(6, avlTree.getSizeElements());
		setup2();
		assertEquals(7, avlTree.getSizeNodes());
		assertEquals(7, avlTree.getSizeElements());
		assertNull(avlTree.search(11.4));
		assertFalse(avlTree.delete(11.4, 0));
		assertTrue(avlTree.delete(80.0, 0));
		assertNull(avlTree.search(80.0));
		assertEquals(6, avlTree.getSizeNodes());
		assertEquals(6, avlTree.getSizeElements());
		setup2();
		assertEquals(7, avlTree.getSizeNodes());
		assertEquals(7, avlTree.getSizeElements());
		assertNotNull(avlTree.search(39.1));
		assertTrue(avlTree.delete(39.1, 0));
		assertNull(avlTree.search(39.1));
		assertEquals(6, avlTree.getSizeNodes());
		assertEquals(6, avlTree.getSizeElements());
		setup2();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		AVLNode<Double, ArrayList<Integer>> node = new AVLNode<>(80.0, list);
		avlTree.insert(node, 0);
		assertEquals(7, avlTree.getSizeNodes());
		assertEquals(8, avlTree.getSizeElements());
		assertNotNull(avlTree.search(80.0));
		assertTrue(avlTree.delete(80.0, 0));
		assertNotNull(avlTree.search(80.0));
		assertEquals(1, avlTree.search(80.0).getValue().size());
		assertEquals(7, avlTree.getSizeNodes());
		assertEquals(7, avlTree.getSizeElements());
	}

	@Test
	public void testPredecessor() {
		setup2();
		assertEquals(35.5, avlTree.predecessor(avlTree.search(39.1)).getKey());
		assertEquals(39.1, avlTree.predecessor(avlTree.search(45.0)).getKey());
		assertEquals(45.0, avlTree.predecessor(avlTree.search(80.0)).getKey());
		assertEquals(83.7, avlTree.predecessor(avlTree.search(88.8)).getKey());
	}

	@Test
	public void testSucessor() {
		setup2();
		assertEquals(45.0, avlTree.successor(avlTree.search(39.1)).getKey());
		assertEquals(80.0, avlTree.successor(avlTree.search(45.0)).getKey());
		assertEquals(83.7, avlTree.successor(avlTree.search(80.0)).getKey());
		assertEquals(90.4, avlTree.successor(avlTree.search(88.8)).getKey());
	}

	@Test
	public void testPreOrder() {
		setup2();
		assertEquals("80.0 39.1 35.5 45.0 88.8 83.7 90.4 ", avlTree.preOrder(avlTree.getRoot()));
	}

	@Test
	public void testInOrder() {
		setup2();
		assertEquals("35.5 39.1 45.0 80.0 83.7 88.8 90.4 ", avlTree.inOrder(avlTree.getRoot()));
	}

	@Test
	public void testPostOrder() {
		setup2();
		assertEquals("35.5 45.0 39.1 83.7 90.4 88.8 80.0 ", avlTree.postOrder(avlTree.getRoot()));
	}
}