package kalchenko.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee implements Entity{
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String job;
    private boolean inOffice;
    private BankOffice bankOffice;
    private boolean loansAvailable;
    private BigDecimal salary;

    public Employee() {}

    public Employee(String fullName, LocalDate birthDate, String job, boolean inOffice,
                    BankOffice bankOffice, boolean canApplyLoan, BigDecimal salary) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.job = job;
        this.inOffice = inOffice;
        this.bankOffice = bankOffice;
        this.loansAvailable = canApplyLoan;
        this.salary = salary;
    }

    public Employee(Employee employee) {
        this.id = employee.getId();
        this.fullName = employee.getFullName();
        this.birthDate = employee.getBirthDate();
        this.job = employee.getJob();
        this.inOffice = employee.inOffice;
        this.bankOffice = employee.getBankOffice();
        this.loansAvailable = employee.loansAvailable;
        this.salary = employee.getSalary();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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

    public boolean isLoansAvailable() {
        return loansAvailable;
    }

    public void setLoansAvailable(boolean loansAvailable) {
        this.loansAvailable = loansAvailable;
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
                ", bankOffice=" + bankOffice.getName() +
                ", canApplyLoan=" + loansAvailable +
                ", salary=" + salary +
                '}';
    }
}
