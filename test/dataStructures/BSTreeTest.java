package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class BSTreeTest {

	private BSTree<Double, ArrayList<Integer>, Integer> bsTree;

	public void setup1() {
		bsTree = new BSTree<>();
	}

	public void setup2() {
		bsTree = new BSTree<>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		BSTNode<Double, ArrayList<Integer>> node = new BSTNode<>(80.0, list);
		bsTree.insert(node, 0);
		list = new ArrayList<Integer>();
		list.add(1);
		node = new BSTNode<Double, ArrayList<Integer>>(88.8, list);
		bsTree.insert(node, 1);
		list = new ArrayList<Integer>();
		list.add(2);
		node = new BSTNode<Double, ArrayList<Integer>>(39.1, list);
		bsTree.insert(node, 2);
		list = new ArrayList<Integer>();
		list.add(3);
		node = new BSTNode<Double, ArrayList<Integer>>(45.0, list);
		bsTree.insert(node, 3);
		list = new ArrayList<Integer>();
		list.add(4);
		node = new BSTNode<>(35.5, list);
		bsTree.insert(node, 4);
		list = new ArrayList<Integer>();
		list.add(5);
		node = new BSTNode<>(83.7, list);
		bsTree.insert(node, 5);
		list = new ArrayList<Integer>();
		list.add(6);
		node = new BSTNode<>(90.4, list);
		bsTree.insert(node, 6);
	}

	@Test
	public void testInsert() {
		setup1();
		assertNull(bsTree.getRoot());
		assertEquals(0, bsTree.getSizeNodes());
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		BSTNode<Double, ArrayList<Integer>> node = new BSTNode<>(11.1, list);
		bsTree.insert(node, 0);
		assertNotNull(bsTree.getRoot());
		assertNotNull(bsTree.search(11.1));
		assertEquals(11.1, bsTree.search(11.1).getKey());
		assertNull(bsTree.getRoot().getParent());
		assertNull(bsTree.getRoot().getLeft());
		assertNull(bsTree.getRoot().getRight());
		assertEquals(1, bsTree.getSizeNodes());
		assertEquals(1, bsTree.getSizeElements());
		setup2();
		assertNotNull(bsTree.getRoot());
		assertEquals(7, bsTree.getSizeNodes());
		assertEquals(7, bsTree.getSizeElements());
		list = new ArrayList<Integer>();
		list.add(4);
		node = new BSTNode<>(30.0, list);
		bsTree.insert(node, 4);
		BSTNode<Double, ArrayList<Integer>> objSearch = bsTree.search(30.0);
		assertNotNull(objSearch);
		assertEquals(8, bsTree.getSizeNodes());
		assertEquals(30.0, objSearch.getKey());
		assertNull(objSearch.getLeft());
		assertNull(objSearch.getRight());
		assertNotNull(objSearch.getParent());
		assertEquals(35.5, objSearch.getParent().getKey());
		assertEquals(30.0, objSearch.getParent().getLeft().getKey());
		assertEquals(80.0, bsTree.getRoot().getKey());
		setup2();
		list = new ArrayList<Integer>();
		list.add(4);
		node = new BSTNode<>(80.0, list);
		bsTree.insert(node, 4);
		assertEquals(2, bsTree.getRoot().getValue().size());
		assertEquals(7, bsTree.getSizeNodes());
		assertEquals(8, bsTree.getSizeElements());
	}

	@Test
	public void testSearch() {
		setup1();
		assertNull(bsTree.getRoot());
		assertNull(bsTree.search(11.1));
		setup2();
		assertNotNull(bsTree.getRoot());
		assertNotNull(bsTree.search(45.0));
	}

	@Test
	public void testDelete() {
		setup1();
		assertNull(bsTree.getRoot());
		assertEquals(0, bsTree.getSizeNodes());
		assertEquals(0, bsTree.getSizeElements());
		assertFalse(bsTree.delete(50.0, 0));
		assertEquals(0, bsTree.getSizeNodes());
		assertEquals(0, bsTree.getSizeElements());
		setup2();
		assertNotNull(bsTree.getRoot());
		assertEquals(80.0, bsTree.getRoot().getKey());
		assertEquals(7, bsTree.getSizeNodes());
		assertEquals(7, bsTree.getSizeElements());
		assertNotNull(bsTree.search(35.5));
		assertTrue(bsTree.delete(35.5, 0));
		assertNull(bsTree.search(35.5));
		assertEquals(6, bsTree.getSizeNodes());
		assertEquals(6, bsTree.getSizeElements());
		setup2();
		assertEquals(7, bsTree.getSizeNodes());
		assertEquals(7, bsTree.getSizeElements());
		assertNull(bsTree.search(11.4));
		assertFalse(bsTree.delete(11.4, 0));
		assertTrue(bsTree.delete(80.0, 0));
		assertNull(bsTree.search(80.0));
		assertEquals(6, bsTree.getSizeNodes());
		assertEquals(6, bsTree.getSizeElements());
		setup2();
		assertEquals(7, bsTree.getSizeNodes());
		assertEquals(7, bsTree.getSizeElements());
		assertNotNull(bsTree.search(39.1));
		assertTrue(bsTree.delete(39.1, 0));
		assertNull(bsTree.search(39.1));
		assertEquals(6, bsTree.getSizeNodes());
		assertEquals(6, bsTree.getSizeElements());
		setup2();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		BSTNode<Double, ArrayList<Integer>> node = new BSTNode<>(80.0, list);
		bsTree.insert(node, 0);
		assertEquals(7, bsTree.getSizeNodes());
		assertEquals(8, bsTree.getSizeElements());
		assertNotNull(bsTree.search(80.0));
		assertTrue(bsTree.delete(80.0, 0));
		assertNotNull(bsTree.search(80.0));
		assertEquals(1, bsTree.search(80.0).getValue().size());
		assertEquals(7, bsTree.getSizeNodes());
		assertEquals(7, bsTree.getSizeElements());
	}

	@Test
	public void testPredecessor() {
		setup2();
		assertEquals(35.5, bsTree.predecessor(bsTree.search(39.1)).getKey());
		assertEquals(39.1, bsTree.predecessor(bsTree.search(45.0)).getKey());
		assertEquals(45.0, bsTree.predecessor(bsTree.search(80.0)).getKey());
		assertEquals(83.7, bsTree.predecessor(bsTree.search(88.8)).getKey());
	}

	@Test
	public void testSucessor() {
		setup2();
		assertEquals(45.0, bsTree.successor(bsTree.search(39.1)).getKey());
		assertEquals(80.0, bsTree.successor(bsTree.search(45.0)).getKey());
		assertEquals(83.7, bsTree.successor(bsTree.search(80.0)).getKey());
		assertEquals(90.4, bsTree.successor(bsTree.search(88.8)).getKey());
	}

	@Test
	public void testPreOrder() {
		setup2();
		assertEquals("80.0 39.1 35.5 45.0 88.8 83.7 90.4 ", bsTree.preOrder(bsTree.getRoot()));
	}

	@Test
	public void testInOrder() {
		setup2();
		assertEquals("35.5 39.1 45.0 80.0 83.7 88.8 90.4 ", bsTree.inOrder(bsTree.getRoot()));
	}

	@Test
	public void testPostOrder() {
		setup2();
		assertEquals("35.5 45.0 39.1 83.7 90.4 88.8 80.0 ", bsTree.postOrder(bsTree.getRoot()));
	}
}