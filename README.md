Exceptions. Home work 1 
=============================

[![Join the chat at https://gitter.im/changerequest/exceptions.hw1](https://badges.gitter.im/changerequest/exceptions.hw1.svg)](https://gitter.im/changerequest/exceptions.hw1?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

1 Bank Account
--------------
Create class that implements BankAccount interface. Your task is to write that class.
The primary task of the BankAccount object is to keep track of the account balance. 
However, different accounts may earn interest at different rates, so the object must also track its own interest rate.
The BankAccount class should has three constructors:

1. one takes an initial balance and an interest rate;
2. one takes just the initial balance (the interest rate is set to the default interest rate, which is 1.0%); 
3. one takes no arguments (the balance is set to $0.00, and the interest rate to the default).

Each BankAccount object is assigned an account number in the order that it was created. 
That is, the account number for the first account is 1, for the second account 2, and so on. 
This account number is available through the instance constant ACCOUNT_NO.
BankAccounts also have the following methods:

1. **getBalance** - returns the balance in this account;
2. **getRate** - returns the rate for this account;
3. **withdraw** - takes money out of the account -- but only if there's enough money in the account to cover the withdrawal; 
4. **deposit** - adds money to the account;
5. **addInterest** adds interest to the account. It adds one year's interest to the account (at the account's individual interest rate). For example, if the balance is $1000.00 and the rate is 10%, then addInterest() increases the balance to $1100 ($1000 plus 10% of $1000); 
6. **setRate** - changes the interest rate on this account;
7. **toString** - returns a String representing this account. The String is of the form "Account #N, ($bbb.bb)", where N is the account number and bbb.bb is the account balance. Balance must have exactly two digits after the decimal with DOWN rounding.

2 Transaction management for Bank Account
-----------------------------------------
Implement TransactionalBankAccount interface. There are several additional things:

1. **setAutoCommit** - determine whether each specific operation is automatically committed (can't be reverted);
2. **commit** - when auto commit is disabled, commits all changes;
3. **revert** - when auto commit is disabled, revert all uncommitted changes.

Feel free to use BankAccount implemented in previous task.

3 Transaction manager
---------------------

As soon as we have TransactionalBankAccount it's not very convenient to execute bunch of operations in one transaction. 
Implement AccountTransactionManager which allows to execute several operations at once.
There two methods:
 
1. **execute** - executes array of operations. Do not guaranty that all operations are executed without errors and do not revert previous operations in case of errors.
2. **executeInTransaction** - executes array of operations in transactions. Guaranty that ALL operations are executed or none of them (in case of any error). Should enable autoCommit after execution.

4 Cafe
------

There are several classes which describe Cafe. 
Cafe serves Clients giving them coup of some kind of drink. 
Client has one method **drinkCoffee** with one argument **Drink**.
If a drink is not a coffee - one exception should be thrown by Client. It should be handled by the Cafe and another drink given to Client.
If coffee is too cold - second exception should be thrown by Client. It should be handled by the Cafe and coffee should be warmed up.
If coffee is too hot - third exception should be thrown by Client. It should be handled by the Cafe and coffee should be cooled.
Client can be very unsatisfied by any other reason (random) and fourth exception should be thrown by Client. It that case Cafe can't serve Client and exception should be thrown.

Create own exceptions hierarchy. Determine which exception should be thrown in each case.
Exceptions should have information about temperature if Coffee was too cold or too hot.
Print to console every exception that was handled.

There is an App class that creates Cafe and generates Clients which Cafe should serve.
