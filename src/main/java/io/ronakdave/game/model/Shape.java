package io.ronakdave.game.model;

public enum Shape {
    ROCK,
    PAPER,
    SCISSORS;

    public static Shape getShape(String shape) {
        return Shape.valueOf(shape.toUpperCase());
    }
}
