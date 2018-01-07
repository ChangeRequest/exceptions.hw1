package school.lemon.changerequest.java.banking.impl;

import school.lemon.changerequest.java.banking.BankAccount;

public class BankAccountImpl implements BankAccount {

    protected static final double DEFAULT_RATE = 0.01;
    private static int numberOfAccounts = 0;

    protected double balance;
    protected double rate;
    protected final int accountNumber;

    public BankAccountImpl() {
        this(0, DEFAULT_RATE);
    }

    public BankAccountImpl(double balance) {
        this(balance, DEFAULT_RATE);
    }

    public BankAccountImpl(double balance, double rate) throws IllegalArgumentException {
        if (balance < 0 || rate < 0)
            throw new IllegalArgumentException("Balance < 0 or(and) rate < 0");
        this.balance = balance;
        this.rate = rate;
        accountNumber = numberOfAccounts++;
    }

    /**
     * Get account number
     *
     * @return account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Get current balance
     *
     * @return account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Get current rate
     *
     * @return account rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Set current account rate in percents
     *
     * @param rate value
     */
    public void setRate(double rate) throws IllegalArgumentException {
        if (rate < 0)
            throw new IllegalArgumentException("Rate < 0");
        this.rate = rate;
    }

    /**
     * Withdraw specified sum from account
     *
     * @param sum to withdraw
     * @throws IllegalArgumentException for {@code sum < 0 || sum > balance}
     */
    public void withdraw(double sum) throws IllegalArgumentException {
        if (sum < 0 || sum > balance)
            throw new IllegalArgumentException("Sum < 0 or sum > balance");
        balance -= sum;
    }

    /**
     * Deposit specified sum for account
     *
     * @param sum to deposit
     * @throws IllegalArgumentException for {@code sum < 0}
     */
    public void deposit(double sum) throws IllegalArgumentException {
        if (sum < 0)
            throw new IllegalArgumentException("Sum < 0");
        balance += sum;
    }

    /**
     * Add interest for 1 year to balance.
     * E.g.: rate = 10% and balance = 200$ -> interest = 20$. So, new balance is 120$.
     */
    public void addInterest() {
        balance += balance * rate;
    }


    /**
     * @return account information in the following format: Account #123, ($10.32).
     */
    public String toString() {
        String result = String.format("Account #%d, ($%.2f)", accountNumber, balance);
        return result;
    }
}
