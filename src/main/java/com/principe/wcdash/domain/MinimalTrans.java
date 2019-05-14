package com.principe.wcdash.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "dbo", name = "bpaworkqueueitem")

public class MinimalTrans {
        @Id
        @Column(name = "id") // 1
                String transIdFromDB;

        @Column(name = "keyvalue") // 3
                String claimnumber;

        @Column(name = "status") // 4
                String statustext;

        @Column(name = "finished")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")// 7
                LocalDateTime transactionCompleteTime;

        @Column(name = "exceptionreasonvarchar") // 19
                String exceptionReason2;

        @Column(name = "worktime") // 11
                int workTimeInSecs;

        @Column(name = "data") // 23
                String xmldata;

        public String getTransIdFromDB() {
                if (this.transIdFromDB == null) {
                        return "";
                }
                else {return transIdFromDB;
                }
        }

        public void setTransIdFromDB(String transIdFromDB) {
                this.transIdFromDB = transIdFromDB;
        }

        public String getClaimnumber() {
                if(this.claimnumber==null) {
                        return "";
                } else {return claimnumber;
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

        @Override public boolean equals(Object o) {
                if (this == o)
                        return true;

                if (!(o instanceof MinimalTrans))
                        return false;

                MinimalTrans that = (MinimalTrans) o;

                return new EqualsBuilder().append(getWorkTimeInSecs(), that.getWorkTimeInSecs()).append(getTransIdFromDB(), that.getTransIdFromDB()).append(getClaimnumber(), that.getClaimnumber()).append(getStatustext(), that.getStatustext()).append(getTransactionCompleteTime(), that.getTransactionCompleteTime()).append(getXmldata(), that.getXmldata()).isEquals();
        }

        @Override public int hashCode() {
                return new HashCodeBuilder(17, 37).append(getTransIdFromDB()).append(getClaimnumber()).append(getStatustext()).append(getTransactionCompleteTime()).append(getWorkTimeInSecs()).append(getXmldata()).toHashCode();
        }
}