package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ImmutableLinkedListTest {

    private ImmutableLinkedList immutableLinkedList;

    @Before
    public void setUp() throws Exception {
        immutableLinkedList = new ImmutableLinkedList();
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(1, (new ImmutableLinkedList(new Integer[0])).add(1).get(0));
    }

    @Test
    public void add() {
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.add(5);
        assertEquals(5, immutableLinkedList.get(0));
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.add(2);
        assertEquals(5, immutableLinkedList.get(0));
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.add(8);
        assertEquals(2, immutableLinkedList.get(1));
    }

    @Test
    public void addAtIndex() {
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.add(0, 5);
        assertEquals(5, immutableLinkedList.get(0));
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.add(0, 6);
        assertEquals(6, immutableLinkedList.get(0));
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.add(2, 7);
        assertEquals(7, immutableLinkedList.get(2));
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.add(1, 3);
        assertEquals(3, immutableLinkedList.get(1));
    }

    @Test
    public void addAll() {
        Integer[] c = new Integer[5];
        Arrays.fill(c, 5);
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.addAll(c);
        assertEquals(5, immutableLinkedList.get(0));
        assertEquals(5, immutableLinkedList.get(4));
        Integer[] d = new Integer[5];
        Arrays.fill(d, 7);
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.addAll(d);
        assertEquals(7, immutableLinkedList.get(5));
        assertEquals(7, immutableLinkedList.get(9));
        assertEquals(1, immutableLinkedList.add(1).addAll(new Integer[0]).get(10));
    }

    @Test
    public void addAllAtIndex() {
        Integer[] c = new Integer[5];
        Arrays.fill(c, 5);
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.addAll(0, c);
        assertEquals(5, immutableLinkedList.get(0));
        assertEquals(5, immutableLinkedList.get(4));
        Integer[] d = new Integer[5];
        Arrays.fill(d, 7);
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.addAll(3, d);
        assertEquals(7, immutableLinkedList.get(7));
        assertEquals(5, immutableLinkedList.get(8));
        assertEquals(7, immutableLinkedList.get(3));
        assertEquals(5, immutableLinkedList.get(2));
        assertEquals(1, immutableLinkedList.add(1).addAll(0, new Integer[0]).get(10));
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.addAll(0, c);
        assertEquals(5, immutableLinkedList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getZeroException() {
        immutableLinkedList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getExceptionNegative() {
        immutableLinkedList.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getException() {
        immutableLinkedList.add(7).get(1);
    }

    @Test
    public void get() {
        assertEquals(7, immutableLinkedList.add(7).get(0));
    }

    @Test
    public void remove() {
        assertEquals(5, immutableLinkedList.add(7).add(5).remove(0).get(0));
        assertEquals(7, immutableLinkedList.add(7).add(5).remove(1).get(0));
        assertEquals(6, immutableLinkedList.add(7).add(5).add(6).remove(1).get(1));
    }

    @Test
    public void set() {
        assertEquals(3, immutableLinkedList.add(1).add(2).set(0, 3).get(0));
        assertEquals(3, immutableLinkedList.set(0, 3).get(0));
    }

    @Test
    public void indexOf() {
        assertEquals(1, immutableLinkedList.add(1).add(2).indexOf(2));
        assertEquals(-1, immutableLinkedList.indexOf(2));
        assertEquals(-1, immutableLinkedList.add(1).add(2).indexOf(345));
    }

    @Test
    public void size() {
        assertEquals(2, immutableLinkedList.add(1).add(2).size());
    }

    @Test
    public void clear() {
        assertEquals(0, immutableLinkedList.add(1).add(2).clear().size());
    }

    @Test
    public void isEmpty() {
        assertFalse(immutableLinkedList.add(1).add(2).isEmpty());
        assertTrue(immutableLinkedList.add(1).add(2).clear().isEmpty());
    }

    @Test
    public void toArray() {
        assertEquals(2, immutableLinkedList.add(1).add(2).toArray()[1]);
        assertNull(immutableLinkedList.toArray());
    }

    @Test
    public void addFirst() {
        assertEquals(2, immutableLinkedList.addFirst(1).addFirst(2).get(0));
    }

    @Test
    public void addLast() {
        assertEquals(1, immutableLinkedList.addLast(1).addLast(2).get(0));
    }

    @Test
    public void getHead() {
        assertEquals(2, immutableLinkedList.addFirst(1).addFirst(2).getHead().getValue());
        assertNull(immutableLinkedList.getHead());
    }

    @Test
    public void getTail() {
        assertEquals(1, immutableLinkedList.addFirst(1).addFirst(2).getTail().getValue());
        assertNull(immutableLinkedList.getTail());
    }

    @Test
    public void getFirst() {
        assertEquals(2, immutableLinkedList.addFirst(1).addFirst(2).getFirst());
        assertNull(immutableLinkedList.getFirst());
    }

    @Test
    public void getLast() {
        assertEquals(1, immutableLinkedList.addFirst(1).addFirst(2).getLast());
        assertNull(immutableLinkedList.getLast());
    }

    @Test
    public void removeFirst() {
        assertEquals(1, immutableLinkedList.addFirst(1).addFirst(2).removeFirst().getFirst());
        assertEquals(1, immutableLinkedList.removeFirst().add(1).get(0));
    }

    @Test
    public void removeLast() {
        assertEquals(2, immutableLinkedList.addFirst(1).addFirst(2).removeLast().getLast());
        assertEquals(1, immutableLinkedList.removeLast().add(1).get(0));
    }
}