package ru.academits.polyanskaya;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("inputArrayListHome.txt"))) {
            ArrayList<String> lines = new ArrayList<>();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            System.out.println("Список на массиве из прочитанного файла \"inputArrayListHome.txt\": " + lines);
        } catch (FileNotFoundException e) {
            System.out.println("Указанный файл не найден");
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

        ArrayList<Integer> repeatedNumbersOriginalList = new ArrayList<>(Arrays.asList(1, 5, 7, 1, 1, 7, 3, 5, 1));
        ArrayList<Integer> uniqueNumbersResultList = new ArrayList<>(repeatedNumbersOriginalList.size());

        for (Integer integer : repeatedNumbersOriginalList) {
            if (!uniqueNumbersResultList.contains(integer)) {
                uniqueNumbersResultList.add(integer);
            }
        }

        System.out.println("Список на массиве после удаления дубликатов: " + uniqueNumbersResultList);
    }
}