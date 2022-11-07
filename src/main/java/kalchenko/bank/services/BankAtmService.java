package kalchenko.bank.services;

import kalchenko.bank.entity.BankAtm;

import java.util.List;

public interface BankAtmService extends BankOperations{

    /**
     * Добавляет bankAtm в репозиторий, если добавление было успешно
     * извещает об этом связанный bankOffice.
     */
    BankAtm addBankAtm(BankAtm bankAtm);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    BankAtm getBankAtmById(Long id);

    /**
     * Возвращает все банки, которые хранятся в репозитории.
     */
    List<BankAtm> getAllBankAtms();

    /**
     * Удаляет объект, извещает об этом связанный BankOffice.
     */
    boolean deleteBankAtmById(Long id);
}
