package ru.academits.polyanskaya;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("inputArrayListHome.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                list.add(line);
            }

            System.out.println(list);
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти данный файл");
        }

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(4, 4, 1, 5, 7, 1, 3, 5, 4, 4, 4, 4));

        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
                i--;
            }
        }

        System.out.println(numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>();

        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.indexOf(numbers1.get(i)) >= i) {
                numbers2.add(numbers1.get(i));
            }
        }

        System.out.println(numbers2);
    }
}