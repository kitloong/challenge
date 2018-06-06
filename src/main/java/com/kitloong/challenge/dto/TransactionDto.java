package com.kitloong.challenge.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Copyright 2018 Allure Systems.
 * Created by Kit Loong on 2018/06/06.
 */
public class TransactionDto {

    @NotNull
    private double amount;

    @NotNull
    private long timestamp;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return Double.compare(that.amount, amount) == 0 &&
                timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, timestamp);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
