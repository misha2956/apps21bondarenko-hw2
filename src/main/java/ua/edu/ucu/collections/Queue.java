package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    ImmutableLinkedList immutableLinkedList = new ImmutableLinkedList();

    public Object peek() {
        return immutableLinkedList.getFirst();
    }

    public Object dequeue() {
        Object val = immutableLinkedList.getFirst();
        immutableLinkedList = immutableLinkedList.removeFirst();
        return val;
    }

    public void enqueue(Object e) {
        immutableLinkedList = immutableLinkedList.addLast(e);
    }
}
