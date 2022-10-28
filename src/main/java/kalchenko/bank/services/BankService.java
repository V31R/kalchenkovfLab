package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;

public interface BankService extends BankOperations {

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    Bank getBank();
    /**
     * Добавляет bank в репозиторий
     */
    Bank addBank(Bank bank);

    /**
     * Увеличивает число офисов.
     */
    boolean addOffice();

    /**
     * Уменьшает число офисов.
     */
    boolean deleteOffice();

    /**
     * Увеличивает число банкоматов.
     */
    boolean addAtm();

    /**
     * Уменьшает число банкоматов.
     */
    boolean deleteAtm();

    /**
     * Увеличивает число работников.
     */
    boolean addEmployee();

    /**
     * Уменьшает число работников.
     */
    boolean deleteEmployee();

    /**
     * Увеличивает число пользователей.
     */
    boolean addUser();

    /**
     * Уменьшает число пользователей.
     */
    boolean deleteUser();

}
