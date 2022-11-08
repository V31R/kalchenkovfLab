package kalchenko.bank.entity;

import java.math.BigDecimal;

public class Bank implements Entity {

    private Long id;
    private String name;
    private int officesNumber = 0;
    private int atmNumber = 0;
    private int employeeNumber = 0;
    private int userNumber = 0;
    private int bankRate;
    private BigDecimal moneyAmount;
    private BigDecimal interestRate;

    public Bank() {
    }

    public Bank(String name, int bankRate, BigDecimal moneyAmount, BigDecimal interestRate) {
        this.name = name;
        this.bankRate = bankRate;
        this.moneyAmount = moneyAmount;
        this.interestRate = interestRate;
    }

    public Bank(Bank bank) {
        this.id = bank.getId();
        this.name = bank.getName();
        this.bankRate = bank.getBankRate();
        this.moneyAmount = bank.getMoneyAmount();
        this.interestRate = bank.getInterestRate();
        this.officesNumber = bank.getOfficesNumber();
        this.atmNumber = bank.getAtmNumber();
        this.employeeNumber = bank.getEmployeeNumber();
        this.userNumber = bank.getUserNumber();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOfficesNumber() {
        return officesNumber;
    }

    public void setOfficesNumber(int officesNumber) {
        this.officesNumber = officesNumber;
    }

    public int getAtmNumber() {
        return atmNumber;
    }

    public void setAtmNumber(int atmNumber) {
        this.atmNumber = atmNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getBankRate() {
        return bankRate;
    }

    public void setBankRate(int bankRate) {
        this.bankRate = bankRate;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", officesNumber=" + officesNumber +
                ", atmNumber=" + atmNumber +
                ", employeeNumber=" + employeeNumber +
                ", userNumber=" + userNumber +
                ", bankRate=" + bankRate +
                ", moneyAmount=" + moneyAmount +
                ", interestRate=" + interestRate + "%}";
    }

}
