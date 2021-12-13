package ua.edu.ucu.collections.immutable;

public final class ImmutableLinkedList implements ImmutableList {
    private final Node firstNode, lastNode;
    private final int size;

    private static class NodePair {
        public final Node first, last;

        public NodePair(Node first, Node last) {
            this.first = first;
            this.last = last;
        }
    }

    public ImmutableLinkedList(Object[] elements) {
        if (elements.length == 0) {
            firstNode = null;
            lastNode = null;
            size = 0;
            return;
        }
        NodePair nodePair = createNodeSequence(elements);
        firstNode = nodePair.first;
        lastNode = nodePair.last;
        size = elements.length;
    }

    public ImmutableLinkedList() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    private ImmutableLinkedList(Node firstNode, Node lastNode, int size) {
        this.firstNode = firstNode;
        this.lastNode = lastNode;
        this.size = size;
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of bounds!");
        }
    }

    /**
     * Creates a node sequence from a non-empty array
     * @param c source array
     * @return NodePair or first & last node;
     */
    private static NodePair createNodeSequence(Object[] c) {
        Node firstNode = new Node(c[0]);
        Node lastNode = firstNode;
        for (int i = 1; i < c.length; i++) {
            Node prevNewNode = lastNode;
            lastNode = new Node(c[i]);
            prevNewNode.setNext(lastNode);
            lastNode.setPrevious(prevNewNode);
        }
        return new NodePair(firstNode, lastNode);
    }

    /**
     * Creates a node sequence copy.
     * @param firstNode source start Node
     * @param size node sequence length
     * @return NodePair or first & last nodes;
     */
    private static NodePair copyNodeSequence(Node firstNode, int size) {
        Node curNode = firstNode;
        Node newFirstNode = new Node(curNode.getValue());
        Node newLastNode = newFirstNode;
        for (int i = 1; i < size; i++) {
            curNode = curNode.getNext();
            Node prevNewNode = newLastNode;
            newLastNode = new Node(curNode.getValue());
            prevNewNode.setNext(newLastNode);
            newLastNode.setPrevious(prevNewNode);
        }
        return new NodePair(newFirstNode, newLastNode);
    }

    private static Node findIthNode(Node firstNode, int index) {
        Node curNode = firstNode;
        for (int i = 0; i < index; i++) {
            curNode = curNode.getNext();
        }
        return curNode;
    }

    @Override
    public ImmutableList add(Object e) {
        return this.addLast(e);
    }

    @Override
    public ImmutableList add(int index, Object e) throws IndexOutOfBoundsException {
        if (firstNode == null || index == size) {
            return this.add(e);
        }
        if (index == 0) {
            return this.addFirst(e);
        }
        checkIndex(index);

        NodePair initialNodePair = copyNodeSequence(firstNode, size);
        Node newNode = new Node(e);

        Node im1Node = findIthNode(initialNodePair.first, index - 1);
        im1Node.getNext().setPrevious(newNode);
        newNode.setNext(im1Node.getNext());
        im1Node.setNext(newNode);
        newNode.setPrevious(im1Node);

        return new ImmutableLinkedList(initialNodePair.first, initialNodePair.last, size + 1);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        if (firstNode == null) {
            return new ImmutableLinkedList(c);
        }
        NodePair initialNodePair = copyNodeSequence(firstNode, size);
        if (c.length == 0) {
            return new ImmutableLinkedList(initialNodePair.first, initialNodePair.last, size);
        }
        NodePair appendNodePair = createNodeSequence(c);
        initialNodePair.last.setNext(appendNodePair.first);
        appendNodePair.first.setPrevious(initialNodePair.last);
        return new ImmutableLinkedList(initialNodePair.first, appendNodePair.last, size + c.length);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) throws IndexOutOfBoundsException {
        if (firstNode == null || index == size) {
            return this.addAll(c);
        }
        checkIndex(index);

        NodePair initialNodePair = copyNodeSequence(firstNode, size);
        if (c.length == 0) {
            return new ImmutableLinkedList(initialNodePair.first, initialNodePair.last, size);
        }
        NodePair insertNodePair = createNodeSequence(c);
        int newSize = size + c.length;

        if (index == 0) {
            insertNodePair.last.setNext(initialNodePair.first);
            initialNodePair.first.setPrevious(insertNodePair.last);
            return new ImmutableLinkedList(insertNodePair.first, initialNodePair.last, newSize);
        }

        Node im1Node = findIthNode(initialNodePair.first, index - 1);
        im1Node.getNext().setPrevious(insertNodePair.last);
        insertNodePair.last.setNext(im1Node.getNext());
        im1Node.setNext(insertNodePair.first);
        insertNodePair.first.setPrevious(im1Node);

        return new ImmutableLinkedList(initialNodePair.first, initialNodePair.last, newSize);
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return findIthNode(firstNode, index).getValue();
    }

    @Override
    public ImmutableList remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        if (index == 0) {
            return this.removeFirst();
        }
        if (index == (size - 1)) {
            return this.removeLast();
        }
        NodePair initialNodePair = copyNodeSequence(firstNode, size);
        Node iNode = findIthNode(initialNodePair.first, index);
        Node previous = iNode.getPrevious();
        Node next = iNode.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        return new ImmutableLinkedList(initialNodePair.first, initialNodePair.last, size);
    }

    @Override
    public ImmutableList set(int index, Object e) throws IndexOutOfBoundsException {
        if (firstNode == null) {
            return this.add(index, e);
        }
        checkIndex(index);
        NodePair initialNodePair = copyNodeSequence(firstNode, size);
        Node iNode = findIthNode(initialNodePair.first, index);
        iNode.setValue(e);
        return new ImmutableLinkedList(initialNodePair.first, initialNodePair.last, size);
    }

    /**
     * searches for an element in the array
     * @param e object to search for
     * @return index of found Object, or -1 if object was not found
     */
    @Override
    public int indexOf(Object e) {
        if (firstNode == null) {
            return -1;
        }
        Node curNode = firstNode;
        for (int i = 0; i < size; i++) {
            if (curNode.getValue() == e) {
                return i;
            }
            curNode = curNode.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        if (firstNode == null) {
            return null;
        }
        Object[] ans = new Object[size];
        Node curNode = firstNode;
        for (int i = 0; i < size; i++) {
            ans[i] = curNode.getValue();
            curNode = curNode.getNext();
        }
        return ans;
    }

    public ImmutableLinkedList addFirst(Object e) {
        if (firstNode == null) {
            Node node = new Node(e);
            return new ImmutableLinkedList(node, node, 1);
        }
        NodePair initialNodePair = copyNodeSequence(firstNode, size);
        Node newNode = new Node(e);
        initialNodePair.first.setPrevious(newNode);
        newNode.setNext(initialNodePair.first);
        return new ImmutableLinkedList(newNode, initialNodePair.last, size + 1);
    }

    public ImmutableLinkedList addLast(Object e) {
        if (firstNode == null) {
            Node node = new Node(e);
            return new ImmutableLinkedList(node, node, 1);
        }
        NodePair nodePair = copyNodeSequence(firstNode, size);
        Node newLastNode = new Node(e);
        nodePair.last.setNext(newLastNode);
        newLastNode.setPrevious(nodePair.last);
        return new ImmutableLinkedList(nodePair.first, newLastNode, size + 1);
    }

    public Node getHead() {
        if (firstNode != null) {
            return firstNode.copy();
        } else {
            return null;
        }
    }

    public Node getTail() {
        if (lastNode != null) {
            return lastNode.copy();
        } else {
            return null;
        }
    }

    public Object getFirst() {
        if (firstNode != null) {
            return firstNode.getValue();
        } else {
            return null;
        }
    }

    public Object getLast() {
        if (lastNode != null) {
            return lastNode.getValue();
        } else {
            return null;
        }
    }

    public ImmutableLinkedList removeFirst() {
        if (firstNode == null) {
            return new ImmutableLinkedList();
        }
        NodePair initialNodePair = copyNodeSequence(firstNode, size);
        return new ImmutableLinkedList(initialNodePair.first.getNext(), initialNodePair.last, size - 1);
    }

    public ImmutableLinkedList removeLast() {
        if (firstNode == null) {
            return new ImmutableLinkedList();
        }
        NodePair initialNodePair = copyNodeSequence(firstNode, size);
        return new ImmutableLinkedList(initialNodePair.first, initialNodePair.last.getPrevious(), size - 1);
    }
}
