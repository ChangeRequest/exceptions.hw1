package school.lemon.changerequest.java.cafe.exceptions;

public class LowTemperatureException extends ClientException {

    private int temperature;

    public LowTemperatureException(String message, int temperature) {
        super(message);
        this.temperature = temperature;
    }

    int getTemperature() {
        return temperature;
    }
}
