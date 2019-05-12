package com.principe.wcdash.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TopTen {
        int indexNumber;
        String elementName;
        int elementCount;

        public String getElementName() {
                return elementName;
        }

        public void setElementName(String elementName) {
                this.elementName = elementName;
        }

        public int getElementCount() {
                return elementCount;
        }

        public void setElementCount(int elementCount) {
                this.elementCount = elementCount;
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

                if (!(o instanceof TopTen))
                        return false;

                TopTen topTen = (TopTen) o;

                return new EqualsBuilder().append(getElementCount(), topTen.getElementCount()).append(getElementName(), topTen.getElementName()).isEquals();
        }

        @Override public int hashCode() {
                return new HashCodeBuilder(17, 37).append(getElementName()).append(getElementCount()).toHashCode();
        }
}
