package br.evoluum.robot.error;

/**
 * @author Mychell Teixeira (mychellt@gmail.com)
 * @since 22/09/2020
 *
 */
public class AppException extends Exception {
    public AppException(String message) {
        super(message);
    }
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
