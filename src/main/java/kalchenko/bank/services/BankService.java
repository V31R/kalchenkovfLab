package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.exceptions.*;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

public interface BankService extends BankOperations {

    /**
     * Возвращает банк по id, который хранится в репозитории.
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

    /**
     * Создаёт объект банка
     */
    Bank createBank();

    /**
     * Вывод всех данных по bankId банка (банкоматы, офисы, сотрудники, клиенты)
     */
    void outputBankInfo(Long bankId, OutputStream outputStream);

    /**
     * Выдаёт кредит пользователю
     * @param userId id пользователя, которому будет выдан кредит
     * @param creditSum сумма, на которую будет выдан кредит
     * @return возвращает id кредита, если он был выдан, иначе возвращает null.
     */
    Long issueLoan(Long userId, BigDecimal creditSum) throws LendingTermsException;

}
