package com.example.squarearea.service.strategy;

import org.springframework.stereotype.Component;

@Component
public class RectangleAreaStrategy implements AreaStrategy {

    @Override
    public String shape() {
        return "rectangle";
    }

    @Override
    public double calculate(double... params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("Rectangle requires exactly 2 parameters: width and height");
        }
        double width = params[0];
        double height = params[1];
        validatePositive(width, "Rectangle width");
        validatePositive(height, "Rectangle height");
        return width * height;
    }
}
