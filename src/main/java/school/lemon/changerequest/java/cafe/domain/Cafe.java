package school.lemon.changerequest.java.cafe.domain;

import java.util.Random;

import school.lemon.changerequest.java.cafe.exceptions.*;

public class Cafe {

    private final Random random = new Random();

    public void serve(Client client) throws RandomClientException {
        Drink drink = null;
        try {
            drink = generateCup();
            client.drinkCoffee(drink);
        } catch (IncorrectDrinkException e) {
            System.out.println(e.getMessage());
            serve(client);
        } catch (HighTemperatureException e) {
            System.out.println(e.getMessage());
            cool(drink);
        } catch (LowTemperatureException e) {
            System.out.println(e.getMessage());
            warm(drink);
        }
    }

    private Drink generateCup() {
        DrinkType[] drinkTypes = DrinkType.values();
        return new Drink(drinkTypes[random.nextInt(drinkTypes.length)], random.nextInt(100));
    }

    private void warm(Drink drink) {
        drink.setTemperature(Client.LOWEST_TEMPERATURE);
    }

    private void cool(Drink drink) {
        drink.setTemperature(Client.HIGHEST_TEMPERATURE);
    }
}
