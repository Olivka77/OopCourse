package ru.academits.polyanskaya.my_array_list_main;

import ru.academits.polyanskaya.my_array_list.MyArrayList;

import java.util.Arrays;
import java.util.HashSet;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList1 = new MyArrayList<>(20);
        MyArrayList<String> myArrayList2 = new MyArrayList<>();

        System.out.println("Размер списков myArrayList1 и myArrayList2: " + myArrayList1.size() + " и " + myArrayList2.size());

        myArrayList1.ensureCapacity(21);
        String string1 = "Один";

        myArrayList2.add(string1);
        myArrayList2.add("Два");
        myArrayList2.add("Три");

        System.out.println("Список myArrayList2: " + myArrayList2);

        myArrayList1.add(20);
        myArrayList1.add(10);
        myArrayList1.add(9);
        myArrayList1.trimToSize();

        System.out.println("Размер списка myArrayList1 после добавления 3 элементов и вызова метода trimToSize: " + myArrayList1.size());

        MyArrayList<Integer> myArrayList3 = new MyArrayList<>();

        System.out.println("Пустой ли список myArrayList3: " + myArrayList3.isEmpty());
        System.out.println("Содержит ли список myArrayList2 объект string: " + myArrayList2.contains(string1));
        System.out.print("Печать списка myArrayList2 с помощью итератора: ");

        for (String string2 : myArrayList2) {
            System.out.print(string2 + " ");
        }

        Object[] objectsArray = myArrayList2.toArray();
        System.out.println();
        System.out.print("Массив objectsArray: " + Arrays.toString(objectsArray));

        System.out.println();
        System.out.println("Список stringsArray2: " + myArrayList2);

        System.out.println("Результат удаления элемента по индексу 0 из списка myArrayList1: " + myArrayList1.remove(0));
        System.out.println("Список myArrayList1 после удаления элемента: " + myArrayList1);

        myArrayList3.add(8);
        myArrayList3.add(7);

        System.out.println("Содержит ли myArrayList1 список myArrayList3: " + new HashSet<>(myArrayList1).containsAll(myArrayList3));

        myArrayList1.addAll(myArrayList3);
        myArrayList1.addAll(1, myArrayList3);

        System.out.println("Список myArrayList1 после добавления myArrayList3: " + myArrayList1);

        myArrayList1.removeAll(myArrayList3);
        System.out.println("Список myArrayList1 после удаления myArrayList3: " + myArrayList1);

        myArrayList1.add(8);
        myArrayList1.add(8);
        myArrayList1.add(8);
        myArrayList1.add(12);
        myArrayList1.add(8);
        myArrayList1.add(0, 8);

        System.out.println("Список myArrayList1 после добавления элементов: " + myArrayList1);

        myArrayList1.retainAll(myArrayList3);

        System.out.println("Список myArrayList1 после вызова метода retainAll: " + myArrayList1);

        myArrayList3.sort(new MyArrayList.MyComparator<>());

        System.out.println("Список myArrayList3 после вызова метода retainAll: " + myArrayList3);

        myArrayList1.replaceAll(n -> n * n);

        System.out.println("Список myArrayList1 после вызова метода replaceAll: " + myArrayList1);
        System.out.println("Элемент списка myArrayList1 с индексом 0: " + myArrayList1.get(0));

        myArrayList1.set(0, 8);

        System.out.println("Список myArrayList1 после замены первого элемента: " + myArrayList1);

        myArrayList1.add(0, 3);
        myArrayList1.add(6, 3);

        System.out.println("Список myArrayList1 после добавления элементов: " + myArrayList1);
        System.out.println("Индекс первого вхождения элемента 8 в список myArrayList1: " + myArrayList1.indexOf(8));
        System.out.println("Индекс последнего вхождения элемента 64 в список myArrayList1: " + myArrayList1.lastIndexOf(64));
    }
}