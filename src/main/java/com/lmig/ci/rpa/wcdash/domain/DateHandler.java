package com.lmig.ci.rpa.wcdash.domain;

import java.time.LocalDate;

public class DateHandler {
        LocalDate startDateRange;
        LocalDate afterStartDate;
        LocalDate endDateRange;
        LocalDate beforeEndDate;
        int numberOfDays;

        public LocalDate getStartDateRange() {
                return startDateRange;
        }

        public void setStartDateRange(LocalDate startDateRange) {
                this.startDateRange = startDateRange;
        }

        public LocalDate getAfterStartDate() {
                return afterStartDate;
        }

        public void setAfterStartDate(LocalDate afterStartDate) {
                this.afterStartDate = afterStartDate;
        }

        public LocalDate getEndDateRange() {
                return endDateRange;
        }

        public void setEndDateRange(LocalDate endDateRange) {
                this.endDateRange = endDateRange;
        }

        public LocalDate getBeforeEndDate() {
                return beforeEndDate;
        }

        public void setBeforeEndDate(LocalDate beforeEndDate) {
                this.beforeEndDate = beforeEndDate;
        }

        public int getNumberOfDays() {
                return numberOfDays;
        }

        public void setNumberOfDays(int numberOfDays) {
                this.numberOfDays = numberOfDays;
        }
}
