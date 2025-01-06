package com.sd.stockmanagementsystem.application.dto.core;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;


@Data
@Builder

public class AttributeKeyValue {
    private final String attributeName;
    private final String value;

    public AttributeKeyValue(String attributeName, String value) {
        this.attributeName = attributeName;
        this.value = value;
    }

    // Add equals and hashCode for proper functionality in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeKeyValue that = (AttributeKeyValue) o;
        return Objects.equals(attributeName, that.attributeName) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName, value);
    }

    @Override
    public String toString() {
        return "AttributeKeyValue{" +
                "attributeName='" + attributeName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}