package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.repositories.BankRepository;
import kalchenko.bank.services.BankService;

import java.math.BigDecimal;
import java.util.List;

public class BankServiceImpl implements BankService {

    private static BankServiceImpl INSTANCE;

    private BankServiceImpl(){}

    public static BankServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BankServiceImpl();
        }

        return INSTANCE;
    }

    private final BankRepository bankRepository = BankRepository.getInstance();


    @Override
    public Bank addBank(Bank bank) {
        return bankRepository.add(bank);
    }

    @Override
    public Bank getBankById(Long id) {
        return bankRepository.findById(id);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank update(Bank bank) {
        return bankRepository.update(bank);
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var bank = bankRepository.findById(id);

        if(bank != null && money.compareTo(bank.getMoneyAmount()) == -1){
            bank.setMoneyAmount(bank.getMoneyAmount().subtract(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bank = bankRepository.findById(id);

        if(bank != null ){
            bank.setMoneyAmount(bank.getMoneyAmount().add(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }


}
