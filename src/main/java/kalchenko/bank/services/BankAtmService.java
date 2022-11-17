package kalchenko.bank.services;

import kalchenko.bank.entity.BankAtm;
import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.entity.Employee;

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
     * Возвращает все бакноматы, которые хранятся в репозитории, принадлежащие офису officeId.
     */
    List<BankAtm> getAllBankAtmsByOffice(Long officeId);

    /**
     * Создаёт банкомат
     */
    BankAtm createBankAtm(BankOffice bankOffice, Employee employee);

    /**
     * Удаляет бакномат по Id, извещает об этом связанный BankOffice.
     */
    boolean deleteBankAtmById(Long id);
}
