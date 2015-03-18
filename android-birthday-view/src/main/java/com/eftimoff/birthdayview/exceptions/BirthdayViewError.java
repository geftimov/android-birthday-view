package com.eftimoff.birthdayview.exceptions;

public class BirthdayViewError {

    private Exception exception;
    private Type type;

    public BirthdayViewError(final Exception exception, final Type type) {
        this.exception = exception;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public enum Type {
        ILLEGAL, NUMBER
    }
}
