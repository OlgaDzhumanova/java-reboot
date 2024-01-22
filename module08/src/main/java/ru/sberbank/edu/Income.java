package ru.sberbank.edu;

public class Income {
    private double sum;
    private double percentage;
    private int years;

    private double totalAmount;

    public Income(double sum, double percentage, int years) {
        this.sum = sum;
        this.percentage = percentage;
        this.years = years;
        this.totalAmount = counter(sum, percentage, years);
    }

    public double counter(double sum, double percentage, int years) {
        double percentageForYear = (sum * percentage) / 100;
        return sum + (percentageForYear * years);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "Income{" +
                "sum=" + sum +
                ", percentage=" + percentage +
                ", years=" + years +
                '}';
    }
}