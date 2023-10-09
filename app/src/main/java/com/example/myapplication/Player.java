package com.example.myapplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class Player {
    private String nom;
    private int intents, numeroadivinat;
    private int temps;

    public Player(String nom,int intents, int temps){
        this.nom = nom;
        this.intents = intents;
        this.temps = temps;
    }
}
