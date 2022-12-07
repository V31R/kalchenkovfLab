package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.exceptions.*;

import java.io.IOException;
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

    /**
     * Выводит все счета банка в txt файл
     * @param bankId  id банка
     * @param filename имя txt файла
     */
    void exportBankAccounts(Long bankId, String filename) throws IOException;

    /**
     * Считывает все счета из txt файла ы банк
     * @param bankId id банка, в который будут считаны счета
     * @param filename имя txt файла, из которого будут считаны счета
     */
    void importBankAccounts(Long bankId, String filename) throws IOException;

    /**
     * Переносит счета из одно банка в другой
     * @param srcBankId id банка, из которого будут перенесены счета
     * @param dstBankId id банка, в который будут перенесены счета
     */
    void transferBankAccounts(Long srcBankId, Long dstBankId) throws IOException;

}
