package com.example.homebanking.DTOS;

    public class PaymentsDTO {

        private String number;
        private int cvv;
        private double amount;
        private String description;
        private String thruDate;
        private String name;




        public PaymentsDTO() {
        }

        public PaymentsDTO(String number, int cvv, double amount, String description, String thruDate, String name) {
            this.number = number;
            this.cvv = cvv;
            this.amount = amount;
            this.description = description;
            this.thruDate = thruDate;
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getCvv() {
            return cvv;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getThruDate() {
            return thruDate;
        }

        public void setThruDate(String thruDate) {
            this.thruDate = thruDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

