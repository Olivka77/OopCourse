package ru.academits.polyanskaya.list_main;

import ru.academits.polyanskaya.singly_linked_list.SinglyLinkedList;

public class SinglyLinkedListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addItemToTop(50);
        list.addItemToTop(40);
        list.addItemToTop(30);
        list.addItemToTop(20);
        list.addItemToTop(10);
        list.addItemToTop(123);
        list.addItemToTop(15);

        System.out.println("Список: " + list);
        System.out.println("Значение первого элемент списка: " + list.getFirstItemData());
        System.out.println("Размер списка: " + list.getSize());
        System.out.println("Значение по индексу 1: " + list.getDataByIndex(1));
        System.out.println("Замена значения по индексу 1 на 321, старое значение: " + list.setDataByIndex(1, 321));
        System.out.println("Список после изменения: " + list);
        System.out.println("Удаление первого элемента списка, старое значение: " + list.deleteItemByIndex(0));
        System.out.println("Список после изменения: " + list);
        System.out.println("Удаление последнего элемента списка, старое значение: " + list.deleteItemByIndex(5));
        System.out.println("Список после изменения: " + list);

        list.insertItem(1, 0);

        System.out.println("Список после вставки элемента 0 в список по индексу 1: " + list);
        System.out.println("Результат удаления значения 321: " + list.deleteItemByData(321));
        System.out.println("Список после удаления значения 321: " + list);
        System.out.println("Результат удаления первого элемента: " + list.deleteFirstElement());
        System.out.println("Список после удаления первого элемента: " + list);

        list.listReversal();

        System.out.println("Разворот списка: " + list);

        SinglyLinkedList<Integer> listCopy = list.copy();

        System.out.println("Копия списка: " + listCopy);

        list.setDataByIndex(0, 100);

        System.out.println("Исходный список после изменения первого элемента: " + list);
        System.out.println("Копия списка после изменения первого элемента в исходном списке: " + listCopy);
    }
}