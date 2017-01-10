package school.lemon.changerequest.java.banking;

/**
 * Created by lbrdev on 10.01.2017.
 * Project: exceptions.hw1
 */
public class TestBA {
    public static void main(String[] args) {
        BankAccountImpl bankAccount1 = new BankAccountImpl();

        bankAccount1.balance = 15;
        bankAccount1.rate = 10;
        bankAccount1.accountNumber = 1;
        bankAccount1.sum = 10;
        bankAccount1.newRate = 11;

        System.out.println("" + bankAccount1.toString());
        bankAccount1.withdraw(bankAccount1.sum);
        System.out.println("withdraw " + bankAccount1.balance );
        bankAccount1.addInterest();
        System.out.println("after addInterest" + bankAccount1.balance);
        bankAccount1.setRate(bankAccount1.newRate);
        bankAccount1.addInterest();
        System.out.println("addInterst" + bankAccount1.balance);


    }
}
