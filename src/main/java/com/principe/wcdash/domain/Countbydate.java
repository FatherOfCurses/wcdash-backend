package com.principe.wcdash.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;

public class Countbydate {
    LocalDate transactionDate;
    int transactionCount;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Countbydate))
            return false;

        Countbydate that = (Countbydate) o;

        return new EqualsBuilder().append(getTransactionCount(), that.getTransactionCount()).append(getTransactionDate(), that.getTransactionDate()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getTransactionDate()).append(getTransactionCount()).toHashCode();
    }
}
