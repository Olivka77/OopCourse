package ru.academits.polyanskaya.singly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            stringBuilder.append(currentItem.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.append(']').toString();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Первый элемент отсутствует - список пуст");
        }

        return head.getData();
    }

    private ListItem<T> getItem(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    private void checkIndex(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует, список пуст");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона допустимых значений "
                    + "от 0 до " + (size - 1));
        }
    }

    public T getByIndex(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getItem(index);
        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> previousItem = getItem(index - 1);
        T deletedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());

        size--;

        return deletedData;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона допустимых значений "
                    + "от 0 до " + size);
        }

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<T> item = getItem(index - 1);
        item.setNext(new ListItem<>(data, item.getNext()));

        size++;
    }

    public boolean deleteByData(T data) {
        if (head == null) {
            return false;
        }

        for (ListItem<T> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(data, currentItem.getData())) {
                if (previousItem == null) {
                    head = head.getNext();
                } else {
                    previousItem.setNext(currentItem.getNext());
                }

                size--;

                return true;
            }
        }

        return false;
    }

    public T deleteFirst() {
        T deletedData = getFirst();
        head = head.getNext();

        size--;

        return deletedData;
    }

    public void reverse() {
        ListItem<T> previousItem = null;

        for (ListItem<T> currentItem = head, nextItem; currentItem != null; currentItem = nextItem) {
            nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> resultList = new SinglyLinkedList<>();

        if (head == null) {
            return resultList;
        }

        ListItem<T> newHead = new ListItem<>(head.getData());
        ListItem<T> newCurrentItem = newHead;

        for (ListItem<T> currentItem = head.getNext(); currentItem != null; currentItem = currentItem.getNext()) {
            ListItem<T> newItem = new ListItem<>(currentItem.getData());
            newCurrentItem.setNext(newItem);
            newCurrentItem = newItem;
        }

        resultList.size = size;
        resultList.head = newHead;

        return resultList;
    }
}