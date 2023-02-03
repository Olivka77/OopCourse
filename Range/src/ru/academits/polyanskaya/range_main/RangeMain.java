package ru.academits.polyanskaya.range_main;

import ru.academits.polyanskaya.range.Range;

import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(2, 6);

        System.out.printf("Длина диапазона от %.2f до %.2f: %.2f.%n", range1.getFrom(), range1.getTo(), range1.getLength());

        Range range2 = new Range(4, 8);

        range2.setFrom(5);
        range2.setTo(7);

        System.out.printf("Длина диапазона от %.2f до %.2f: %.2f.%n%n", range2.getFrom(), range2.getTo(), range2.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число:");
        double number = scanner.nextDouble();

        if (range1.isInside(number)) {
            System.out.printf("Данное число принадлежит диапазону от %.2f до %.2f.%n%n", range1.getFrom(), range1.getTo());
        } else {
            System.out.printf("Данное число не принадлежит диапазону от %.2f до %.2f.%n%n", range1.getFrom(), range1.getTo());
        }

        Range range3 = range1.getIntersection(range2);

        if (range3 == null) {
            System.out.printf("Диапазоны от %.2f до %.2f и от %.2f до %.2f не пересекаются.%n%n",
                    range1.getFrom(), range1.getTo(), range2.getFrom(), range2.getTo());
        } else {
            System.out.printf("Диапазоны от %.2f до %.2f и от %.2f до %.2f пересекаются на интервале от %.2f до %.2f.%n%n",
                    range1.getFrom(), range1.getTo(), range2.getFrom(), range2.getTo(), range3.getFrom(), range3.getTo());
        }

        Range[] range4 = range1.getUnion(range2);

        System.out.printf("Объединение диапазонов от %.2f до %.2f и от %.2f до %.2f: от %.2f до %.2f", range1.getFrom(),
                range1.getTo(), range2.getFrom(), range2.getTo(), range4[0].getFrom(), range4[0].getTo());

        if (range4.length > 1) {
            System.out.printf(" и от %.2f до %.2f", range4[1].getFrom(), range4[1].getTo());
        }

        Range[] range5 = range1.getDifference(range2);

        if (range5.length == 0) {
            System.out.printf(".%n%nРазность диапазонов от %.2f до %.2f и от %.2f до %.2f - пустое множество.%n%n",
                    range1.getFrom(), range1.getTo(), range2.getFrom(), range2.getTo());
        } else {
            System.out.printf(".%n%nРазность диапазонов от %.2f до %.2f и от %.2f до %.2f: от %.2f до %.2f", range1.getFrom(),
                    range1.getTo(), range2.getFrom(), range2.getTo(), range5[0].getFrom(), range5[0].getTo());

            if (range5.length > 1) {
                System.out.printf(" и от %.2f до %.2f.%n", range5[1].getFrom(), range5[1].getTo());
            }
        }
    }
}