package com.company;

import java.util.Calendar;

public class ParkingSession {
    private Calendar entryDt;
    private Calendar paymentDt;
    private Calendar exitDt;
    private double totalPayment=0;
    private String carPlateNumber;
    private int ticketNumber;
    private static int counter = 0;



    public ParkingSession(Calendar entryDt, String carPlateNumber) {
        this.entryDt = entryDt;
        this.carPlateNumber = carPlateNumber;
        ticketNumber = ++counter;
    }

    public Calendar getEntryDt() {
        return entryDt;
    }

    public void setEntryDt(Calendar entryDt) {
        this.entryDt = entryDt;
    }

    public Calendar getPaymentDt() {
        return paymentDt;
    }

    public void setPaymentDt(Calendar paymentDt) {
        this.paymentDt = paymentDt;
    }

    public Calendar getExitDt() {
        return exitDt;
    }

    public void setExitDt(Calendar exitDt) {
        this.exitDt = exitDt;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        ParkingSession.counter = counter;
    }

}
