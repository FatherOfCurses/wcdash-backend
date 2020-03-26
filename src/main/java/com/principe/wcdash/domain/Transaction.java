package com.principe.wcdash.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

public class Transaction {

    String transIdFromDB;
    String claimnumber;
    String statustext;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")// 7
            LocalDateTime transactionCompleteTime;
    String exceptionReason2;
    int workTimeInSecs;
    String xmldata;

    public String getTransIdFromDB() {
        if (this.transIdFromDB == null) {
            return "";
        } else {
            return transIdFromDB;
        }
    }

    public void setTransIdFromDB(String transIdFromDB) {
        this.transIdFromDB = transIdFromDB;
    }

    public String getClaimnumber() {
        if (this.claimnumber == null) {
            return "";
        } else {
            return claimnumber;
        }
    }

    public void setClaimnumber(String claimnumber) {
        this.claimnumber = claimnumber;
    }

    public String getStatustext() {
        if (this.statustext == null) {
            return "";
        } else {
            return statustext;
        }
    }

    public void setStatustext(String statustext) {
        this.statustext = statustext;
    }

    public LocalDateTime getTransactionCompleteTime() {
        if (this.transactionCompleteTime == null) {
            return LocalDateTime.now();
        } else {
            return transactionCompleteTime;
        }
    }

    public void setTransactionCompleteTime(LocalDateTime transactionCompleteTime) {
        this.transactionCompleteTime = transactionCompleteTime;
    }

    public int getWorkTimeInSecs() {
        return workTimeInSecs;
    }

    public void setWorkTimeInSecs(int workTimeInSecs) {
        this.workTimeInSecs = workTimeInSecs;
    }

    public String getXmldata() {
        return xmldata;
    }

    public void setXmldata(String xmldata) {
        this.xmldata = xmldata;
    }

    public String getExceptionReason2() {
        if (this.exceptionReason2 == null) {
            return "";
        } else {
            return exceptionReason2;
        }
    }

    public void setExceptionReason2(String exceptionReason2) {
        this.exceptionReason2 = exceptionReason2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Transaction))
            return false;

        Transaction that = (Transaction) o;

        return new EqualsBuilder().append(getWorkTimeInSecs(), that.getWorkTimeInSecs()).append(getTransIdFromDB(), that.getTransIdFromDB()).append(getClaimnumber(), that.getClaimnumber()).append(getStatustext(), that.getStatustext()).append(getTransactionCompleteTime(), that.getTransactionCompleteTime()).append(getXmldata(), that.getXmldata()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getTransIdFromDB()).append(getClaimnumber()).append(getStatustext()).append(getTransactionCompleteTime()).append(getWorkTimeInSecs()).append(getXmldata()).toHashCode();
    }
}