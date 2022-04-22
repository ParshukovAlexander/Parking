package com.company;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class ParkingManager {

    private ArrayList<ParkingSession> parkingSessionsActive = new ArrayList<>();
    private ArrayList<ParkingSession> parkingSessionsNotActive = new ArrayList<>();
    private final int parkingCapacity;
    private ArrayList<Tariff> tariffs = new ArrayList<>();

    public ParkingManager(int capacity, TariffLoader loader) {
        parkingCapacity = capacity;
        tariffs = loader.loadTariff();
        tariffs.sort(Comparator.comparing(Tariff::getMinutes));
    }

    public ParkingSession EnterParking(String carPlateNumber) {
        if (searchFreeCapacity() == 0) return null;
        else if (parkingSessionsActive.stream().anyMatch(t -> t.getCarPlateNumber()
                .equalsIgnoreCase(carPlateNumber))) throw new IllegalArgumentException("Error");
        else {
            ParkingSession parkingSession = new ParkingSession(TimeHelper.getInstance().getCurrentDate(), carPlateNumber);
            parkingSessionsActive.add(parkingSession);
            return parkingSession;
        }
        /* Check that there is a free parking place (by comparing the parking capacity
         * with the number of active parking sessions). If there are no free places, return null
         *
         * Also check that there are no existing active sessions with the same car plate number,
         * if such session exists, also return null
         *
         * Otherwise:
         * Create a new Parking session, fill the following properties:
         * EntryDt = current date time
         * CarPlateNumber = carPlateNumber (from parameter)
         * TicketNumber = unused parking ticket number = generate this programmatically
         *
         * Add the newly created session to the list of active sessions
         *
         * Advanced task:
         * Link the new parking session to an existing user by car plate number (if such user exists)
         */
    }

    private int searchFreeCapacity() {
        return parkingCapacity - parkingSessionsActive.size();
    }

    public boolean TryLeaveParkingWithTicket(int ticketNumber) {
        Optional<ParkingSession> optionalSession = parkingSessionsActive.
                stream().filter(t -> t.getTicketNumber() == ticketNumber).findFirst();
        if (optionalSession.isEmpty()) throw new IllegalArgumentException("Error");
        if (GetRemainingCost(ticketNumber) <= 0) {
            optionalSession.get().setExitDt(TimeHelper.getInstance().getCurrentDate());
            parkingSessionsActive.remove(optionalSession.get());
            parkingSessionsNotActive.add(optionalSession.get());
            return true;
        }
        return false;
        /*
         * Убедитесь, что автомобиль покидает парковку в период бесплатного выезда
         * из PaymentDt (или, если оплата не производилась, из EntryDt)
         * 1. Если да:
         * 1.1 Завершите сеанс парковки, установив свойство ExitDt
         * 1.2 Переместить сеанс из списка активных сеансов в список прошедших сеансов *
         * 1.3 вернуть true и объект завершенной парковочной сессии в параметре out
         *
         * 2. В противном случае вернуть false, session = null
         */
/*
        /*
         * Check that the car leaves parking within the free leave period
         * from the PaymentDt (or if there was no payment made, from the EntryDt)
         * 1. If yes:
         *   1.1 Complete the parking session by setting the ExitDt property
         *   1.2 Move the session from the list of active sessions to the list of past sessions             *
         *   1.3 return true and the completed parking session object in the out parameter
         *
         * 2. Otherwise, return false, session = null
         */
    }

    public void payForParking(int ticketNumber, double amount)
    {
        Optional<ParkingSession> optionalSession = parkingSessionsActive.
                stream().filter(t -> t.getTicketNumber() == ticketNumber).findFirst();
        optionalSession.get().setTotalPayment(amount);
        optionalSession.get().setPaymentDt(TimeHelper.getInstance().getCurrentDate());
        /*
         * Save the payment details in the corresponding parking session
         * Set PaymentDt to current date and time
         *
         * For simplicity we won't make any additional validation here and always
         * assume that the parking charge is paid in full
         */
        /*
         * Сохраните платежные реквизиты в соответствующей парковочной сессии
         * Установите PaymentDt на текущую дату и время
         *
         * Для простоты мы не будем делать дополнительную проверку здесь и всегда
         * предполагается, что плата за парковку оплачена полностью
         */
    }
    private double difference(Calendar instance, Calendar paymentDt) {
        return (instance.getTimeInMillis() - paymentDt.getTimeInMillis()) / 60000.0;
    }

    public double GetRemainingCost(int ticketNumber) {
        Optional<ParkingSession> optionalSession = parkingSessionsActive.
                stream().filter(t -> t.getTicketNumber() == ticketNumber).findFirst();
        Calendar date;
        if (optionalSession.get().getPaymentDt() == null) {
            date = optionalSession.get().getEntryDt();
        } else date = optionalSession.get().getPaymentDt();

        Optional<Tariff> tariffRes = tariffs.stream().
                filter(t -> t.getMinutes() >= difference(TimeHelper.getInstance().getCurrentDate(), date)).findFirst();
        if (tariffRes.isEmpty()) tariffRes = Optional.ofNullable(tariffs.get(tariffs.size() - 1));
        return tariffRes.get().getRate() ;
        /* Возвращаем сумму к оплате за парковку
         * Если оплата уже была произведена, но была начислена доплата
         * из-за позднего выхода этот метод должен возвращать сумму
         * который еще не оплачен (не полная стоимость)
         */
        /* Return the amount to be paid for the parking
         * If a payment had already been made but additional charge was then given
         * because of a late exit, this method should return the amount
         * that is yet to be paid (not the total charge)
         */
    }
}
