package com.kitloong.challenge.entity;

/**
 * Created by Kit Loong on 10/6/2018.
 */
public class CollectedTransaction {
    private long totalCollected;

    private double totalAmount;

    private double min;

    private double max;

    public CollectedTransaction() {
    }

    public CollectedTransaction(long totalCollected, double totalAmount, double min, double max) {
        this.totalCollected = totalCollected;
        this.totalAmount = totalAmount;
        this.min = min;
        this.max = max;
    }

    public long getTotalCollected() {
        return totalCollected;
    }

    public void setTotalCollected(long totalCollected) {
        this.totalCollected = totalCollected;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectedTransaction that = (CollectedTransaction) o;

        if (totalCollected != that.totalCollected) return false;
        if (Double.compare(that.totalAmount, totalAmount) != 0) return false;
        if (Double.compare(that.min, min) != 0) return false;
        return Double.compare(that.max, max) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (totalCollected ^ (totalCollected >>> 32));
        temp = Double.doubleToLongBits(totalAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(min);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(max);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CollectedTransaction{" +
                "totalCollected=" + totalCollected +
                ", totalAmount=" + totalAmount +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
