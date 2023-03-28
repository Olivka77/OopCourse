package ru.academits.polyanskaya.my_array_list;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        checkCapacity(capacity);

        this.capacity = capacity;
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        checkCapacity(capacity);

        if (this.capacity < capacity) {
            items = Arrays.copyOf(items, capacity);
            this.capacity = capacity;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона допустимых значений от 0 до :" + (size - 1));
        }
    }

    private void checkCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Размер списка: " + capacity + ", размер списка не может быть отрицательным");
        }
    }

    private void ensureNotEmpty(Collection<?> collection) {
        if (collection.size() == 0) {
            throw new IllegalArgumentException("Коллекция пуста");
        }
    }

    public void trimToSize() {
        if (size < capacity) {
            items = Arrays.copyOf(items, size);

            capacity = size;
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

    private class MyArrayListIterator implements ListIterator<E> {
        private int currentIndex = -1;
        private int currentModCount;

        public MyArrayListIterator() {
            currentModCount = modCount;
        }

        public MyArrayListIterator(int index) {
            currentModCount = modCount;
            currentIndex = --index;
        }

        private void checkListChanges(int currentModCount, int modCount) {
            if (currentModCount != modCount) {
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

            checkListChanges(currentModCount, modCount);

            ++currentIndex;

            return items[currentIndex];
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex >= 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("Предыдущий элемент отсутствует");
            }

            checkListChanges(currentModCount, modCount);

            --currentIndex;

            return items[currentIndex];
        }

        @Override
        public int nextIndex() {
            return ++currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex;
        }

        @Override
        public void remove() {
            checkListChanges(currentModCount, modCount);

            MyArrayList.this.remove(currentIndex);
            currentModCount = modCount;

            --currentIndex;
        }

        @Override
        public void set(E element) {
            checkListChanges(currentModCount, modCount);

            MyArrayList.this.set(currentIndex, element);
        }

        @Override
        public void add(E element) {
            checkListChanges(currentModCount, modCount);

            MyArrayList.this.add(++currentIndex, element);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
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
        ensureCapacity(size + 1);
        items[size] = item;

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if (items[i] == object) {
                remove(i);

                size--;
                modCount++;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() > size) {
            throw new UnsupportedOperationException("Размер переданной коллекции " + collection.size() + " больше размера списка " + size);
        }

        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int collectionSize = collection.size();

        ensureNotEmpty(collection);

        ensureCapacity(size + collectionSize);

        //noinspection unchecked
        E[] collectionArray = (E[]) collection.toArray();

        System.arraycopy(collectionArray, 0, items, size, collectionSize);
        size += collectionSize;

        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index != size) {
            checkIndex(index);
        }

        ensureNotEmpty(collection);

        int collectionSize = collection.size();

        ensureCapacity(size + collectionSize);

        //noinspection unchecked
        E[] collectionArray = (E[]) collection.toArray();

        int itemsCountToMove = size - index;

        if (itemsCountToMove != 0) {
            System.arraycopy(items, index, items, index + collectionSize, itemsCountToMove);
        }

        System.arraycopy(collectionArray, 0, items, index, collectionSize);
        size += collectionSize;

        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        ensureNotEmpty(collection);

        boolean isChanged = false;

        MyArrayList<E> newList = new MyArrayList<>(0);

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                newList.add(items[i]);

                isChanged = true;
            }
        }

        if (isChanged) {
            items = newList.items;
            size = newList.size;
            capacity = newList.capacity;

            modCount++;
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        ensureNotEmpty(collection);

        boolean isChanged = false;

        MyArrayList<E> newList = new MyArrayList<>(size);

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                newList.add(items[i]);

                isChanged = true;
            }
        }

        if (isChanged) {
            items = newList.items;
            size = newList.size;
            capacity = newList.capacity;

            modCount++;
        }

        return isChanged;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            E oldValue = items[i];
            E newValue = operator.apply(oldValue);
            items[i] = newValue;
        }

        modCount++;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        Arrays.sort(items, 0, size, comparator);
    }

    public static class MyComparator<E extends Comparable<E>> implements Comparator<E> {
        @Override
        public int compare(E object1, E object2) {
            return object1.compareTo(object2);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;

        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        if (isEmpty()) {
            throw new NullPointerException("Список пуст");
        }

        return items[index];
    }


    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldValue = items[index];
        items[index] = element;

        modCount++;

        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        if (index != 0) {
            checkIndex(index - 1);
        }

        ensureCapacity(size + 1);

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;

        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        if (size == 0) {
            throw new UnsupportedOperationException("Удаление элемента из пустого списка");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами допустимых значений от 0 до " + (size - 1));
        }

        E elementToRemove = get(index);

        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }

        items[size - 1] = null;

        modCount++;
        size--;

        return elementToRemove;
    }

    @Override
    public int indexOf(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyArrayListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkIndex(index);

        return new MyArrayListIterator(index);
    }

    @Override // Без реализации
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection ConstantConditions
        return null;
    }

    @Override // Без реализации
    public Spliterator<E> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public String toString() {
        if (size == 0) {
            throw new NoSuchElementException("Ошибка печати элементов пустого списка");
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        return (stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length())).toString();
    }
}