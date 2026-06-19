package utils;

import java.util.ArrayList;
import java.util.List;

public class CompatibiliteSang {

    public static List<String> groupesCompatibles(String groupe) {

        List<String> liste = new ArrayList<>();

        switch (groupe) {

            case "O-":
                liste.add("O-");
                break;

            case "O+":
                liste.add("O+");
                liste.add("O-");
                break;

            case "A-":
                liste.add("A-");
                liste.add("O-");
                break;

            case "A+":
                liste.add("A+");
                liste.add("A-");
                liste.add("O+");
                liste.add("O-");
                break;

            case "B-":
                liste.add("B-");
                liste.add("O-");
                break;

            case "B+":
                liste.add("B+");
                liste.add("B-");
                liste.add("O+");
                liste.add("O-");
                break;

            case "AB-":
                liste.add("AB-");
                liste.add("A-");
                liste.add("B-");
                liste.add("O-");
                break;

            case "AB+":
                liste.add("AB+");
                liste.add("AB-");
                liste.add("A+");
                liste.add("A-");
                liste.add("B+");
                liste.add("B-");
                liste.add("O+");
                liste.add("O-");
                break;

        }

        return liste;

    }

}