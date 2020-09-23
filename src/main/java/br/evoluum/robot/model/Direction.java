package br.evoluum.robot.model;

public enum Direction {
    NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

    private final String code;

    Direction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isNorth() {
        return this.equals(NORTH);
    }

    public boolean isSouth() {
        return this.equals(SOUTH);
    }

    public boolean isEast() {
        return this.equals(EAST);
    }

    public boolean isWest() {
        return this.equals(WEST);
    }
}
