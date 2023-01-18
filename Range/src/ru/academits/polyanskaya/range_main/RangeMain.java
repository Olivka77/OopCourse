package ru.academits.polyanskaya.range_main;

import ru.academits.polyanskaya.range.Range;

import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(-1, 8);
        Range range2 = new Range(15, 25.5);

        System.out.println("Длина диапазона от " + range1.getFrom() + " до " + range1.getTo() + ": " + range1.getLength());

        range2.setFrom(30);
        range2.setTo(50.1);

        System.out.println("Длина диапазона от " + range2.getFrom() + " до " + range2.getTo() + ": " + range2.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число:");
        double number = scanner.nextDouble();

        if (range1.isInside(number)) {
            System.out.println("Данное число принадлежит диапазону от " + range1.getFrom() + " до " + range1.getTo());
        } else {
            System.out.println("Данное число не принадлежит диапазону от " + range1.getFrom() + " до " + range1.getTo());
        }
    }
}