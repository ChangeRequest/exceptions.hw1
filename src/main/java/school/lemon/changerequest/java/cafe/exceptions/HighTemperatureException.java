package school.lemon.changerequest.java.cafe.exceptions;

public class HighTemperatureException extends TemperatureException {

    public HighTemperatureException(String message, int temperature) {
        super(message, temperature);
    }
}
