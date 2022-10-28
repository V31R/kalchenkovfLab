package kalchenko.bank.services;

import kalchenko.bank.entity.BankAtm;

public interface BankAtmService extends BankOperations{

    /**
     * Добавляет bankAtm в репозиторий, если добавление было успешно
     * извещает об этом связанный bankOffice.
     */
    BankAtm addBankAtm(BankAtm bankAtm);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    BankAtm getBankAtm();

    /**
     * Удаляет объект, извещает об этом связанный BankOffice.
     */
    boolean deleteBankAtm();
}
