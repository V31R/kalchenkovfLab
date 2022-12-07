package kalchenko.bank.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.CreditAccount;
import kalchenko.bank.entity.PaymentAccount;
import kalchenko.bank.exceptions.*;
import kalchenko.bank.repositories.*;
import kalchenko.bank.services.BankService;
import kalchenko.bank.services.CreditAccountService;
import kalchenko.bank.services.PaymentAccountService;
import kalchenko.bank.utils.BankComparator;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс-одиночка
 */
public class BankServiceImpl implements BankService {

    private static BankServiceImpl INSTANCE;

    private BankServiceImpl() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static BankServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankServiceImpl();
        }

        return INSTANCE;
    }

    private final BankRepository bankRepository = BankRepository.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String TRANSFER_PATH = "/tmp/transfer.txt";

    private static final int MAX_RATE = 100;

    private static int number = 0;
    private static final Random RANDOM = new Random();
    private static final int MIN_LOAN_TERM_IN_MONTH = 3;
    private static final double MAX_INTEREST_RATE = 20.d;
    private static final BigDecimal MAX_SALARY_PART = BigDecimal.valueOf(0.3d);
    private static final int MAX_MONEY = 1_000_000;

    public Bank createBank() {
        var rate = RANDOM.nextInt(MAX_RATE);
        //Поскольку рейтинг может быть до 100, то нужно уменьшить его в 10 раз, чтобы ставка не могла быть отрицательной
        BigDecimal interestRate = BigDecimal.valueOf(RANDOM.nextDouble() * (MAX_INTEREST_RATE - rate / 10.d));
        BigDecimal money = BigDecimal.valueOf(RANDOM.nextInt(MAX_MONEY));
        return new Bank(String.format("Bank_%d", number++), rate, money, interestRate);
    }

    @Override
    public Bank addBank(Bank bank) {
        return bankRepository.add(bank);
    }

    @Override
    public Bank getBankById(Long id) {
        var bank = bankRepository.findById(id);;
        if(bank == null){
            throw new IdException();
        }
        return bank;
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
        if(BigDecimal.ZERO.compareTo(money) > 0){
            throw new NegativeSumException();
        }
        var bank = bankRepository.findById(id);
        if(bank==null){
            throw new IdException();
        }
        if (money.compareTo(bank.getMoneyAmount()) == -1) {
            bank.setMoneyAmount(bank.getMoneyAmount().subtract(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        if(BigDecimal.ZERO.compareTo(money) > 0){
            throw new NegativeSumException();
        }
        var bank = bankRepository.findById(id);

        if (bank != null) {
            bank.setMoneyAmount(bank.getMoneyAmount().add(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }


    @Override
    public void outputBankInfo(Long bankId, OutputStream outputStream) {

        PrintStream printStream = new PrintStream(outputStream);

        var bank = bankRepository.findById(bankId);
        if(bank == null){
            throw new NotExistedObjectException();
        }
        printStream.printf("Bank data about %s\n", bank.getName());
        printStream.println(bank);

        BankAtmRepository bankAtmRepository = BankAtmRepository.getInstance();
        EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
        UserRepository userRepository = UserRepository.getInstance();

        var bankOffices = BankOfficeServiceImpl.getInstance().getAllBankOfficesByBankId(bankId);
        if (bankOffices.size() > 0) {
            printStream.println("Bank offices:");
            bankOffices.forEach(printStream::println);
        } else {
            printStream.println("User does not have offices");
        }

        var bankAtms = bankAtmRepository.findAll().stream()
                .filter(bankAtm -> bankAtm.getBankOffice().getBank().getId().compareTo(bankId) == 0)
                .toList();

        if (bankAtms.size() > 0) {
            printStream.println("Bank ATMs:");
            bankAtms.forEach(printStream::println);
        } else {
            printStream.println("Bank does not ATM");
        }

        var employees = employeeRepository.findAll().stream()
                .filter(employee -> employee.getBankOffice().getBank().getId().compareTo(bankId) == 0)
                .toList();

        if (employees.size() > 0) {
            printStream.println("Bank employees:");
            employees.forEach(printStream::println);
        } else {
            printStream.println("Bank does not employees");
        }

        var users = userRepository.findAll().stream()
                .filter(
                        user -> user.getBanks().stream()
                                .filter(b -> b.getId().compareTo(bankId) == 0)
                                .toList()
                                .size() > 0
                )
                .toList();

        if (users.size() > 0) {
            printStream.println("Bank users:");
            users.forEach(printStream::println);
        } else {
            printStream.println("Bank does not user");
        }

    }


    @Override
    public Long issueLoan(Long userId, BigDecimal creditSum) throws LendingTermsException {
        if(BigDecimal.ZERO.compareTo(creditSum) >= 0){
            throw new NegativeSumException();
        }
        var user = UserServiceImpl.getInstance().getUserById(userId);

        //получем список банков, отсортированных по правилу из лабораторной работы. Последний банк - самый лучший
        var banks = bankRepository.findAll().stream().sorted(new BankComparator()).toList();
        for(int i = banks.size()-1; i >=0; i--){
            if( banks.get(i).getBankRate() > 50 && user.getCreditRate() < 500){
                continue;
            }
            var offices = BankOfficeServiceImpl.getInstance().getAllBankOfficesByBankId(banks.get(i).getId());
            for(int j = 0; j < offices.size(); j++) {
                if(!offices.get(j).isLoansAvailable() || offices.get(j).getMoneyAmount().compareTo(creditSum) < 0){
                    continue;
                }
                var employee = EmployeeServiceImpl.getInstance()
                        .getEmployeeInOfficeWhichCanApplyLoan(offices.get(j).getId());

                if(employee == null){
                    continue;
                }

                var atms = BankAtmServiceImpl.getInstance().getAllBankAtmsByOffice(offices.get(j).getId());
                for(int k = 0; k < atms.size(); k++) {
                    if(!atms.get(k).isPaymentAvailable() || atms.get(k).getMoneyAmount().compareTo(creditSum) < 0){
                        continue;
                    }

                    int monthNumber = creditSum.divide(user.getSalary().multiply(MAX_SALARY_PART), RoundingMode.CEILING).intValue();
                    if(monthNumber < MIN_LOAN_TERM_IN_MONTH){
                        monthNumber = MIN_LOAN_TERM_IN_MONTH;
                    }

                    var paymentAccount = PaymentAccountServiceImpl.getInstance().paymentAccountRegistration(banks.get(i), user);

                    var creditAccountService = CreditAccountServiceImpl.getInstance();
                    var creditAccount = creditAccountService.createCreditAccount(banks.get(i), user,
                            paymentAccount, employee, creditSum, monthNumber);

                    BankAtmServiceImpl.getInstance().withdrawMoney(atms.get(k).getId(), creditSum);

                    return creditAccountService.addCreditAccount(creditAccount).getId();

                }

            }
        }

        throw new LendingTermsException();
    }

    @Override
    public void exportBankAccounts(Long bankId, String filename) throws IOException {
        var bank = bankRepository.findById(bankId);
        var paymentAccounts = PaymentAccountServiceImpl.getInstance().getAllPaymentAccountsByBank(bank);
        var creditAccounts = CreditAccountServiceImpl.getInstance().getAllCreditAccountsByBank(bank);

        AccountsRepository accountsRepository = new AccountsRepository(paymentAccounts,creditAccounts);

        File file = new File(filename);
        objectMapper.writeValue(file, accountsRepository);

    }

    @Override
    public void importBankAccounts(Long bankId, String filename) throws IOException {
        var bank = bankRepository.findById(bankId);
        File file = new File(filename);
        AccountsRepository accountsRepository = objectMapper.readValue(file, AccountsRepository.class);
        var paymentAccounts = accountsRepository.paymentAccounts;
        var creditAccounts = accountsRepository.creditAccounts;

        creditAccounts.forEach(creditAccount -> creditAccount.setBankName(bank.getName()));

        PaymentAccountService paymentAccountService = PaymentAccountServiceImpl.getInstance();
        CreditAccountService creditAccountService = CreditAccountServiceImpl.getInstance();

        List<PaymentAccount> newPaymentAccounts = new ArrayList<>();
        for(var paymentAccount: paymentAccounts){
            paymentAccount.setBankName(bank.getName());
            newPaymentAccounts.add(paymentAccountService.addPaymentAccount(paymentAccount));
        }
        for(var creditAccount: creditAccounts) {
           int index = paymentAccounts.indexOf(creditAccount.getPaymentAccount());
           if(index != -1){
               creditAccount.setPaymentAccount(newPaymentAccounts.get(index));
           }
           creditAccountService.addCreditAccount(creditAccount);
        }

    }

    @Override
    public void transferBankAccounts(Long srcBankId, Long dstBankId) throws IOException {
        var srcBank = bankRepository.findById(srcBankId);
        var dstBank = bankRepository.findById(dstBankId);
        var paymentAccounts = PaymentAccountServiceImpl.getInstance().getAllPaymentAccountsByBank(srcBank);
        var creditAccounts = CreditAccountServiceImpl.getInstance().getAllCreditAccountsByBank(srcBank);

        this.exportBankAccounts(srcBankId, TRANSFER_PATH);

        creditAccounts.forEach(paymentAccount ->  CreditAccountRepository.getInstance().deleteById(paymentAccount.getId()));
        paymentAccounts.forEach(paymentAccount ->  PaymentAccountRepository.getInstance().deleteById(paymentAccount.getId()));

        for(var paymentAccount: paymentAccounts){
            var user = UserServiceImpl.getInstance().getUserById(paymentAccount.getUser().getId());
            var banks = user.getBanks();
            banks.removeIf(bank -> bank.getName().equals(srcBank.getName()));
            user.setBanks(banks);
            UserServiceImpl.getInstance().updateUser(user);
        }

        this.importBankAccounts(dstBankId, TRANSFER_PATH);

    }

    private static class AccountsRepository{
        public List<PaymentAccount> paymentAccounts;
        public List<CreditAccount> creditAccounts;

        public AccountsRepository(){}

        public AccountsRepository(List<PaymentAccount> paymentAccounts, List<CreditAccount> creditAccounts) {
            this.paymentAccounts = paymentAccounts;
            this.creditAccounts = creditAccounts;
        }
    }


}
