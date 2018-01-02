package school.lemon.changerequest.java.cafe.exceptions;

public class HighTemperatureException extends ClientException {

    private int temperature;

    public HighTemperatureException(String message, int temperature) {
        super(message);
        this.temperature = temperature;
    }

    int getTemperature() {
        return temperature;
    }
}
