package school.lemon.changerequest.java.cafe.domain;

import school.lemon.changerequest.java.cafe.exceptions.*;

import java.util.Random;

public class Client {

    public static final int LOWEST_TEMPERATURE = 30;
    public static final int HIGHEST_TEMPERATURE = 80;

    private final Random random = new Random();

    public void drinkCoffee(Drink drink) throws IncorrectDrinkException, LowTemperatureException,
            HighTemperatureException, RandomClientException {
        if (drink.getDrinkType() != DrinkType.COFFEE) {
            throw new IncorrectDrinkException("Tastes like a " + drink.getDrinkType());
        } else if (drink.getTemperature() < LOWEST_TEMPERATURE) {
            throw new LowTemperatureException("Looks like it's too cold...", drink.getTemperature());
        } else if (drink.getTemperature() > HIGHEST_TEMPERATURE) {
            throw new HighTemperatureException("Looks like it's too hot...", drink.getTemperature());
        } else if (random.nextInt(100) < 10) {
            throw new RandomClientException("I don't like your Cafe!");
        } else {
            System.out.println("Delicious, thank you.");
        }
    }

}
