package kalchenko.bank.entity;

import java.math.BigDecimal;

public class BankOffice {

    private Long id;
    private String name;
    private String address;
    private Bank bank;
    private boolean isWorking;
    private boolean isPossiblePlaceAtm;
    private int atmNumber=0;
    private boolean loansAvailable;
    private boolean paymentAvailable;
    private boolean depositAvailable;
    private BigDecimal moneyAmount;
    private BigDecimal rent;

    public BankOffice() {}

    public BankOffice(Long id, String name, String address, Bank bank, boolean isWorking,
                      boolean isPossiblePlaceAtm, boolean canApplyLoan, boolean canPaymentOfMoney,
                      boolean canDepositMoney, BigDecimal moneyAmount, BigDecimal rent) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isPossiblePlaceAtm = isPossiblePlaceAtm;
        this.loansAvailable = canApplyLoan;
        this.paymentAvailable = canPaymentOfMoney;
        this.depositAvailable = canDepositMoney;
        this.moneyAmount = moneyAmount;
        this.rent = rent;
    }

    public BankOffice(BankOffice bankOffice) {
        this.id = bankOffice.getId();
        this.name = bankOffice.getName();
        this.address = bankOffice.getAddress();
        this.bank = bankOffice.getBank();
        this.isWorking = bankOffice.isWorking();
        this.isPossiblePlaceAtm = bankOffice.isPossiblePlaceAtm();
        this.atmNumber = bankOffice.getAtmNumber();
        this.loansAvailable = bankOffice.isLoansAvailable();
        this.paymentAvailable = bankOffice.isPaymentAvailable();
        this.depositAvailable = bankOffice.isDepositAvailable();
        this.moneyAmount = bankOffice.getMoneyAmount();
        this.rent = bankOffice.getRent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public boolean isPossiblePlaceAtm() {
        return isPossiblePlaceAtm;
    }

    public void setPossiblePlaceAtm(boolean possiblePlaceAtm) {
        isPossiblePlaceAtm = possiblePlaceAtm;
    }

    public int getAtmNumber() {
        return atmNumber;
    }

    public void setAtmNumber(int atmNumber) {
        this.atmNumber = atmNumber;
    }

    public boolean isLoansAvailable() {
        return loansAvailable;
    }

    public void setLoansAvailable(boolean loansAvailable) {
        this.loansAvailable = loansAvailable;
    }

    public boolean isPaymentAvailable() {
        return paymentAvailable;
    }

    public void setPaymentAvailable(boolean paymentAvailable) {
        this.paymentAvailable = paymentAvailable;
    }

    public boolean isDepositAvailable() {
        return depositAvailable;
    }

    public void setDepositAvailable(boolean depositAvailable) {
        this.depositAvailable = depositAvailable;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "BankOffice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", bank=" + bank +
                ", isWorking=" + isWorking +
                ", isPossiblePlaceAtm=" + isPossiblePlaceAtm +
                ", atmNumber=" + atmNumber +
                ", canApplyLoan=" + loansAvailable +
                ", canPaymentOfMoney=" + paymentAvailable +
                ", canDepositMoney=" + depositAvailable +
                ", moneyAmount=" + moneyAmount +
                ", rent=" + rent +
                '}';
    }
}
