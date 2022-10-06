package kalchenko.Bank.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CreditAccount {

    Long id;
    User user;
    String bankName;
    Date start;
    Date end;
    int monthNumber;
    BigDecimal sum;
    BigDecimal monthPayment;
    BigDecimal interestRate;
    Employee employee;

    public CreditAccount() {}

    public CreditAccount(Long id, User user, String bankName, Date start, Date end, int monthNumber, BigDecimal sum,
                         BigDecimal monthPayment, BigDecimal interestRate, Employee employee) {
        this.id = id;
        this.user = user;
        this.bankName = bankName;
        this.start = start;
        this.end = end;
        this.monthNumber = monthNumber;
        this.sum = sum;
        this.monthPayment = monthPayment;
        this.interestRate = interestRate;
        this.employee = employee;
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
    }

    public Long getId() {
        return id;
    }

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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

}