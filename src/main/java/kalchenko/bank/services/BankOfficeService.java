package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.exceptions.NegativeSumException;
import kalchenko.bank.exceptions.NotExistedObjectException;

import java.util.List;

public interface BankOfficeService extends BankOperations {

    /**
     * Добавляет bankOffice в репозиторий, извещает об этом связанный bank.
     */
    BankOffice addBankOffice(BankOffice bankOffice) throws IdException, NegativeSumException;

    /**
     * Возвращает офис по Id, который хранится в репозитории.
     */
    BankOffice getBankOfficeById(Long id) throws IdException;

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
    boolean addAtm(Long bankOfficeId) throws IdException, NotExistedObjectException;

    /**
     * Уменьшает число банкоматов, при удалении банкомата, извещает об этом связанный bank.
     */
    boolean deleteAtm(Long bankOfficeId) throws NotExistedObjectException, IdException;

    /**
     * Извещает связанный bank об увеличении числа пользователей.
     */
    boolean addEmployee(Long bankOfficeId) throws NotExistedObjectException, IdException;

    /**
     * Извещает связанный bank об уменьшении числа пользователей.
     */
    boolean deleteEmployee(Long bankOfficeId) throws IdException, NotExistedObjectException;

    /**
     * Создаёт офис
     */
    BankOffice createBankOffice(Bank bank);
}
