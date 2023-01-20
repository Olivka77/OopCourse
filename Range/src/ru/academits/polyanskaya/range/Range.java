package ru.academits.polyanskaya.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (range.from == from && range.to == to) {
            return range;
        }

        if (range.from < to && range.to > from || range.from > to && range.to < from) {
            return new Range(Math.max(range.from, from), Math.min(range.to, to));
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (range.from == from && range.to == to) {
            Range[] rangeArray = new Range[1];

            rangeArray[0] = range;

            return rangeArray;
        }

        if (range.from <= to && range.to >= from || range.from >= to && range.to <= from) {
            Range[] rangeArray = new Range[1];

            rangeArray[0] = new Range(Math.min(range.from, from), Math.max(range.to, to));

            return rangeArray;
        }

        Range[] rangesArray = new Range[2];

        rangesArray[0] = new Range(from, to);
        rangesArray[1] = new Range(range.from, range.to);

        return rangesArray;
    }

    public Range[] getDifference(Range range) {
        if (range.from == from && range.to == to || from >= range.from && to <= range.to) {
            return null;
        }

        if (from < range.to && to < range.from || from > range.to && to > range.from) {
            Range[] rangeArray = new Range[1];

            rangeArray[0] = new Range(from, to);

            return rangeArray;
        }

        if (from < range.from && to > range.to) {
            Range[] rangesArray = new Range[2];

            rangesArray[0] = new Range(from, range.from);
            rangesArray[1] = new Range(range.to, to);

            return rangesArray;
        }

        if (range.from <= from) {
            Range[] rangeArray = new Range[1];

            rangeArray[0] = new Range(range.to, to);

            return rangeArray;
        }

        Range[] rangeArray = new Range[1];

        rangeArray[0] = new Range(from, range.from);

        return rangeArray;
    }
}