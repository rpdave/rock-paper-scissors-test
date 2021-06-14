package io.ronakdave.game.model;

public enum Shape {
    ROCK,
    PAPER,
    SCISSORS;

    public static Shape getShape(String shape) {
        try {
            return Shape.valueOf(shape.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid shape: " + shape);
        }
    }
}
