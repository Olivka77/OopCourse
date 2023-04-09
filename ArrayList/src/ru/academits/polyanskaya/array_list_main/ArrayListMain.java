package ru.academits.polyanskaya.array_list_main;

import ru.academits.polyanskaya.array_list.ArrayList;

import java.util.Arrays;
import java.util.HashSet;

public class ArrayListMain {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>(20);
        ArrayList<String> arrayList2 = new ArrayList<>();

        System.out.println("Размер списков arrayList1 и arrayList2: " + arrayList1.size() + " и " + arrayList2.size());

        arrayList1.ensureCapacity(21);
        String string1 = "Один";

        arrayList2.add(string1);
        arrayList2.add("Два");
        arrayList2.add("Три");

        System.out.println("Список arrayList2: " + arrayList2);

        arrayList1.add(20);
        arrayList1.add(10);
        arrayList1.add(9);
        arrayList1.trimToSize();

        System.out.println("Размер списка arrayList1 после добавления 3 элементов и вызова метода trimToSize: " + arrayList1.size());

        ArrayList<Integer> arrayList3 = new ArrayList<>();

        System.out.println("Пустой ли список arrayList3: " + arrayList3.isEmpty());
        System.out.println("Содержит ли список arrayList2 объект string: " + arrayList2.contains(string1));
        System.out.print("Печать списка arrayList2 с помощью итератора: ");

        for (String string : arrayList2) {
            System.out.print(string + " ");
        }

        Object[] objectsArray = arrayList2.toArray();
        System.out.println();
        System.out.print("Массив objectsArray: " + Arrays.toString(objectsArray));

        System.out.println();
        System.out.println("Список stringsArray2: " + arrayList2);

        System.out.println("Результат удаления элемента по индексу 0 из списка arrayList1: " + arrayList1.remove(0));
        System.out.println("Список arrayList1 после удаления элемента: " + arrayList1);

        arrayList3.add(8);
        arrayList3.add(7);

        System.out.println("Содержит ли arrayList1 список arrayList3: " + new HashSet<>(arrayList1).containsAll(arrayList3));

        arrayList1.addAll(arrayList3);
        arrayList1.addAll(1, arrayList3);

        System.out.println("Список arrayList1 после добавления arrayList3: " + arrayList1);

        arrayList1.removeAll(arrayList3);
        System.out.println("Список arrayList1 после удаления arrayList3: " + arrayList1);

        arrayList1.add(8);
        arrayList1.add(8);
        arrayList1.add(8);
        arrayList1.add(12);
        arrayList1.add(8);
        arrayList1.add(0, 8);

        System.out.println("Список arrayList1 после добавления элементов: " + arrayList1);

        arrayList1.retainAll(arrayList3);

        System.out.println("Список arrayList1 после вызова метода retainAll: " + arrayList1);
        System.out.println("Элемент списка arrayList1 с индексом 0: " + arrayList1.get(0));

        arrayList1.set(0, 64);

        System.out.println("Список arrayList1 после замены первого элемента: " + arrayList1);

        arrayList1.add(0, 3);
        arrayList1.add(6, 3);

        System.out.println("Список arrayList1 после добавления элементов: " + arrayList1);
        System.out.println("Индекс первого вхождения элемента 8 в список arrayList1: " + arrayList1.indexOf(8));
        System.out.println("Индекс последнего вхождения элемента 64 в список arrayList1: " + arrayList1.lastIndexOf(64));
    }
}