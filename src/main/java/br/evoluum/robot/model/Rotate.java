package br.evoluum.robot.model;

/**
 * @author Mychell Teixeira (mychellt@gmail.com)
 * @since 22/09/2020
 */
public enum Rotate {
    L("L", "LEFT"), R("R", "RIGHT");

    private final String code;
    private final String description;

    Rotate(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRight() {
        return this.equals(L);
    }
}
