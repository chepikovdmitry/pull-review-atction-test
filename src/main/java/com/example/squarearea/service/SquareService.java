package com.example.squarearea.service;

import org.springframework.stereotype.Service;

@Service
public class SquareService {

    public double calculateArea(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Square side must be a positive number");
        }
        return side * side;
    }
}
