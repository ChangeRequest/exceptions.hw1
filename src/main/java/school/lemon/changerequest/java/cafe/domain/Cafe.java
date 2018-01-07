package school.lemon.changerequest.java.cafe.domain;

import java.util.Random;

import school.lemon.changerequest.java.cafe.exceptions.*;

public class Cafe {

    private final Random random = new Random();

    public void serve(Client client) throws UnsatisfiedClientException {
        Drink drink = generateCup();
        try {
            client.drinkCoffee(drink);
        } catch (IncorrectDrinkException e) {
            System.out.println(e.getMessage());
            bringCoffee(drink);
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

    private void bringCoffee(Drink drink) {
        drink.setDrinkType(DrinkType.COFFEE);
    }
}
