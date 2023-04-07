package ru.academits.polyanskaya.singly_linked_list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (ListItem<T> position = head; position != null; position = position.getNext()) {
            stringBuilder.append(position.getData()).append(", ");
        }

        if (head != null) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами диапазона допустимых значений "
                    + "от 0 до " + (size - 1));
        }
    }

    public T getDataByIndex(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getItem(index);

        T oldData = item.getData();

        item.setData(data);

        return oldData;
    }

    public T deleteByIndex(int index) {
        if (index == 0) {
            return deleteFirst();
        }

        checkIndex(index);

        ListItem<T> previousItem = getItem(index - 1);
        T deletedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());

        size--;

        return deletedData;
    }

    public void insert(int index, T data) {
        if (index == 0) {
            addFirst(data);

            return;
        }

        checkIndex(index - 1);

        ListItem<T> item = getItem(index - 1);

        item.setNext(new ListItem<>(data, item.getNext()));

        size++;
    }

    public boolean deleteByData(T data) {
        for (ListItem<T> position = head, previousItem = null; position != null; previousItem = position, position = position.getNext()) {
            if (position.getData().equals(data)) {
                if (previousItem == null) {
                    head = head.getNext();
                } else {
                    previousItem.setNext(position.getNext());
                }

                size--;

                return true;
            }
        }

        return false;
    }


    public T deleteFirst() {
        T dataToDelete = getFirst();
        head = head.getNext();

        size--;

        return dataToDelete;
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