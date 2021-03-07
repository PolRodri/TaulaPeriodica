package com.example.taulaperiodica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ActivityJoc extends AppCompatActivity {

    ArrayList<Element> elements = MainActivity.Elements;
    int punts = 0;
    Random alea = new Random();
    int i = alea.nextInt(elements.size());
    String textIntroduit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Joc Taula Periòdica");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_joc);

        TextView puntuacioMax = findViewById(R.id.PuntuacioMax);
        puntuacioMax.setText(String.valueOf(PuntuacioMax.getInstance().puntuacioMax));

        TextView edt = findViewById(R.id.nom);
        edt.setText(elements.get(i).getNom());


        Button validaBoto = findViewById(R.id.btnValida);

        validaBoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView simbolText = findViewById(R.id.simbol);
                textIntroduit = simbolText.getText().toString();

                String simbol = elements.get(i).getSimbol();

                TextView puntuacio = findViewById(R.id.punts);

                if (textIntroduit.equalsIgnoreCase(simbol)) {
                    int punts = incrementaPunts();
                    puntuacio.setText(String.valueOf(punts));

                } else {
                    if(punts > PuntuacioMax.getInstance().puntuacioMax){
                        PuntuacioMax.getInstance().puntuacioMax = punts;
                    }
                    int punts = decrementaPunts();
                    puntuacio.setText(String.valueOf(punts));
                    tornaMainMenu();
                }

                i = alea.nextInt(elements.size());
                TextView edt = findViewById(R.id.nom);
                edt.setText(elements.get(i).getNom());
            }
        });


    }

    // Capturar pulsacions en el menú de la barra superior.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int incrementaPunts() {
        punts++;
        return punts;
    }

    private int decrementaPunts() {
        punts = 0;
        return punts;
    }

    private void tornaMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

}