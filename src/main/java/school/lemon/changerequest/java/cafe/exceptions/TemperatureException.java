package school.lemon.changerequest.java.cafe.exceptions;

public class TemperatureException extends ClientException
{
    private int temperature;

    public TemperatureException(String message, int temperature) {
        super(message);
        this.temperature = temperature;
    }

    int getTemperature() {
        return temperature;
    }
}
