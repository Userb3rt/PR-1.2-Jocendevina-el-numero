package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    //int numero_Aleatorio = (int)(Math.random()*100+1);
    int numero_Aleatorio = 4;
    int numerodeintentos = 1;
    ArrayList<Player> Records = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AlertDialog.Builder builderinsertardatos = new AlertDialog.Builder(MainActivity.this);


        super.onCreate(savedInstanceState);

        Date fechainici = new Date();


        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button_validar);
        TextView historial = findViewById(R.id.textViewintents);
        TextView ranking = findViewById(R.id.ranking);
        String anteriorh = "";
        ArrayList<Integer> rankinglist = new ArrayList<Integer>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Mensaje;
                EditText et1;
                et1 = (EditText)findViewById(R.id.numero_jugador);
                String anteriorh = historial.getText().toString();
                String rankingtext = "";

                try {
                    String numero_escrito = et1.getText().toString();
                    int numero_escrito_int = Integer.parseInt(numero_escrito);
                    et1.setText("");
                    if (numero_escrito_int > numero_Aleatorio){
                        Mensaje = "El numero introducido es mas grande que el Aleatorio.";
                        historial.setText("Intento numero "+numerodeintentos+": "+numero_escrito+"\n"+anteriorh);
                        numerodeintentos++;

                    }else if(numero_escrito_int < numero_Aleatorio){
                        Mensaje = "El numero introducido es mas pequeño que el Aleatorio.";
                        historial.setText("Intento numero "+numerodeintentos+": "+numero_escrito+"\n"+anteriorh);
                        numerodeintentos++;
                    }else {
                        //Calculamos el tiempo que ha tardado en conseguir el numero:
                        int minutostrans = 0;
                        int segundostrans = 0;
                        Date fechafinal = new Date();
                        long tiempo_trancurrido = fechafinal.getTime()-fechainici.getTime();
                        TimeUnit unidad = TimeUnit.SECONDS;
                        long Segundos =unidad.convert(tiempo_trancurrido,TimeUnit.MILLISECONDS);
                        if (Long.valueOf(Segundos).intValue()>=60){minutostrans = Long.valueOf(Segundos).intValue()/60;segundostrans = Long.valueOf(Segundos).intValue()%60;}
                        Toast.makeText(MainActivity.this,minutostrans+"' "+segundostrans+"''", Toast.LENGTH_SHORT).show();


                        Mensaje = "Has adivinado el Número!!!";
                        builderinsertardatos.setTitle("¡ACERTASTE EL NÚMERO!");
                        builderinsertardatos.setMessage("Quieres guardar tu record?\nEscribe tu usuario:");
                        EditText et = new EditText(MainActivity.this);
                        builderinsertardatos.setView(et);
                        builderinsertardatos.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nomplayer = et.getText().toString();
                                int intentosnewplayer = numerodeintentos;
                                int numero_alto = numero_Aleatorio;

                                Player player = new Player(nomplayer,intentosnewplayer,1);


                            }
                        });
                        AlertDialog dialoginsertar = builderinsertardatos.create();
                        dialoginsertar.show();








                        //numero_Aleatorio = (int)(Math.random()*100+1);
                        numero_Aleatorio = 2;
                        historial.setText("");





                        if (rankinglist.size() == 0){rankinglist.add(numerodeintentos);}
                        else if (!rankinglist.contains(numerodeintentos)){
                            rankinglist.add(numerodeintentos);
                            Collections.sort(rankinglist);
                        }
                        for (int i = 0; i<rankinglist.size();i++){
                            if (i == 0){
                                rankingtext += "Best puntuación: "+ rankinglist.get(i)+"\n";
                            }else{rankingtext += i+1+"º intento: "+rankinglist.get(i)+"\n";}
                        }
                        ranking.setText(rankingtext);
                        numerodeintentos = 1;

                    }
                }catch(Exception e){
                    Mensaje = "Has de introducir un valor valido del 1 al 100!";
                }

                Toast.makeText(MainActivity.this,Mensaje, Toast.LENGTH_SHORT).show();

            }
        });

        Button seeactivityranking = findViewById(R.id.seeactivityrecords);
        seeactivityranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);

            }
        });

    }

}