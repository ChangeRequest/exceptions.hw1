package school.lemon.changerequest.java.banking.impl;

import school.lemon.changerequest.java.banking.TransactionalBankAccount;

public class TransactionalBankAccountImpl extends BankAccountImpl implements TransactionalBankAccount {

    class BankAccountBuffer {
        double rate;
        double balance;

        BankAccountBuffer(TransactionalBankAccount obj) {
            rate = obj.getRate();
            balance = obj.getBalance();
        }
    }

    BankAccountBuffer latestCommit;
    private boolean autoCommit = true;


    public TransactionalBankAccountImpl() {
        this(0, BankAccountImpl.DEFAULT_RATE);
    }

    public TransactionalBankAccountImpl(double balance) {
        this(balance, BankAccountImpl.DEFAULT_RATE);
    }

    public TransactionalBankAccountImpl(double balance, double rate) {
        super(balance, rate);
        latestCommit = new BankAccountBuffer(this);
    }

    /**
     * Specified whether future operations should be in transaction.
     *
     * @param value, true - each operation executes independently.
     *               false - all operations before {@code commit()} can be reverted via {@code revert()}.
     */
    public void setAutoCommit(boolean value) {
        autoCommit = value;
    }

    /**
     * @return true - if auto commit enabled, false - otherwise.
     */
    public boolean isAutoCommitEnabled() {
        return autoCommit;
    }

    /**
     * Commits all uncommitted operations
     */
    public void commit() {
        if (!autoCommit) {
            latestCommit.balance = balance;
            latestCommit.rate = rate;
        }
    }

    /**
     * Revert all uncommitted operations
     */
    public void revert() {
        if (!autoCommit) {
            balance = latestCommit.balance;
            rate = latestCommit.rate;
        }
    }

    @Override
    public void addInterest() {
        super.addInterest();
        if (autoCommit)
            commit();
    }

    @Override
    public void deposit(double sum) {
        super.deposit(sum);
        if (autoCommit)
            commit();
    }

    @Override
    public void setRate(double rate) {
        super.setRate(rate);
        if (autoCommit)
            commit();
    }

    @Override
    public void withdraw(double sum) {
        super.withdraw(sum);
        if (autoCommit)
            commit();
    }

    void setLatestCommit(BankAccountBuffer newCommit) {
        latestCommit = newCommit;
    }

    BankAccountBuffer getLatestCommit() {
        return latestCommit;
    }
}
