package ru.academits.polyanskaya.singly_linked_list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
        head = null;
    }

    @Override
    public String toString() {
        if (head == null) {
            throw new NoSuchElementException("Ошибка печати элементов пустого списка");
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            stringBuilder.append(p.getData()).append(", ");
        }

        return (stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length())).toString();
    }

    public void addItemToTop(T data) {
        if (head == null) {
            head = new ListItem<>(data, null);
        } else {
            head = new ListItem<>(data, head);
        }

        count++;
    }

    public int getSize() {
        return count;
    }

    public T getFirstItemData() {
        if (head == null) {
            throw new NoSuchElementException("Ошибка доступа к первому элементу в пустом списке");
        }

        return head.getData();
    }

    private ListItem<T> getItem(int index) {
        if (index > count - 1 || index < 0) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " за пределами диапазона допустимых значений "
                    + "от 0 до " + (count - 1));
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    public T getDataByIndex(int index) {
        return getItem(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        T oldData = getItem(index).getData();

        getItem(index).setData(data);

        return oldData;
    }

    public T deleteItemByIndex(int index) {
        if (index == 0) {
            T dataToDelete = getDataByIndex(0);
            head = head.getNext();

            count--;

            return dataToDelete;
        }

        ListItem<T> itemBeforeItemToDelete = getItem(index - 1);
        T dataToDelete = itemBeforeItemToDelete.getNext().getData();
        itemBeforeItemToDelete.setNext(itemBeforeItemToDelete.getNext().getNext());

        count--;

        return dataToDelete;
    }

    public void insertItem(int index, T data) {
        if (index == 0) {
            addItemToTop(data);

            return;
        }

        if (index == count) {
            count++;
            getItem(index - 1).setNext(new ListItem<>(data, null));

            return;
        }

        getItem(index - 1).setNext(new ListItem<>(data, getItem(index)));
        count++;
    }

    public boolean deleteItemByData(T data) {
        ListItem<T> item = new ListItem<>(data, head);

        for (int i = 0; i < count; i++) {
            item = item.getNext();

            if (item.getData().equals(data)) {
                deleteItemByIndex(i);

                return true;
            }
        }

        return false;
    }

    public T deleteFirstElement() {
        return deleteItemByIndex(0);
    }

    public void listReversal() {
        ListItem<T> previousItem = null;
        ListItem<T> nextItem;

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = nextItem) {
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

        resultList.count = count;
        resultList.head = newHead;

        return resultList;
    }
}