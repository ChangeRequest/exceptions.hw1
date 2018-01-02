package school.lemon.changerequest.java.banking.impl;

import school.lemon.changerequest.java.banking.TransactionalBankAccount;

public class TransactionalBankAccountImpl extends BankAccountImpl implements TransactionalBankAccount {

    private BankAccountImpl latestCommit;
    private boolean autoCommit = true;


    public TransactionalBankAccountImpl() {
        this(0, 0);
    }

    public TransactionalBankAccountImpl(double balance) {
        this(balance, 0.01);
    }

    public TransactionalBankAccountImpl(double balance, double rate) {
        super(balance, rate);
        latestCommit = new BankAccountImpl(this);
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
        latestCommit.balance = balance;
        latestCommit.rate = rate;
    }

    /**
     * Revert all uncommitted operations
     */
    public void revert() {
        balance = latestCommit.balance;
        rate = latestCommit.rate;
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
}
