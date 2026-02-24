package com.example.squarearea.service.strategy;

import org.springframework.stereotype.Component;

@Component
public class SquareAreaStrategy implements AreaStrategy {

    @Override
    public String shape() {
        return "square";
    }

    @Override
    public double calculate(double... params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Square requires exactly 1 parameter: side");
        }
        double side = params[0];
        validatePositive(side, "Square side");
        return side * side;
    }
}
