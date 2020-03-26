package hachi.hachishop.exception;

public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException(String msg) {
        super(msg);
    }
}
