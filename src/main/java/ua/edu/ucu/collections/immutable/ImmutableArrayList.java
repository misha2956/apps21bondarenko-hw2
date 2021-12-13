package ua.edu.ucu.collections.immutable;

public final class ImmutableArrayList implements ImmutableList {
    private static final int INIT_SIZE = 4;
    private final Object[] arrayList;
    private final int size;

    public ImmutableArrayList(Object[] elements) {
        arrayList = elements.clone();
        size = arrayList.length;
    }

    public ImmutableArrayList() {
        arrayList = new Object[INIT_SIZE];
        size = 0;
    }

    private ImmutableArrayList(Object[] arrayList, int newSize) {
        this.arrayList = arrayList;
        this.size = newSize;
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of bounds!");
        }
    }

    @Override
    public ImmutableList add(Object e) {
        Object[] newArrayList = arrayList;
        int newSize = size + 1;
        if (arrayList.length < newSize) {
            newSize = size * 2;
            newArrayList = new Object[newSize];
            System.arraycopy(arrayList, 0, newArrayList, 0, size);
        }
        newArrayList[size] = e;
        return new ImmutableArrayList(newArrayList, newSize);
    }

    @Override
    public ImmutableList add(int index, Object e) throws IndexOutOfBoundsException {
        if (index == size) {
            return this.add(e);
        }
        checkIndex(index);
        int newSize = size + 1;
        Object[] newArrayList = new Object[newSize];
        System.arraycopy(arrayList, 0, newArrayList, 0, index);
        newArrayList[index] = e;
        System.arraycopy(arrayList, index, newArrayList, index + 1, size - index);
        return new ImmutableArrayList(newArrayList, newSize);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        Object[] newArrayList = arrayList;
        int newSize = size + c.length;
        if (newSize == size) {
            return new ImmutableArrayList(newArrayList, newSize);
        }
        if (arrayList.length < newSize) {
            newArrayList = new Object[newSize];
            System.arraycopy(arrayList, 0, newArrayList, 0, size);
        }
        System.arraycopy(c, 0, newArrayList, size, c.length);
        return new ImmutableArrayList(newArrayList, newSize);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) throws IndexOutOfBoundsException {
        if (index == size) {
            return this.addAll(c);
        }
        checkIndex(index);
        int newSize = size + c.length;
        Object[] newArrayList = new Object[newSize];
        System.arraycopy(arrayList, 0, newArrayList, 0, index);
        System.arraycopy(c, 0, newArrayList, index, c.length);
        System.arraycopy(arrayList, index, newArrayList, index + c.length, size - index);
        return new ImmutableArrayList(newArrayList, newSize);
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public ImmutableList remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        int newSize = size - 1;
        Object[] newArrayList = new Object[newSize];
        System.arraycopy(arrayList, 0, newArrayList, 0, index);
        System.arraycopy(arrayList, index + 1, newArrayList, index, size - index - 1);
        return new ImmutableArrayList(newArrayList, newSize);
    }

    @Override
    public ImmutableList set(int index, Object e) throws IndexOutOfBoundsException {
        checkIndex(index);
        Object[] newArrayList = new Object[size];
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        newArrayList[index] = e;
        return new ImmutableArrayList(newArrayList, size);
    }

    /**
     * searches for an element in the array
     * @param e object to search for
     * @return index of found Object, or -1 if object was not found
     */
    @Override
    public int indexOf(Object e) {
        int ans = -1;
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == e) {
                ans = i;
            }
        }
        return ans;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        return arrayList.clone();
    }
}
