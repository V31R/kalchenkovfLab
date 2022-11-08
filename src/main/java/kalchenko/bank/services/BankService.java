package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;

import java.io.OutputStream;
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
     * Если объект существует, то обновляет его.
     */
    Bank update(Bank bank);

    void outputBankInfo(Long bankId, OutputStream outputStream);

}
