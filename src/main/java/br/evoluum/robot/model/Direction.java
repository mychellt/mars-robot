package br.evoluum.robot.model;

/**
 * @author Mychell Teixeira (mychellt@gmail.com)
 * @since 22/09/2020
 */
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
