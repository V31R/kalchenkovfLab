package kalchenko.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements Entity {

    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private BigDecimal salary;
    private String job;
    private List<Bank> banks = new ArrayList<>();
    private int creditRate = 0;

    public User() {
    }

    public User(String fullName, LocalDate birthDate, BigDecimal salary,
                String job, Bank bank) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.job = job;
        this.banks.add(bank);
    }

    public User(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.birthDate = user.getBirthDate();
        this.salary = user.getSalary();
        this.job = user.getJob();
        this.banks = user.getBanks();
        this.creditRate = user.getCreditRate();
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void addBank(Bank bank) {
        this.banks.add(bank);
    }

    public int getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(int creditRate) {
        this.creditRate = creditRate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                ", bank=" + banks.stream().map(Bank::getName).toList() +
                ", creditRate=" + creditRate +
                '}';
    }
}
