package ua.edu.ucu.collections.immutable;

public class Node {
    private Node previous;
    private Node next;
    private Object value;

    public Node() {
    }

    @Override
    public String toString() {
        return value.toString();
    }


    public Node getPrevious() {
        return null;
    }

    public void setPrevious(Node prev) {
    }

    public Object getValue() {
        return null;
    }

    public void setValue(Object val) {
    }

    public Node getNext() {
        return null;
    }

    public void setNext(Node ne) {
    }
}

