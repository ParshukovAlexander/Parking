package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryLoader implements TariffLoader{

    @Override
    public ArrayList<Tariff> loadTariff() {
        Tariff tariff= new Tariff(15,0);
        Tariff tariff1= new Tariff(60,50);
        Tariff tariff2= new Tariff(120,100);
        Tariff tariff3= new Tariff(600,350);
        ArrayList<Tariff> tariffs=new ArrayList<>(List.of(tariff,tariff1,tariff2,tariff3));
        return  tariffs;
    }
}
