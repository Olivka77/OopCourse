package ru.academits.polyanskaya.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        checkCapacity(capacity);

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        checkCapacity(capacity);

        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона допустимых значений от 0 до " + (size - 1));
        }
    }

    private void checkCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка: " + capacity + ", вместимость списка не может быть отрицательной");
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    private class ArrayListIterator implements ListIterator<E> {
        private int currentIndex = -1;
        private int startModCount;

        public ArrayListIterator() {
            startModCount = modCount;
        }

        public ArrayListIterator(int index) {
            startModCount = modCount;
            currentIndex = index - 1;
        }

        private void checkListChanges() {
            if (startModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            checkListChanges();

            ++currentIndex;

            return items[currentIndex];
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex != 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("Предыдущий элемент отсутствует");
            }

            checkListChanges();

            --currentIndex;

            return items[currentIndex];
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex;
        }

        @Override
        public void remove() {
            checkListChanges();

            ArrayList.this.remove(currentIndex);
            startModCount = modCount;

            --currentIndex;
        }

        @Override
        public void set(E item) {
            checkListChanges();

            ArrayList.this.set(currentIndex, item);
        }

        @Override
        public void add(E item) {
            checkListChanges();

            ArrayList.this.add(++currentIndex, item);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    @Override
    public boolean remove(Object object) {
        if (indexOf(object) >= 0) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index != size) {
            checkIndex(index);
        }

        int collectionSize = collection.size();

        if (collection.isEmpty()) {
            return false;
        }

        ensureCapacity(size + collectionSize);

        for (E item : collection) {
            add(index++, item);
            modCount++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        boolean isChanged = false;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                isChanged = true;

                i--;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        boolean isChanged = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                isChanged = true;

                i--;
            }
        }

        return isChanged;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            Arrays.fill(items, null);

            size = 0;

            modCount++;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }


    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        modCount++;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        if (index != size) {
            checkIndex(index);
        }

        ensureCapacity(size + 1);

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;

        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует в пустом списке");
        }

        checkIndex(index);

        E removedItem = get(index);

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        modCount++;
        size--;

        return removedItem;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ArrayListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkIndex(index);

        return new ArrayListIterator(index);
    }

    @Override // Без реализации
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        if (size != 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        return stringBuilder.append(']').toString();
    }
}