package by.rppba.production.util.exception;

public class WrongOrderException extends Exception {
    public WrongOrderException() {
        super("Plan doesn't contains this product");
    }
}
