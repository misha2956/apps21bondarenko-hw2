package ua.edu.ucu.collections.immutable;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {

    private ImmutableArrayList immutableArrayList;

    @Before
    public void setUp() throws Exception {
        immutableArrayList = new ImmutableArrayList();
    }

    @Test
    public void testAdd() {
        assertEquals(immutableArrayList.add(3).get(0), 3);
    }

    @Test
    public void testAddAtPosition() {
        assertEquals(3, immutableArrayList.add(0, 3).get(0));
    }

    @Test
    public void testAddAll() {
        Integer[] c = new Integer[3];
        Arrays.fill(c, 1);
        assertEquals(1, immutableArrayList.addAll(c).get(2));
    }

    @Test
    public void testAddAllAtPosition() {
        Integer[] c = new Integer[3];
        Arrays.fill(c, 1);
        assertEquals(1, immutableArrayList.addAll(0, c).get(2));
        Integer[] b = new Integer[3];
        Arrays.fill(b, 2);
        assertEquals(1, immutableArrayList.addAll(0, c).addAll(2, b).get(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetExceptionZero() {
        immutableArrayList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException() {
        immutableArrayList.add(1).get(4);
    }

    @Test
    public void testGet() {
        assertEquals(1, immutableArrayList.add(1).get(0));
    }

    @Test
    public void testRemove() {
        assertEquals(2, immutableArrayList.add(1).add(2).remove(0).get(0));
    }

    @Test
    public void testSet() {
        assertEquals(3, immutableArrayList.add(1).add(2).set(0, 3).get(0));
    }

    @Test
    public void testIndexOf() {
        assertEquals(1, immutableArrayList.add(1).add(2).indexOf(2));
    }

    @Test
    public void testSize() {
        assertEquals(2, immutableArrayList.add(1).add(2).size());
    }

    @Test
    public void testClear() {
        assertEquals(0, immutableArrayList.add(1).add(2).clear().size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(immutableArrayList.add(1).add(2).clear().isEmpty());
    }

    @Test
    public void testToArray() {
        assertEquals(2, immutableArrayList.add(1).add(2).toArray()[1]);
    }
}