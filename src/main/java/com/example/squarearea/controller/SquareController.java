package com.example.squarearea.controller;

import com.example.squarearea.service.SquareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/area")
public class SquareController {

    private final SquareService squareService;

    public SquareController(SquareService squareService) {
        this.squareService = squareService;
    }

    @GetMapping("/square")
    public ResponseEntity<AreaResponse> calculateSquareArea(@RequestParam double side) {
        try {
            double area = squareService.calculateSquareArea(side);
            return ResponseEntity.ok(new AreaResponse("square", area, "Area calculated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new AreaResponse("square", 0, e.getMessage()));
        }
    }

    @GetMapping("/rectangle")
    public ResponseEntity<AreaResponse> calculateRectangleArea(@RequestParam double width, @RequestParam double height) {
        try {
            double area = squareService.calculateRectangleArea(width, height);
            return ResponseEntity.ok(new AreaResponse("rectangle", area, "Area calculated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new AreaResponse("rectangle", 0, e.getMessage()));
        }
    }

    @GetMapping("/circle")
    public ResponseEntity<AreaResponse> calculateCircleArea(@RequestParam double radius) {
        try {
            double area = squareService.calculateCircleArea(radius);
            return ResponseEntity.ok(new AreaResponse("circle", area, "Area calculated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new AreaResponse("circle", 0, e.getMessage()));
        }
    }

    @GetMapping("/triangle")
    public ResponseEntity<AreaResponse> calculateTriangleArea(@RequestParam double base, @RequestParam double height) {
        try {
            double area = squareService.calculateTriangleArea(base, height);
            return ResponseEntity.ok(new AreaResponse("triangle", area, "Area calculated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new AreaResponse("triangle", 0, e.getMessage()));
        }
    }

    public static class AreaResponse {
        private String shape;
        private double area;
        private String message;

        public AreaResponse(String shape, double area, String message) {
            this.shape = shape;
            this.area = area;
            this.message = message;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
