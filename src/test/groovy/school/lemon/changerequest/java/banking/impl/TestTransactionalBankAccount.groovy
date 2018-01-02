package school.lemon.changerequest.java.banking.impl

import school.lemon.changerequest.java.banking.TransactionalBankAccount

class TestTransactionalBankAccount extends TestBankAccount {

    protected TransactionalBankAccount getBankAccount() {
        return AccountFactory.createTransactionalBankAccount()
    }

    def "Test revert operation"() {
        given:
        def account = getBankAccount();
        account.deposit(100);
        account.setRate(10)
        account.setAutoCommit(false)

        when:
        account.withdraw(50)
        account.deposit(25)
        account.setRate(5)
        account.addInterest()
        account.revert()

        then:
        account.getBalance() == 100
        account.getRate() == 10
    }

    def "Test commit operation"() {
        given:
        def account = getBankAccount();
        account.deposit(100);
        account.setRate(10)
        account.setAutoCommit(false)

        when:
        account.setRate(5)
        account.addInterest() // 105
        account.withdraw(50) // 55
        account.deposit(25) // 80
        account.commit()

        then:
        account.getBalance() == 80
        account.getRate() == 5
    }

    def "Test uncommited changes"() {
        given:
        def account = getBankAccount();
        account.deposit(100);
        account.setRate(10)
        account.setAutoCommit(false)

        when:
        account.setRate(5)
        account.addInterest() // 105
        account.withdraw(50) // 55
        account.deposit(25) // 80

        then:
        account.getBalance() == 80
        account.getRate() == 5
    }

}
