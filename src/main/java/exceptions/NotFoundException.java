package exceptions;

public class NotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Element not found!";
    }
}
