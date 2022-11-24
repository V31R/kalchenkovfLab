package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.exceptions.IdException;

import java.util.List;

public interface BankOfficeService extends BankOperations {

    /**
     * Добавляет bankOffice в репозиторий, извещает об этом связанный bank.
     */
    BankOffice addBankOffice(BankOffice bankOffice);

    /**
     * Возвращает офис по Id, который хранится в репозитории.
     */
    BankOffice getBankOfficeById(Long id);

    /**
     * Возвращает все офисы, которые хранятся в репозитории.
     */
    List<BankOffice> getAllBankOffices();

    /**
     * Возвращает все офисы, которые хранятся в репозитории, принадлежащие банку bankId.
     */
    List<BankOffice> getAllBankOfficesByBankId(Long bankId);

    /**
     * Удаляет офис по Id, извещает об этом связанный Bank.
     */
    boolean deleteBankOfficeById(Long id) throws IdException;

    /**
     * Увеличивает число банкоматов, при добавлении банкомата, извещает об этом связанный bank.
     */
    boolean addAtm(Long bankOfficeId);

    /**
     * Уменьшает число банкоматов, при удалении банкомата, извещает об этом связанный bank.
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

    /**
     * Создаёт офис
     */
    BankOffice createBankOffice(Bank bank);
}
