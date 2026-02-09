package com.example.squarearea.controller;

import com.example.squarearea.service.SquareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/square")
public class SquareController {

    private final SquareService squareService;

    public SquareController(SquareService squareService) {
        this.squareService = squareService;
    }

    @GetMapping("/area")
    public ResponseEntity<AreaResponse> calculateArea(@RequestParam double side) {
        try {
            double area = squareService.calculateArea(side);
            return ResponseEntity.ok(new AreaResponse(area, "Square area calculated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new AreaResponse(0, e.getMessage()));
        }
    }

    public static class AreaResponse {
        private double area;
        private String message;

        public AreaResponse(double area, String message) {
            this.area = area;
            this.message = message;
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
