package ru.academits.polyanskaya;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("inputArrayListHome.txt"))) {
            ArrayList<String> arrayList = new ArrayList<>();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }

            System.out.println("Список на массиве из прочитанного файла \"inputArrayListHome.txt\": " + arrayList);
        } catch (IOException e) {
            System.out.println("Ошибка доступа к указанному файлу");
        }

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(4, 4, 1, 5, 7, 1, 3, 5, 4, 4, 4, 4));

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }

        System.out.println("Список на массиве после удаления чётных чисел: " + numbers);

        ArrayList<Integer> numbersOriginalList = new ArrayList<>(Arrays.asList(1, 5, 7, 1, 1, 7, 3, 5, 1));
        ArrayList<Integer> numbersResultList = new ArrayList<>(numbersOriginalList.size());

        for (int i = 0; i < numbersOriginalList.size(); i++) {
            if (numbersOriginalList.indexOf(numbersOriginalList.get(i)) == i) {
                numbersResultList.add(numbersOriginalList.get(i));
            }
        }

        System.out.println("Список на массиве после удаления дубликатов: " + numbersResultList);
    }
}