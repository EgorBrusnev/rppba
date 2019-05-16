package by.rppba.production.util.exception;

public class TooMuchProductsException extends Exception {
    public TooMuchProductsException() {
        super("Too much products for plan");
    }
}
