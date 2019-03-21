package com.lmig.ci.rpa.wcdash.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class Customer {

        int indexNumber;

        String customerName;

        int exceptionCount;

        public String getCustomerName() {
                return customerName;
        }

        public void setCustomerName(String customerName) {
                this.customerName = customerName;
        }

        public int getExceptionCount() {
                return exceptionCount;
        }

        public void setExceptionCount(int exceptionCount) {
                this.exceptionCount = exceptionCount;
        }

        public int getIndexNumber() {
                return indexNumber;
        }

        public void setIndexNumber(int indexNumber) {
                this.indexNumber = indexNumber;
        }

        @Override public boolean equals(Object o) {
                if (this == o)
                        return true;

                if (!(o instanceof Customer))
                        return false;

                Customer customer = (Customer) o;

                return new EqualsBuilder().append(getExceptionCount(), customer.getExceptionCount()).append(getCustomerName(), customer.getCustomerName()).isEquals();
        }

        @Override public int hashCode() {
                return new HashCodeBuilder(17, 37).append(getCustomerName()).append(getExceptionCount()).toHashCode();
        }
}
