package ru.DTF98.ewm.error;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> type, long id) {
        super(String.format("Не найден %s с id=%s", type.getSimpleName(), id));
    }

    public NotFoundException(String message) {
        super(message);
    }
}
