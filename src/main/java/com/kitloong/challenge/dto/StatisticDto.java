package com.kitloong.challenge.dto;

import java.util.Objects;

/**
 * Copyright 2018 Allure Systems.
 * Created by Kit Loong on 2018/06/06.
 */
public class StatisticDto {
    public double sum;

    public double avg;

    public double max;

    public double min;

    public long count;

    public StatisticDto() {
    }

    public StatisticDto(double sum, double avg, double max, double min, long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticDto that = (StatisticDto) o;
        return Double.compare(that.sum, sum) == 0 &&
                Double.compare(that.avg, avg) == 0 &&
                Double.compare(that.max, max) == 0 &&
                Double.compare(that.min, min) == 0 &&
                count == that.count;
    }

    @Override
    public int hashCode() {

        return Objects.hash(sum, avg, max, min, count);
    }

    @Override
    public String toString() {
        return "StatisticDto{" +
                "sum=" + sum +
                ", avg=" + avg +
                ", max=" + max +
                ", min=" + min +
                ", count=" + count +
                '}';
    }
}
