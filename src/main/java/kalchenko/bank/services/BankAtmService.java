package kalchenko.bank.services;

import kalchenko.bank.entity.BankAtm;

import java.util.List;

public interface BankAtmService extends BankOperations{

    /**
     * Добавляет bankAtm в репозиторий, извещает об этом связанный bankOffice.
     */
    BankAtm addBankAtm(BankAtm bankAtm);

    /**
     * Возвращает бакномат по Id, который хранится в репозитории.
     */
    BankAtm getBankAtmById(Long id);

    /**
     * Возвращает все бакноматы, которые хранятся в репозитории.
     */
    List<BankAtm> getAllBankAtms();

    /**
     * Удаляет бакномат по Id, извещает об этом связанный BankOffice.
     */
    boolean deleteBankAtmById(Long id);
}
