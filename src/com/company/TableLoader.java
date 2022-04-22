package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TableLoader implements TariffLoader{
    @Override
    public ArrayList<Tariff> loadTariff() {
        ArrayList<Tariff> tariffs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("filtersTable.csv"));
            br.readLine();
            String tuple="";
            while ((tuple=br.readLine()) != null){
                String[] tupleMassive =tuple.split(";");
                tariffs.add(new Tariff(Integer.parseInt(tupleMassive[0]),Integer.parseInt(tupleMassive[1])));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return tariffs;
    }

}
