package com.example.squarearea.service;

import com.example.squarearea.service.strategy.AreaStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SquareService {

    private final Map<String, AreaStrategy> strategies;

    public SquareService(List<AreaStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(AreaStrategy::shape, Function.identity()));
    }

    public double calculateSquareArea(double side) {
        return calculate("square", side);
    }

    public double calculateRectangleArea(double width, double height) {
        return calculate("rectangle", width, height);
    }

    public double calculateCircleArea(double radius) {
        return calculate("circle", radius);
    }

    public double calculateTriangleArea(double base, double height) {
        return calculate("triangle", base, height);
    }

    private double calculate(String shape, double... params) {
        AreaStrategy strategy = strategies.get(shape);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported shape: " + shape);
        }
        return strategy.calculate(params);
    }
}
