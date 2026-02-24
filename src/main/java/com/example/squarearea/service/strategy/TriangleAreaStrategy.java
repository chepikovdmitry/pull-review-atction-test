package com.example.squarearea.service.strategy;

import org.springframework.stereotype.Component;

@Component
public class TriangleAreaStrategy implements AreaStrategy {

    @Override
    public String shape() {
        return "triangle";
    }

    @Override
    public double calculate(double... params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("Triangle requires exactly 2 parameters: base and height");
        }
        double base = params[0];
        double height = params[1];
        validatePositive(base, "Triangle base");
        validatePositive(height, "Triangle height");
        return (base * height) / 2.0;
    }
}
