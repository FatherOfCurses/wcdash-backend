package com.lmig.ci.rpa.wcdash.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SummaryData {

        //TODO: Maybe parse out month, date, day of week, week number here?
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")// 7
        LocalDate summaryDate;

        double dailyAverageWorkTime;

        long numberOfTransactions;

        BigDecimal percentOfTotal;

        public LocalDate getSummaryDate() {
                return summaryDate;
        }

        public void setSummaryDate(LocalDate summaryDate) {
                this.summaryDate = summaryDate;
        }

        public double getDailyAverageWorkTime() {
                return dailyAverageWorkTime;
        }

        public void setDailyAverageWorkTime(double dailyAverageWorkTime) {
                this.dailyAverageWorkTime = dailyAverageWorkTime;
        }

        public long getNumberOfTransactions() {
                return numberOfTransactions;
        }

        public void setNumberOfTransactions(long numberOfTransactions) {
                this.numberOfTransactions = numberOfTransactions;
        }

        public BigDecimal getPercentOfTotal() {
                return percentOfTotal;
        }

        public void setPercentOfTotal(BigDecimal percentOfTotal) {
                this.percentOfTotal = percentOfTotal;
        }

        @Override public boolean equals(Object o) {
                if (this == o)
                        return true;

                if (!(o instanceof SummaryData))
                        return false;

                SummaryData that = (SummaryData) o;

                return new EqualsBuilder().append(getDailyAverageWorkTime(), that.getDailyAverageWorkTime()).append(getSummaryDate(), that.getSummaryDate()).isEquals();
        }

        @Override public int hashCode() {
                return new HashCodeBuilder(17, 37).append(getSummaryDate()).append(getDailyAverageWorkTime()).toHashCode();
        }
}

