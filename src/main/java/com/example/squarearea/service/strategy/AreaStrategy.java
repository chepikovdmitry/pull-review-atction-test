package com.example.squarearea.service.strategy;

public interface AreaStrategy {

    String shape();

    double calculate(double... params);

    default void validatePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive number");
        }
    }
}
