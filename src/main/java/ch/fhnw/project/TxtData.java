package ch.fhnw.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtData{
    private String name;
    private ArrayList<Double> vars = new ArrayList<>();

    public  TxtData(String pfad, int welcheVariable){
        try {
            BufferedReader br = new BufferedReader(new FileReader(pfad));
            String[] names = br.readLine().split("\\t"); //nimmt erste zeile und speichert einzelne werte in array getrennt durch tab
            this.name = names[welcheVariable]; // speichert name der variable

            // array mit werten erstellen
            String zwischen;
            while( (zwischen = br.readLine()) != null){
                names = zwischen.split("\\t");
                this.vars.add(Double.parseDouble(names[welcheVariable])); // erstellt Array mit zahlen
            }
            br.close();
        }catch (IOException e){
            System.out.println("error");
        }
    }

    public ArrayList<Double> gatVar(){
        return this.vars;
    }
    public String getName(){
        return this.name;
    }
}