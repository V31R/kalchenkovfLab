package kalchenko.bank.services;

import kalchenko.bank.entity.BankOffice;

public interface BankOfficeService extends BankOperations {

    /**
     * Добавляет bankOffice в репозиторий, если добавление было успешно
     * извещает об этом связанный bank.
     */
    BankOffice addBankOffice(BankOffice bankOffice);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    BankOffice getBankOffice();

    /**
     * Удаляет объект, извещает об этом связанный Bank.
     */
    boolean deleteBankOffice();

    /**
     * Увеличивает число банкоматов, при добавлении, извещает об этом связанный bank.
     */
    boolean addAtm();

    /**
     * Уменьшает число банкоматов, при добавлении, извещает об этом связанный bank.
     */
    boolean deleteAtm();

    /**
     * Извещает связанный bank об увеличении числа пользователей.
     */
    boolean addEmployee();

    /**
     * Извещает связанный bank об уменьшении числа пользователей.
     */
    boolean deleteEmployee();

}
