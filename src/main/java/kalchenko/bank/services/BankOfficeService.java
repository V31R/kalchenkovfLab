package kalchenko.bank.services;

import kalchenko.bank.entity.BankOffice;

import java.util.List;

public interface BankOfficeService extends BankOperations {

    /**
     * Добавляет bankOffice в репозиторий, если добавление было успешно
     * извещает об этом связанный bank.
     */
    BankOffice addBankOffice(BankOffice bankOffice);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    BankOffice getBankOfficeById(Long id);

    /**
     * Возвращает все банки, которые хранятся в репозитории.
     */
    List<BankOffice> getAllBanks();

    /**
     * Удаляет объект, извещает об этом связанный Bank.
     */
    boolean deleteBankOfficeById(Long id);

    /**
     * Увеличивает число банкоматов, при добавлении, извещает об этом связанный bank.
     */
    boolean addAtm(Long bankOfficeId);

    /**
     * Уменьшает число банкоматов, при добавлении, извещает об этом связанный bank.
     */
    boolean deleteAtm(Long bankOfficeId);

    /**
     * Извещает связанный bank об увеличении числа пользователей.
     */
    boolean addEmployee(Long bankOfficeId);

    /**
     * Извещает связанный bank об уменьшении числа пользователей.
     */
    boolean deleteEmployee(Long bankOfficeId);

}
