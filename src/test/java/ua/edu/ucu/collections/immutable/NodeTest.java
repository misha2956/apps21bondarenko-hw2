package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    private Node node;

    @Before
    public void setUp() throws Exception {
        node = new Node();
    }

    @Test
    public void testToString() {
        node.setValue(1);
        assertEquals("1", node.toString());
    }
}