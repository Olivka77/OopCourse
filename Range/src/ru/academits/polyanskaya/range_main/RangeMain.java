package ru.academits.polyanskaya.range_main;

import ru.academits.polyanskaya.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(5, 7);

        System.out.println("Длина диапазона " + range1 + ": " + range1.getLength());

        Range range2 = new Range(4, 8);

        range2.setFrom(7);
        range2.setTo(9);

        System.out.println("Значение поля from объекта range2 после изменения: " + range2.getFrom());
        System.out.println("Значение поля to объекта range2 после изменения: " + range2.getTo());
        System.out.println("Длина диапазона " + range2 + ": " + range2.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число:");
        double number = scanner.nextDouble();

        if (range1.isInside(number)) {
            System.out.println("Данное число принадлежит диапазону " + range1);
        } else {
            System.out.println("Данное число не принадлежит диапазону " + range1);
        }

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Диапазоны " + range1 + " и " + range2 + " не пересекаются.");
        } else {
            System.out.println("Диапазоны " + range1 + " и " + range2 + " пересекаются на интервале " + intersection);
        }

        Range[] union = range1.getUnion(range2);
        System.out.println("Объединение диапазонов " + range1 + " и " + range2 + ": " + Arrays.toString(union));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Разность диапазонов " + range1 + " и " + range2 + ": " + Arrays.toString(difference));
    }
}