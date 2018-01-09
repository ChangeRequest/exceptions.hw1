package school.lemon.changerequest.java.banking.impl;

import school.lemon.changerequest.java.banking.BankAccount;
import school.lemon.changerequest.java.banking.TransactionalBankAccount;

public final class AccountFactory {

    private AccountFactory() {
    }

    /**
     * Creates bank account
     *
     * @return bank account
     */
    public static BankAccount createBankAccount() {
        return new BankAccountImpl();
    }

    /**
     * Creates transactional bank account
     *
     * @return transactional bank account
     */
    public static TransactionalBankAccount createTransactionalBankAccount() {
        return new TransactionalBankAccountImpl();
    }
}
