package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.exceptions.NotExistedObjectException;

import java.io.OutputStream;
import java.util.List;

public interface BankService extends BankOperations {

    /**
     * Возвращает банк по id, который хранится в репозитории.
     */
    Bank getBankById(Long id) throws IdException;

    /**
     * Возвращает все банки, которые хранятся в репозитории.
     */
    List<Bank> getAllBanks();

    /**
     * Добавляет bank в репозиторий
     */
    Bank addBank(Bank bank);

    /**
     * Если объект существует, то обновляет его.
     */
    Bank update(Bank bank);

    /**
     * Создаёт объект банка
     */
    Bank createBank();

    /**
     * Вывод всех данных по bankId банка (банкоматы, офисы, сотрудники, клиенты)
     */
    void outputBankInfo(Long bankId, OutputStream outputStream) throws NotExistedObjectException;

}
