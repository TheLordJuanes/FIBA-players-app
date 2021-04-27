package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class BSTreeTest {

	private BSTree<Double, ArrayList<Integer>, Integer> bstree;

	public void setup1() {
		bstree = new BSTree<>();
	}

	public void setup2() {
		bstree = new BSTree<>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		BSTNode<Double, ArrayList<Integer>> node = new BSTNode<>(80.0, list);
		bstree.insert(node, 0);
		list = new ArrayList<Integer>();
		list.add(1);
		node = new BSTNode<Double, ArrayList<Integer>>(88.8, list);
		bstree.insert(node, 1);
		list = new ArrayList<Integer>();
		list.add(2);
		node = new BSTNode<Double, ArrayList<Integer>>(39.1, list);
		bstree.insert(node, 2);
		list = new ArrayList<Integer>();
		list.add(3);
		node = new BSTNode<>(35.5, list);
		bstree.insert(node, 3);
	}

	@Test
	public void testInsert() {
		setup1();
		assertNull(bstree.getRoot());
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		BSTNode<Double, ArrayList<Integer>> node = new BSTNode<>(11.1, list);
		bstree.insert(node, 0);
		assertNotNull(bstree.getRoot());
		assertNotNull(bstree.search(11.1));
		assertEquals(11.1, bstree.search(11.1).getKey());
		assertNull(bstree.getRoot().getParent());
		assertNull(bstree.getRoot().getLeft());
		assertNull(bstree.getRoot().getRight());
		setup2();
		assertNotNull(bstree.getRoot());
		list = new ArrayList<Integer>();
		list.add(4);
		node = new BSTNode<>(30.0, list);
		bstree.insert(node, 4);
		BSTNode<Double, ArrayList<Integer>> objSearch = bstree.search(30.0);
		assertNotNull(objSearch);
		assertEquals(30.0, objSearch.getKey());
		assertNull(objSearch.getLeft());
		assertNull(objSearch.getRight());
		assertNotNull(objSearch.getParent());
		assertEquals(35.5, objSearch.getParent().getKey());
		assertEquals(30.0, objSearch.getParent().getLeft().getKey());
		assertEquals(80.0, bstree.getRoot().getKey());
		setup2();
		list = new ArrayList<Integer>();
		list.add(4);
		node = new BSTNode<>(80.0, list);
		bstree.insert(node, 4);
		assertEquals(2, bstree.getRoot().getValue().size());
	}

	@Test
	public void testSearch() {
		setup1();
		assertNull(bstree.getRoot());
		assertNull(bstree.search(11.1));
		setup2();
		assertNotNull(bstree.getRoot());
		assertNotNull(bstree.search(88.8));
	}

	@Test
	public void testDelete() {
		setup1();
		assertNull(bstree.getRoot());
		assertFalse(bstree.delete(50.0, 0));
		setup2();
		assertNotNull(bstree.getRoot());
		assertEquals(80.0, bstree.getRoot().getKey());
		assertNotNull(bstree.search(35.5));
		assertTrue(bstree.delete(35.5, 0));
		assertNull(bstree.search(35.5));
		setup2();
		assertNull(bstree.search(11.4));
		assertFalse(bstree.delete(11.4, 0));
	}

	@Test
	public void testPreOrder() {
		setup2();
		assertEquals("80.0 39.1 35.5 88.8 ", bstree.preOrder(bstree.getRoot()));
	}

	@Test
	public void testInOrder() {
		setup2();
		assertEquals("35.5 39.1 80.0 88.8 ", bstree.inOrder(bstree.getRoot()));
	}

	@Test
	public void testPostOrder() {
		setup2();
		assertEquals("35.5 39.1 88.8 80.0 ", bstree.postOrder(bstree.getRoot()));
	}
}