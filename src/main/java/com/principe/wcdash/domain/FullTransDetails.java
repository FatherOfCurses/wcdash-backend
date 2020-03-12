package com.principe.wcdash.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

public class FullTransDetails {

    String transIdFromDB;
    String queueid; // 2
    String claimnumber;
    String statustext;
    int attempt; // 5
    // @JsonFormat annotation allows formatting of date.  Otherwise date comes out as a number of sub-objects separated by commas
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")// 7
            LocalDateTime transactionLoadTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")// 7
            LocalDateTime transactionCompleteTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")// 7
            LocalDateTime transactionExceptionTime;
    String exceptionreason; // 9
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")// 7
            LocalDateTime transactionDeferTime;
    int workTimeInSecs;
    int queueident; // 12
    String ident;
    String sessionId;
    int priority; // 15
    int previousWorkTimeInSecs;
    int attemptedWorkTimeInSecs;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime transactionFinishTime;
    String exceptionReason2;
    String exceptionTag;
    String encryptionId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")// 22
            LocalDateTime lastTransactionUpdateTime;
    String xmldata;

    public String getTransIdFromDB() {
        return transIdFromDB;
    }

    public void setTransIdFromDB(String transIdFromDB) {
        this.transIdFromDB = transIdFromDB;
    }

    public String getQueueid() {
        return queueid;
    }

    public void setQueueid(String queueid) {
        this.queueid = queueid;
    }

    public String getClaimnumber() {
        return claimnumber;
    }

    public void setClaimnumber(String claimnumber) {
        this.claimnumber = claimnumber;
    }

    public String getStatustext() {
        return statustext;
    }

    public void setStatustext(String statustext) {
        this.statustext = statustext;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public LocalDateTime getTransactionLoadTime() {
        return transactionLoadTime;
    }

    public void setTransactionLoadTime(LocalDateTime transactionLoadTime) {
        this.transactionLoadTime = transactionLoadTime;
    }

    public LocalDateTime getTransactionCompleteTime() {
        return transactionCompleteTime;
    }

    public void setTransactionCompleteTime(LocalDateTime transactionCompleteTime) {
        this.transactionCompleteTime = transactionCompleteTime;
    }

    public LocalDateTime getTransactionExceptionTime() {
        return transactionExceptionTime;
    }

    public void setTransactionExceptionTime(LocalDateTime transactionExceptionTime) {
        this.transactionExceptionTime = transactionExceptionTime;
    }

    public String getExceptionreason() {
        return exceptionreason;
    }

    public void setExceptionreason(String exceptionreason) {
        this.exceptionreason = exceptionreason;
    }

    public LocalDateTime getTransactionDeferTime() {
        return transactionDeferTime;
    }

    public void setTransactionDeferTime(LocalDateTime transactionDeferTime) {
        this.transactionDeferTime = transactionDeferTime;
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

    public int getQueueident() {
        return queueident;
    }

    public void setQueueident(int queueident) {
        this.queueident = queueident;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPreviousWorkTimeInSecs() {
        return previousWorkTimeInSecs;
    }

    public void setPreviousWorkTimeInSecs(int previousWorkTimeInSecs) {
        this.previousWorkTimeInSecs = previousWorkTimeInSecs;
    }

    public int getAttemptedWorkTimeInSecs() {
        return attemptedWorkTimeInSecs;
    }

    public void setAttemptedWorkTimeInSecs(int attemptedWorkTimeInSecs) {
        this.attemptedWorkTimeInSecs = attemptedWorkTimeInSecs;
    }

    public LocalDateTime getTransactionFinishTime() {
        return transactionFinishTime;
    }

    public void setTransactionFinishTime(LocalDateTime transactionFinishTime) {
        this.transactionFinishTime = transactionFinishTime;
    }

    public String getExceptionReason2() {
        return exceptionReason2;
    }

    public void setExceptionReason2(String exceptionReason2) {
        this.exceptionReason2 = exceptionReason2;
    }

    public String getExceptionTag() {
        return exceptionTag;
    }

    public void setExceptionTag(String exceptionTag) {
        this.exceptionTag = exceptionTag;
    }

    public String getEncryptionId() {
        return encryptionId;
    }

    public void setEncryptionId(String encryptionId) {
        this.encryptionId = encryptionId;
    }

    public LocalDateTime getLastTransactionUpdateTime() {
        return lastTransactionUpdateTime;
    }

    public void setLastTransactionUpdateTime(LocalDateTime lastTransactionUpdateTime) {
        this.lastTransactionUpdateTime = lastTransactionUpdateTime;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FullTransDetails other = (FullTransDetails) obj;
        if (this.attempt != other.attempt) {
            return false;
        }
        if (this.workTimeInSecs != other.workTimeInSecs) {
            return false;
        }
        if (this.queueident != other.queueident) {
            return false;
        }
        if (this.priority != other.priority) {
            return false;
        }
        if (this.previousWorkTimeInSecs != other.previousWorkTimeInSecs) {
            return false;
        }
        if (this.attemptedWorkTimeInSecs != other.attemptedWorkTimeInSecs) {
            return false;
        }
        if (!Objects.equals(this.transIdFromDB, other.transIdFromDB)) {
            return false;
        }
        if (!Objects.equals(this.queueid, other.queueid)) {
            return false;
        }
        if (!Objects.equals(this.claimnumber, other.claimnumber)) {
            return false;
        }
        if (!Objects.equals(this.statustext, other.statustext)) {
            return false;
        }
        if (!Objects.equals(this.exceptionreason, other.exceptionreason)) {
            return false;
        }
        if (!Objects.equals(this.xmldata, other.xmldata)) {
            return false;
        }
        if (!Objects.equals(this.ident, other.ident)) {
            return false;
        }
        if (!Objects.equals(this.sessionId, other.sessionId)) {
            return false;
        }
        if (!Objects.equals(this.exceptionReason2, other.exceptionReason2)) {
            return false;
        }
        if (!Objects.equals(this.exceptionTag, other.exceptionTag)) {
            return false;
        }
        if (!Objects.equals(this.encryptionId, other.encryptionId)) {
            return false;
        }
        if (!Objects.equals(this.transactionLoadTime, other.transactionLoadTime)) {
            return false;
        }
        if (!Objects.equals(this.transactionCompleteTime, other.transactionCompleteTime)) {
            return false;
        }
        if (!Objects.equals(this.transactionExceptionTime, other.transactionExceptionTime)) {
            return false;
        }
        if (!Objects.equals(this.transactionDeferTime, other.transactionDeferTime)) {
            return false;
        }
        if (!Objects.equals(this.transactionFinishTime, other.transactionFinishTime)) {
            return false;
        }
        if (!Objects.equals(this.lastTransactionUpdateTime, other.lastTransactionUpdateTime)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
