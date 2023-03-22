package ru.academits.polyanskaya.singly_linked_list;

public class ListItem<T> {
    private T data;
    private ListItem<T> next;

    public ListItem(T data) {
        this.data = data;
        next = null;
    }

    public ListItem(T data, ListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListItem<T> getNext() {
        return next;
    }

    public void setNext(ListItem<T> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return data == ((ListItem<?>) obj).data;
    }
}