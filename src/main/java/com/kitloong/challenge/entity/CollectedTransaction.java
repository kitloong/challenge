package com.kitloong.challenge.entity;

/**
 * Created by Kit Loong on 10/6/2018.
 */
public class CollectedTransaction {
    private long totalCollected;

    private double totalAmount;

    public CollectedTransaction() {
    }

    public CollectedTransaction(long totalCollected, double totalAmount) {
        this.totalCollected = totalCollected;
        this.totalAmount = totalAmount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectedTransaction that = (CollectedTransaction) o;

        if (totalCollected != that.totalCollected) return false;
        return Double.compare(that.totalAmount, totalAmount) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (totalCollected ^ (totalCollected >>> 32));
        temp = Double.doubleToLongBits(totalAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CollectedTransaction{" +
                "totalCollected=" + totalCollected +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
