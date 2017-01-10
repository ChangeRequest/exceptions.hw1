package school.lemon.changerequest.java.banking;

/**
 * Created by lbrdev on 10.01.2017.
 * Project: exceptions.hw1
 */
public class BankAccountImpl implements BankAccount {

    double balance;
    double rate;
    int accountNumber;
    double sum;
    double newRate;

    public BankAccountImpl() {
        this.balance = 0;
        this.rate = 0;
        this.accountNumber = 0;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getRate() {
        return rate;
    }

    @Override
    public void setRate(double rate) {
        rate = newRate;
    }

    @Override
    public void withdraw(double sum) throws IllegalArgumentException {
        if (balance < sum) {
            throw new IllegalArgumentException("Not enough money for withdrawal");
        }
        balance = getBalance() - sum;
    }

    @Override
    public void deposit(double sum) throws IllegalArgumentException {
        balance = getBalance() + sum;
    }

    @Override
    public void addInterest() {
        balance = getBalance() + getBalance() * getRate();
    }

    @Override
    public String toString() {
        return ("Account#"
                + getAccountNumber()
                + ", ("
                + "$" + getBalance() + ")");
    }
}
