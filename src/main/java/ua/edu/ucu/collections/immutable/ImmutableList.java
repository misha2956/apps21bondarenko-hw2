package ua.edu.ucu.collections.immutable;


public interface ImmutableList {

    ImmutableList add(Object e);

    ImmutableList add(int index, Object e) throws IndexOutOfBoundsException;

    ImmutableList addAll(Object[] c);

    ImmutableList addAll(int index, Object[] c) throws IndexOutOfBoundsException;

    Object get(int index) throws IndexOutOfBoundsException;

    ImmutableList remove(int index) throws IndexOutOfBoundsException;

    ImmutableList set(int index, Object e) throws IndexOutOfBoundsException;

    int indexOf(Object e);

    int size();

    ImmutableList clear();

    boolean isEmpty();

    Object[] toArray();

    @Override
    String toString();
}
