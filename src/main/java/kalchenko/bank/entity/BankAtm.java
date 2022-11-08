package kalchenko.bank.entity;

import kalchenko.bank.utils.AtmStatus;

import java.math.BigDecimal;

public class BankAtm implements Entity {

    private Long id;
    private String name;
    private AtmStatus status;
    private BankOffice bankOffice;
    private String place;
    private Employee employee;
    private boolean paymentAvailable;
    private boolean depositAvailable;
    private BigDecimal moneyAmount;
    private BigDecimal maintenance;

    public BankAtm() {
    }

    public BankAtm(String name, AtmStatus status, BankOffice bankOffice,
                   String place, Employee employee, boolean canPaymentOfMoney, boolean canDepositMoney,
                   BigDecimal moneyAmount, BigDecimal maintenance) {
        this.name = name;
        this.status = status;
        this.bankOffice = bankOffice;
        this.place = place;
        this.employee = employee;
        this.paymentAvailable = canPaymentOfMoney;
        this.depositAvailable = canDepositMoney;
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
        this.paymentAvailable = bankAtm.isPaymentAvailable();
        this.depositAvailable = bankOffice.isDepositAvailable();
        this.moneyAmount = bankAtm.getMoneyAmount();
        this.maintenance = bankAtm.getMaintenance();
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
                ", bankOffice=" + bankOffice.getName() +
                ", place='" + place + '\'' +
                ", employee=" + employee.getFullName() +
                ", canPaymentOfMoney=" + paymentAvailable +
                ", canDepositMoney=" + depositAvailable +
                ", moneyAmount=" + moneyAmount +
                ", maintenance=" + maintenance +
                '}';
    }
}
