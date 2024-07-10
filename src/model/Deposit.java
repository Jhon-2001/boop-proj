package model;

import java.sql.Date;

public class Deposit {
    private int id;
    private int accountId;
    private double amount;
    private Date startDate;
    private Date endDate;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
