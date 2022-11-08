package kalchenko.bank.services.impl;

import kalchenko.bank.entity.BankAtm;
import kalchenko.bank.repositories.BankAtmRepository;
import kalchenko.bank.services.BankAtmService;
import kalchenko.bank.services.BankOfficeService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс-одиночка
 */
public class BankAtmServiceImpl implements BankAtmService {

    private static BankAtmServiceImpl INSTANCE;

    private BankAtmServiceImpl() {
    }

    public static BankAtmServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankAtmServiceImpl();
        }

        return INSTANCE;
    }

    private final BankAtmRepository bankAtmRepository = BankAtmRepository.getInstance();
    private final BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();


    @Override
    public BankAtm addBankAtm(BankAtm bankAtm) {

        var newBankAtm = bankAtmRepository.add(bankAtm);
        var office = newBankAtm.getBankOffice();

        if (office != null) {
            bankOfficeService.addAtm(office.getId());
        }
        return newBankAtm;

    }


    @Override
    public BankAtm getBankAtmById(Long id) {
        return bankAtmRepository.findById(id);
    }

    @Override
    public List<BankAtm> getAllBankAtms() {
        return bankAtmRepository.findAll();
    }

    @Override
    public boolean deleteBankAtmById(Long id) {

        var officeId = bankAtmRepository.findById(id).getId();

        if (bankAtmRepository.deleteById(id)) {

            return bankOfficeService.deleteAtm(officeId);

        }
        return false;
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var bankAtm = bankAtmRepository.findById(id);
        if (bankAtm.isPaymentAvailable()) {
            return bankOfficeService.withdrawMoney(bankAtm.getBankOffice().getId(), money);
        }
        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bankAtm = bankAtmRepository.findById(id);
        if (bankAtm.isDepositAvailable()) {
            return bankOfficeService.depositMoney(bankAtm.getBankOffice().getId(), money);
        }
        return false;
    }
}
