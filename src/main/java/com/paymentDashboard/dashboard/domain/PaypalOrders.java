package com.paymentDashboard.dashboard.domain;

import java.util.List;

public class PaypalOrders {

    private String intent;
    private Payer payer;
    private List<Transaction> transactions;

    public PaypalOrders(String intent, Payer payer, List<Transaction> transactions) {
        this.intent = intent;
        this.payer = payer;
        this.transactions = transactions;
    }

    // Getters and setters


    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static class Payer {
        private String payment_method;

        // Getters and setters

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }
    }

    public static class Transaction {
        private Amount amount;
        private String description;

        // Getters and setters

        public Amount getAmount() {
            return amount;
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class Amount {
        private String total;
        private String currency;

        // Getters and setters

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }
}

