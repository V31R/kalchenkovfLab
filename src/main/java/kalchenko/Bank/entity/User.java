package kalchenko.Bank.entity;

import java.math.BigDecimal;
import java.util.Date;

public class User {

    private Long id;
    private String fullName;
    private Date birthDate;
    private BigDecimal salary;
    private String job;
    private Bank bank;
    private int creditRate = 0;

    public User() {}

    public User(Long id, String fullName, Date birthDate, BigDecimal salary,
                String job, Bank bank) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.job = job;
        this.bank = bank;
    }

    public User(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.birthDate = user.getBirthDate();
        this.salary = user.getSalary();
        this.job = user.getJob();
        this.bank = user.getBank();
        this.creditRate = user.getCreditRate();
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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
                ", bank=" + bank +
                ", creditRate=" + creditRate +
                '}';
    }
}
