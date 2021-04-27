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
		node = new AVLNode<>(35.5, list);
		avlTree.insert(node, 3);
	}

	@Test
	public void testInsert() {
		setup1();
		assertNull(avlTree.getRoot());
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
		setup2();
		assertNotNull(avlTree.getRoot());
		list = new ArrayList<Integer>();
		list.add(4);
		node = new AVLNode<>(30.0, list);
		avlTree.insert(node, 4);
		AVLNode<Double, ArrayList<Integer>> objSearch = avlTree.search(30.0);
		assertNotNull(objSearch);
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
	}

	@Test
	public void testSearch() {
		setup1();
		assertNull(avlTree.getRoot());
		assertNull(avlTree.search(11.1));
		setup2();
		assertNotNull(avlTree.getRoot());
		assertNotNull(avlTree.search(88.8));
	}

	@Test
	public void testDelete() {
		setup1();
		assertNull(avlTree.getRoot());
		assertFalse(avlTree.delete(50.0, 0));
		setup2();
		assertNotNull(avlTree.getRoot());
		assertEquals(80.0, avlTree.getRoot().getKey());
		assertNotNull(avlTree.search(35.5));
		assertTrue(avlTree.delete(35.5, 0));
		assertNull(avlTree.search(35.5));
		setup2();
		assertNull(avlTree.search(11.4));
		assertFalse(avlTree.delete(11.4, 0));
	}

	@Test
	public void testPreOrder() {
		setup2();
        assertEquals("80.0 39.1 35.5 88.8 ", avlTree.preOrder(avlTree.getRoot()));
	}

	@Test
	public void testInOrder() {
		setup2();
        assertEquals("35.5 39.1 80.0 88.8 ", avlTree.inOrder(avlTree.getRoot()));
	}

	@Test
	public void testPostOrder() {
		setup2();
        assertEquals("35.5 39.1 88.8 80.0 ", avlTree.postOrder(avlTree.getRoot()));
	}
}