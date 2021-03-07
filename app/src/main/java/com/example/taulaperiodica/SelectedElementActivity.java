package com.example.taulaperiodica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_element);

        Bundle bundle = getIntent().getExtras();
        int nombreElement = bundle.getInt("nombreAtomic");
        final String nom = bundle.getString("nom");
        String simbol = bundle.getString("simbol");
        float pes = bundle.getFloat("pes");
        int serieQuimica = bundle.getInt("serieQuimica");
        int estat = bundle.getInt("estat");
        int grup = bundle.getInt("grup");
        int periode = bundle.getInt("periode");
        char bloc = bundle.getChar("bloc");
        String configuracio = bundle.getString("configuracio");
        String electrons = bundle.getString("electrons");

        setTitle(nom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //NOM
        TextView edt = findViewById(R.id.Nom);
        edt.setText(nom);
        //SIMBOL
        edt = findViewById(R.id.Simbol);
        edt.setText(simbol);
        //NUMERO
        edt = findViewById(R.id.Numero);
        edt.setText(String.valueOf(nombreElement));
        //Serie
        edt = findViewById(R.id.Serie);
        edt.setText(retornaSerie(serieQuimica));
        //Grup
        edt = findViewById(R.id.Grup);
        edt.setText(String.valueOf(grup));
        //Periode
        edt = findViewById(R.id.Periode);
        edt.setText(String.valueOf(periode));
        //Bloc
        edt = findViewById(R.id.Bloc);
        edt.setText(String.valueOf(bloc));
        //Pes
        edt = findViewById(R.id.Massa);
        edt.setText(String.valueOf(pes));
        //Configuracio
        edt = findViewById(R.id.Configuracio);
        edt.setText(configuracio);
        //Electrons
        edt = findViewById(R.id.Electrons);
        edt.setText(electrons);
        //BOTO
        Button btnWeb = findViewById(R.id.botoWeb);
        Button btnEnvia = findViewById(R.id.botoEnvia);

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaPaginaWeb(nom);
            }
        });

        btnEnvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickWhatsApp(v, nom);
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

    private void enviaPaginaWeb(String nom){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ca.wikipedia.org/wiki/" + nom));
        startActivity(intent);
    }

    private String retornaSerie(int serieNum){
        switch (serieNum) {
            // Respond to the action bar's Up/Home button
            case 1:
                return "Metalls alcalins";
            case 2:
                return "Alcalinoterris";
            case 3:
                return "Lantanoides";
            case 4:
                return "Actinoides";
            case 5:
                return "Metalls de transició";
            case 6:
                return "Metalls del bloc p";
            case 7:
                return "Metal·loides";
            case 8:
                return "No metalls";
            case 9:
                return "Halògens";
            case 10:
                return "Gasos nobles";
            default:
                return "NA";


        }
    }

    public void onClickWhatsApp(View view, String nom) {

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "https://ca.wikipedia.org/wiki/" + nom;

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }
}