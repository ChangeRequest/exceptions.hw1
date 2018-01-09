package school.lemon.changerequest.java.banking.impl;

import school.lemon.changerequest.java.banking.TransactionalBankAccount;

public class AccountTransactionManager {

    private TransactionalBankAccount bankAccount;

    /**
     * @param bankAccount banking account
     * @throws NullPointerException if {@code bankAccount == null}
     */
    public AccountTransactionManager(TransactionalBankAccount bankAccount) throws NullPointerException {
        if (bankAccount == null)
            throw new NullPointerException("bankAccount == null");
        this.bankAccount = bankAccount;
    }

    /**
     * Execute bunch of operations.
     *
     * @param operations array of account operations
     */
    public void execute(BankOperation[] operations) {
        try {
            executeOperations(operations);
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Execute bunch of operations in one transaction.
     * In case of any exception all previous operations should be reverted and original exception should be thrown.
     * {@code autoCommit} should be enabled in any case.
     *
     * @param operations array of account operations
     */
    public void executeInTransaction(BankOperation[] operations) throws IllegalArgumentException {
        try {
            bankAccount.commit();
            bankAccount.setAutoCommit(false);
            executeOperations(operations);
        } catch (IllegalArgumentException e) {
            bankAccount.revert();
            throw e;
        } finally {
            bankAccount.setAutoCommit(true);
        }
    }

    private void executeOperations(BankOperation[] operations) throws IllegalArgumentException {
        for (BankOperation operation : operations) {
            switch (operation.getType()) {
                case ADD_INTEREST: {
                    bankAccount.addInterest();
                    break;
                }
                case DEPOSIT: {
                    bankAccount.deposit(operation.getValue());
                    break;
                }
                case SET_RATE: {
                    bankAccount.setRate(operation.getValue());
                    break;
                }
                case WITHDRAW: {
                    bankAccount.withdraw(operation.getValue());
                    break;
                }
            }
        }
    }


}
