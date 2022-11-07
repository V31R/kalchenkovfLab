package kalchenko.bank.entity;

import java.math.BigDecimal;

public class PaymentAccount implements Entity{

    private Long id;
    private User user;
    private String bankName;
    private BigDecimal sum = BigDecimal.ZERO;

    public PaymentAccount() {}

    public PaymentAccount(User user, String bankName) {
        this.user = user;
        this.bankName = bankName;
    }

    public PaymentAccount(PaymentAccount paymentAccount) {
        this.id = paymentAccount.getId();
        this.user = paymentAccount.getUser();
        this.bankName = paymentAccount.getBankName();
        this.sum = paymentAccount.getSum();
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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "PaymentAccount{" +
                "id=" + id +
                ", user=" + user +
                ", bankName='" + bankName + '\'' +
                ", sum=" + sum +
                '}';
    }
}
