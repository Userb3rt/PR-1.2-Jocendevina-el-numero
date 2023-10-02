package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int numero_Aleatorio = (int)(Math.random()*100+1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button_validar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                while (numero_Aleatorio != 101){ //numero que no aparece en el rango, luego hare que el numero random sea 101 para poder cambiarlo.
                    String Mensaje;
                    EditText et1;
                    et1 = (EditText)findViewById(R.id.numero_jugador);
                    String numero_escrito = et1.getText().toString();
                    int numero_escrito_int = Integer.parseInt(numero_escrito);

                    if (numero_escrito_int > numero_Aleatorio){
                        Mensaje = "El numero introducido es mas grande que el Aleatorio.";
                    }else if(numero_escrito_int < numero_Aleatorio){
                        Mensaje = "El numero introducido es mas pequeño que el Aleatorio.";

                    }else {
                        Mensaje = "Has adivinado el Número!!!";
                        int numero_Aleatorio = (int)(Math.random()*100+1);
                        break;
                    }
                    Toast.makeText(MainActivity.this,Mensaje, Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

}