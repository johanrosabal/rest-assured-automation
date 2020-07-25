package com.blacksystem.automation.application.exceptions;
/**
 * Implementation for unchecked exceptions in the framework
 *
 */
public class AutomationRuntimeException extends RuntimeException{

    /**
     * Auto-generated serialVersionUID
     */
    private static final long serialVersionUID = 6124153116432951527L;

    /**
     * Constructs a new exception with the specified detail message
     *
     * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method
     */
    public AutomationRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of "(cause==null ? null : cause.toString())"
     *
     * @param  cause the cause (which is saved for later retrieval by the {@link #getCause()} method)
     */
    public AutomationRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method)
     */
    public AutomationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
