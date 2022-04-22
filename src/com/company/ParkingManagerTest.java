package com.company;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ParkingManagerTest {
    ParkingManager parkingManager;
   final  String carNumber = "123";
    @Before
    public void setUp(){
     parkingManager = new ParkingManager(4, new MemoryLoader());

    }
    @Test
    public void enterParking() {
        Calendar calendar = new GregorianCalendar(2022, 03, 12, 17, 40);
        TimeHelper.getInstance().setCurrentDate(calendar);
        ParkingSession session = parkingManager.EnterParking(carNumber);
        assertNotNull(session);
        assertEquals(carNumber, session.getCarPlateNumber());
        assertEquals(1, session.getTicketNumber());
        assertEquals(calendar, session.getEntryDt());
    }

    @Test
    public void tryLeaveParkingWithTicketFreePeriod() {
        Calendar calendar = new GregorianCalendar(2022, 03, 12, 17, 40);
        TimeHelper.getInstance().setCurrentDate(calendar);
        ParkingSession session = parkingManager.EnterParking(carNumber);
        Calendar calendarFin = new GregorianCalendar(2022, 03, 12, 17, 45);
        TimeHelper.getInstance().setCurrentDate(calendarFin);
        assertTrue(parkingManager.TryLeaveParkingWithTicket(session.getTicketNumber()));
    }

    @Test
    public void tryLeaveParkingWithTicketNoFreePeriod() {
        Calendar calendar = new GregorianCalendar(2022, 03, 12, 17, 40);
        TimeHelper.getInstance().setCurrentDate(calendar);
        ParkingSession session = parkingManager.EnterParking(carNumber);
        Calendar calendarFin = new GregorianCalendar(2022, 03, 12, 18, 45);
        TimeHelper.getInstance().setCurrentDate(calendarFin);
        assertFalse(parkingManager.TryLeaveParkingWithTicket(session.getTicketNumber()));
    }

    @Test
    public void tryLeaveParkingWithTicketPay() {
        Calendar calendar = new GregorianCalendar(2022, 03, 12, 17, 40);
        TimeHelper.getInstance().setCurrentDate(calendar);
        ParkingSession session = parkingManager.EnterParking(carNumber);
        Calendar calendarFin = new GregorianCalendar(2022, 03, 12, 18, 45);
        TimeHelper.getInstance().setCurrentDate(calendarFin);
        double cost=parkingManager.GetRemainingCost(session.getTicketNumber());
        parkingManager.payForParking(session.getTicketNumber(),cost);
        Calendar calendarFinSecond = new GregorianCalendar(2022, 03, 12, 18, 50);
        assertTrue(parkingManager.TryLeaveParkingWithTicket(session.getTicketNumber()));
        //Функцию,Тесты,javaDoc,
    }

    @Test
    public void getRemainingCost() {
        Calendar calendar = new GregorianCalendar(2022, 03, 12, 17, 40);
        TimeHelper.getInstance().setCurrentDate(calendar);
        ParkingSession session = parkingManager.EnterParking(carNumber);
        Calendar calendarFin = new GregorianCalendar(2022, 03, 12, 17, 45);
        TimeHelper.getInstance().setCurrentDate(calendarFin);
        assertEquals(parkingManager.GetRemainingCost(session.getTicketNumber()),0.0,0.01);
    }

    @Test
    public void payForParking() {
        Calendar calendar = new GregorianCalendar(2022, 03, 12, 17, 40);
        TimeHelper.getInstance().setCurrentDate(calendar);
        ParkingSession session = parkingManager.EnterParking(carNumber);
        Calendar calendarFin = new GregorianCalendar(2022, 03, 12, 18, 45);
        TimeHelper.getInstance().setCurrentDate(calendarFin);
        double res = parkingManager.GetRemainingCost(session.getTicketNumber());
        parkingManager.payForParking(session.getTicketNumber(),res);
        assertEquals(res,session.getTotalPayment(),0.01);
        assertEquals(calendarFin,session.getPaymentDt());
//
//        assertTrue(parkingManager.payForParking(session.getTicketNumber(),0.0));
    }
}