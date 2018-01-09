package school.lemon.changerequest.java.cafe.exceptions;

public class LowTemperatureException extends TemperatureException {

    public LowTemperatureException(String message, int temperature) {
        super(message, temperature);
    }
}
