package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    ImmutableLinkedList immutableLinkedList = new ImmutableLinkedList();

    public void push(Object e) {
        immutableLinkedList = immutableLinkedList.addLast(e);
    }

    public Object pop() {
        Object val = immutableLinkedList.getLast();
        immutableLinkedList = immutableLinkedList.removeLast();
        return val;
    }

    public Object peek() {
        return immutableLinkedList.getLast();
    }
}
