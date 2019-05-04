package by.rppba.production.util.exception;

public class NotEnoughTimeException extends Exception {
    public NotEnoughTimeException() {
        super("Not enough time, please choose another plan date!");
    }
}
