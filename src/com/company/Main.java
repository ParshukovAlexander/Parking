//namespace SmartParkingApp
//        {
//class Tariff
//{
//    public int Minutes { get; set; }
//    public decimal Rate { get; set; }
//}
//}
package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        ParkingManager parkingManager =new ParkingManager(20,new MemoryLoader());
        parkingManager.EnterParking("123");
        parkingManager.EnterParking("124");
        System.out.println(parkingManager.TryLeaveParkingWithTicket(1));
        System.out.println(parkingManager.GetRemainingCost(2));
    }
}
