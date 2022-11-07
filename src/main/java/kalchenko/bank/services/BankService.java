package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;

import java.util.List;

public interface BankService extends BankOperations {

    /**
     * Возвращает банк по Id, который хранится в репозитории.
     */
    Bank getBankById(Long id);

    /**
     * Возвращает все банки, которые хранятся в репозитории.
     */
    List<Bank> getAllBanks();

    /**
     * Добавляет bank в репозиторий
     */
    Bank addBank(Bank bank);

    /**
     * Увеличивает число офисов.
     */
    boolean addOffice(Long bankId);

    /**
     * Уменьшает число офисов.
     */
    boolean deleteOffice(Long bankId);

    /**
     * Увеличивает число банкоматов.
     */
    boolean addAtm(Long bankId);

    /**
     * Уменьшает число банкоматов.
     */
    boolean deleteAtm(Long bankId);

    /**
     * Увеличивает число работников.
     */
    boolean addEmployee(Long bankId);

    /**
     * Уменьшает число работников.
     */
    boolean deleteEmployee(Long bankId);

    /**
     * Увеличивает число пользователей.
     */
    boolean addUser(Long bankId);

    /**
     * Уменьшает число пользователей.
     */
    boolean deleteUser(Long bankId);

}
