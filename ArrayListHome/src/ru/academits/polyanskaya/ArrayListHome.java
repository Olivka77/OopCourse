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
            System.out.println("Не удается найти данный файл");
        }

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(4, 4, 1, 5, 7, 1, 3, 5, 4, 4, 4, 4));

        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
                i--;
            }
        }

        System.out.println("Список на массиве после удаления чётных чисел: " + numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(numbers1);

        for (int i = 0; i < numbers2.size(); i++) {
            if (numbers2.indexOf(numbers2.get(i)) < i) {
                numbers2.remove(i);
                i--;
            }
        }

        System.out.println("Список на массиве после удаления дубликатов: " + numbers2);
    }
}