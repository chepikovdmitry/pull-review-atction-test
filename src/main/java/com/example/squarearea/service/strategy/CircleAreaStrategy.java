package com.example.squarearea.service.strategy;

import org.springframework.stereotype.Component;

@Component
public class CircleAreaStrategy implements AreaStrategy {

    @Override
    public String shape() {
        return "circle";
    }

    @Override
    public double calculate(double... params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Circle requires exactly 1 parameter: radius");
        }
        double radius = params[0];
        validatePositive(radius, "Circle radius");
        return Math.PI * radius * radius;
    }
}
