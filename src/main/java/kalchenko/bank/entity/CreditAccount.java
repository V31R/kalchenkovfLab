package kalchenko.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditAccount implements Entity{

    private Long id;
    private User user;
    private String bankName;
    private LocalDate start;
    private LocalDate end;
    private int monthNumber;
    private BigDecimal sum;
    private BigDecimal monthPayment;
    private BigDecimal interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    public CreditAccount() {}

    public CreditAccount(User user, String bankName, LocalDate start, int monthNumber, BigDecimal sum,
                         BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
        this.user = user;
        this.bankName = bankName;
        this.start = start;
        this.monthNumber = monthNumber;
        this.sum = sum;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(CreditAccount creditAccount) {
        this.id = creditAccount.getId();
        this.user = creditAccount.getUser();
        this.bankName = creditAccount.getBankName();
        this.start = creditAccount.getStart();
        this.end = creditAccount.getEnd();
        this.monthNumber = creditAccount.getMonthNumber();
        this.sum = creditAccount.getSum();
        this.monthPayment = creditAccount.getMonthPayment();
        this.interestRate = creditAccount.getInterestRate();
        this.employee = creditAccount.getEmployee();
        this.paymentAccount = creditAccount.getPaymentAccount();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getMonthPayment() {
        return monthPayment;
    }

    public void setMonthPayment(BigDecimal monthPayment) {
        this.monthPayment = monthPayment;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {
        return "CreditAccount{" +
                "id=" + id +
                ", user=" + user.getFullName() +
                ", bankName='" + bankName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", monthNumber=" + monthNumber +
                ", sum=" + sum +
                ", monthPayment=" + monthPayment +
                ", interestRate=" + interestRate +
                "%, employee=" + employee.getFullName() +
                ", paymentAccount=" + paymentAccount.getId() +
                '}';
    }
}
