package school.lemon.changerequest.java.banking.impl

import spock.lang.Specification

import static school.lemon.changerequest.java.banking.impl.OperationType.*

class TestTransactionManager extends Specification {

    def "Test execute non transactional operations"() {
        given:
        def account = AccountFactory.createTransactionalBankAccount()
        def transactionManager = new AccountTransactionManager(account)
        BankOperation[] operations = [
                new BankOperation(DEPOSIT, 100),
                new BankOperation(SET_RATE, 10),
                new BankOperation(ADD_INTEREST),
                new BankOperation(WITHDRAW, 100),
        ];

        when:
        transactionManager.execute(operations)

        then:
        account.getBalance() == 10
        account.getRate() == 10
        account.isAutoCommitEnabled()
    }

    def "Test execute transactional operations"() {
        given:
        def account = AccountFactory.createTransactionalBankAccount()
        def transactionManager = new AccountTransactionManager(account)
        BankOperation[] operations = [
                new BankOperation(DEPOSIT, 100),
                new BankOperation(SET_RATE, 10),
                new BankOperation(ADD_INTEREST),
                new BankOperation(WITHDRAW, 100),
        ];

        when:
        transactionManager.executeInTransaction(operations)

        then:
        account.getBalance() == 10
        account.getRate() == 10
        account.isAutoCommitEnabled()
    }

    def "Test execute transactional operations with exception"() {
        given:
        def account = AccountFactory.createTransactionalBankAccount()
        account.setRate(5)
        account.deposit(100)

        def transactionManager = new AccountTransactionManager(account)
        BankOperation[] operations = [
                new BankOperation(DEPOSIT, 10),
                new BankOperation(SET_RATE, 10),
                new BankOperation(ADD_INTEREST),
                new BankOperation(WITHDRAW, 100),
                new BankOperation(WITHDRAW, 100)
        ];

        when:
        transactionManager.executeInTransaction(operations)

        then:
        thrown(IllegalArgumentException)
        account.getBalance() == 100
        account.getRate() == 5
        account.isAutoCommitEnabled()
    }

}
