package kalchenko.Bank.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Employee {
    Long id;
    String fullName;
    Date birthDate;
    String job;
    boolean inOffice;
    BankOffice bankOffice;
    boolean canApplyLoan;
    BigDecimal salary;

    public Employee() {}

    public Employee(Long id, String fullName, Date birthDate, String job, boolean inOffice,
                    BankOffice bankOffice, boolean canApplyLoan, BigDecimal salary) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.job = job;
        this.inOffice = inOffice;
        this.bankOffice = bankOffice;
        this.canApplyLoan = canApplyLoan;
        this.salary = salary;
    }

    public Employee(Employee employee) {
        this.id = employee.getId();
        this.fullName = employee.getFullName();
        this.birthDate = employee.getBirthDate();
        this.job = employee.getJob();
        this.inOffice = employee.inOffice;
        this.bankOffice = employee.getBankOffice();
        this.canApplyLoan = employee.canApplyLoan;
        this.salary = employee.getSalary();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isInOffice() {
        return inOffice;
    }

    public void setInOffice(boolean inOffice) {
        this.inOffice = inOffice;
    }

    public BankOffice getBankOffice() {
        return bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public boolean isCanApplyLoan() {
        return canApplyLoan;
    }

    public void setCanApplyLoan(boolean canApplyLoan) {
        this.canApplyLoan = canApplyLoan;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", job='" + job + '\'' +
                ", inOffice=" + inOffice +
                ", bankOffice=" + bankOffice +
                ", canApplyLoan=" + canApplyLoan +
                ", salary=" + salary +
                '}';
    }
}
