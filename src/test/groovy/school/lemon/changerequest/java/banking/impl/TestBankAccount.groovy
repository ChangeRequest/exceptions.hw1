package school.lemon.changerequest.java.banking.impl

import school.lemon.changerequest.java.banking.BankAccount
import spock.lang.Specification

class TestBankAccount extends Specification {

    protected BankAccount getBankAccount() {
        return AccountFactory.createBankAccount();
    }

    def "Test account number"() {
        given:
        def currentAccountNumber = getBankAccount().getAccountNumber()

        when:
        def newAccountNumber = getBankAccount().getAccountNumber()

        then:
        currentAccountNumber + 1 == newAccountNumber
    }

    def "Test set rate"() {
        given:
        def account = getBankAccount()

        when:
        account.setRate 3

        then:
        account.getRate() == 3
    }

    def "Test deposit"() {
        given:
        def account = getBankAccount()

        when:
        account.deposit 200

        then:
        account.getBalance() == 200
    }

    def "Test withdraw"() {
        given:
        def account = getBankAccount()
        account.deposit 200

        when:
        account.withdraw 50

        then:
        account.getBalance() == 150
    }

    def "Test add interest"() {
        given:
        def account = getBankAccount()
        account.deposit 500
        account.setRate 25

        when:
        account.addInterest()

        then:
        account.getBalance() == 625
    }

    def "Test toString"() {
        given:
        def account = getBankAccount()

        when:
        account.deposit 12.34567890

        then:
        account.toString() == ("Account #" + account.getAccountNumber() + ", (\$12.34)")
    }

    def "Test deposit negative value"() {
        given:
        def account = getBankAccount()

        when:
        account.deposit(-12)

        then:
        thrown(IllegalArgumentException)
    }

    def "Test withdraw negative value"() {
        given:
        def account = getBankAccount()

        when:
        account.withdraw(-12)

        then:
        thrown(IllegalArgumentException)
    }

    def "Test withdraw more than balance"() {
        given:
        def account = getBankAccount()
        account.deposit 100

        when:
        account.withdraw 100.0001

        then:
        thrown(IllegalArgumentException)
    }

}
