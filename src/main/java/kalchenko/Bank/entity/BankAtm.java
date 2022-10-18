package kalchenko.Bank.entity;

import kalchenko.Bank.utils.AtmStatus;

import java.math.BigDecimal;

public class BankAtm {

    private Long id;
    private String name;
    private AtmStatus status;
    private BankOffice bankOffice;
    private String place;
    private Employee employee;
    private boolean canPaymentOfMoney;
    private boolean canDepositMoney;
    private BigDecimal moneyAmount;
    private BigDecimal maintenance;

    public BankAtm() {}

    public BankAtm(Long id, String name, AtmStatus status, BankOffice bankOffice,
                   String place, Employee employee, boolean canPaymentOfMoney, boolean canDepositMoney,
                   BigDecimal moneyAmount, BigDecimal maintenance) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.bankOffice = bankOffice;
        this.place = place;
        this.employee = employee;
        this.canPaymentOfMoney = canPaymentOfMoney;
        this.canDepositMoney = canDepositMoney;
        this.moneyAmount = moneyAmount;
        this.maintenance = maintenance;
    }

    public BankAtm(BankAtm bankAtm) {
        this.id = bankAtm.getId();
        this.name = bankAtm.getName();
        this.status = bankAtm.getStatus();
        this.bankOffice = bankAtm.getBankOffice();
        this.place = bankAtm.getPlace();
        this.employee = bankAtm.getEmployee();
        this.canPaymentOfMoney = bankAtm.isCanPaymentOfMoney();
        this.canDepositMoney = bankOffice.isCanDepositMoney();
        this.moneyAmount = bankAtm.getMoneyAmount();
        this.maintenance = bankAtm.getMaintenance();
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
        return bankOffice.getAddress();
    }

    public AtmStatus getStatus() {
        return status;
    }

    public void setStatus(AtmStatus status) {
        this.status = status;
    }

    public BankOffice getBankOffice() {
        return bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isCanPaymentOfMoney() {
        return canPaymentOfMoney;
    }

    public void setCanPaymentOfMoney(boolean canPaymentOfMoney) {
        this.canPaymentOfMoney = canPaymentOfMoney;
    }

    public boolean isCanDepositMoney() {
        return canDepositMoney;
    }

    public void setCanDepositMoney(boolean canDepositMoney) {
        this.canDepositMoney = canDepositMoney;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(BigDecimal maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public String toString() {
        return "BankAtm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", bankOffice=" + bankOffice +
                ", place='" + place + '\'' +
                ", employee=" + employee +
                ", canPaymentOfMoney=" + canPaymentOfMoney +
                ", canDepositMoney=" + canDepositMoney +
                ", moneyAmount=" + moneyAmount +
                ", maintenance=" + maintenance +
                '}';
    }
}
