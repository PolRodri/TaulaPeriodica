package com.example.taulaperiodica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //serieQuimica
    /*
        1: metalls alcalins
        2: Alcalinoterris
    3: Lantanoides
    4: Actinoides
        5: Metalls de transició
    6: metalls del bloc p
        7: metal·loides
        8: no metalls
        9: halògens
        10: gasos nobles
    */
    //estat
    /*1->blauLiquid 2->verdGas 3->negreSolid 4->vermellSintetic*/

    public static ArrayList<Element> Elements = new ArrayList<Element>();

    private  AdaptadorElements adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicialitzaElements();
        // Personalitzem el caption
        setTitle("Llista Elements");

        // Mostrem el botó enrera que cal capturar en l'envent onOptionsItemSelected
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Construim l'adaptador utilitzant un layout per defecte de Android.
        adaptador = new AdaptadorElements(this, Elements);
        //notifyAll

        // Assignem al listview l'adaptador creat
        ListView lst = (ListView)findViewById(R.id.listCustom);
        lst.setAdapter(adaptador);

        // Capturem el clic d'un element de la listview
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                // Agafem l'objecte associat, en aquest cas l'objecte es un STRING
                Element opcioSeleccionada = (Element) a.getItemAtPosition(position);
                mostraInfoElement(opcioSeleccionada);
            }
        });

    }

    protected void onDestroy(){
        super.onDestroy();
        adaptador.clear();
    }
    //INTENTS
    private void mostraInfoElement(Element elementSeleccionat){
        Intent intent = new Intent(this, SelectedElementActivity.class);
        Bundle b = new Bundle();

        b.putInt("nombreAtomic", elementSeleccionat.getNombreAtomic());
        b.putString("nom", elementSeleccionat.getNom());
        b.putString("simbol", elementSeleccionat.getSimbol());
        b.putFloat("pes", elementSeleccionat.getPes());
        b.putInt("serieQuimica", elementSeleccionat.getSerieQuimica());
        b.putInt("estat", elementSeleccionat.getEstat());
        b.putInt("grup", elementSeleccionat.getGrup());
        b.putInt("periode", elementSeleccionat.getPeriode());
        b.putChar("bloc", elementSeleccionat.getBloc());
        b.putString("configuracio", elementSeleccionat.configuracioElectronica());
        b.putString("electrons", elementSeleccionat.electronsPerNivell());

        intent.putExtras(b);
        //intent.putExtra("element", (Parcelable) elementSeleccionat);
        startActivity(intent);
    }

    private void enviaJoc(){
        Intent intent = new Intent(this, ActivityJoc.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar_layout_main, menu);
        return true;
    }

    // Capturar pulsacions en el menú de la barra superior.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //adaptador.clear();
       // adaptador.notifyDataSetChanged();

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
            case R.id.joc:
                enviaJoc();
                return true;
            case R.id.Tots:
                adaptador.clear();
                inicialitzaElements();
                adaptador.notifyDataSetChanged();
                Toast.makeText(this,"TOTS", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Liquids:
                taulaElementsEspecifics(1);
                Toast.makeText(this,"LIQUIDS", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Gasos:
                taulaElementsEspecifics(2);
                Toast.makeText(this,"GASOS", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Solids:
                taulaElementsEspecifics(3);
                Toast.makeText(this,"SOLIDS", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Sintetics:
                taulaElementsEspecifics(4);
                Toast.makeText(this,"SINTETICS", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void taulaElementsEspecifics(int estat){
        adaptador.clear();
        inicialitzaElements();
        ArrayList<Element> elementsEspecifics = new ArrayList<Element>();

        for (int i = 0; i < Elements.size(); i++){
            if(Elements.get(i).getEstat() == estat){
                elementsEspecifics.add(Elements.get(i));
            }
        }
        //Toast.makeText(this,String.valueOf(elementsEspecifics.size()), Toast.LENGTH_LONG).show();

        //netejar adaptador
        adaptador.clear();
        //injectar nova taula
        adaptador.addAll(elementsEspecifics);
        //avisar del update
        adaptador.notifyDataSetChanged();

    }

    private void inicialitzaElements(){
        Elements.add(new Element(1, "H", "Hidrogen", 1.008f, 8,2, 1, 1, 's'));
        Elements.add(new Element(2, "He", "Heli", 4.0026f, 10, 2, 18, 1, 's'));
        Elements.add(new Element(3, "Li", "Liti", 6.94f, 1, 3, 1, 2, 's'));
        Elements.add(new Element(4, "Be", "Berili", 9.0122f, 2, 3, 2, 2, 's'));
        Elements.add(new Element(5, "B","Bor", 10.81f, 7,3, 13, 2, 'p'));
        Elements.add(new Element(6, "C","Carboni", 12.011f, 8, 3, 14, 2, 'p'));
        Elements.add(new Element(7, "N","Nitrogen", 14.007f, 8, 2, 15, 2, 'p'));
        Elements.add(new Element(8, "O","Oxigen", 15.999f, 8, 2, 16, 2, 'p'));
        Elements.add(new Element(9, "F", "Fluor", 18.999f, 9, 2, 17, 2, 'p'));
        Elements.add(new Element(10, "Ne", "Neo", 20.180f, 10, 2, 18, 2, 'p'));
        Elements.add(new Element(11, "Na", "Sodi", 22.990f, 1, 3, 1, 3, 's'));
        Elements.add(new Element(12, "Mg", "Magnesi", 24.305f, 2, 3, 2, 3, 's'));
        Elements.add(new Element(13, "Al", "Alumini", 26.982f, 6, 3, 13, 3, 'p'));
        Elements.add(new Element(14, "Si", "Silici", 28.085f, 7, 3, 14, 3, 'p'));
        Elements.add(new Element(15, "P", "Fòsfor", 30.974f, 8,3, 15, 3, 'p'));
        Elements.add(new Element(16, "S", "Sofre", 32.06f, 8, 3, 16, 3, 'p'));
        Elements.add(new Element(17, "Cl", "Clor", 35.45f, 9, 2, 17, 3, 'p'));
        Elements.add(new Element(18, "Ar", "Argó", 39.948f, 10, 2, 18, 3, 'p'));
        Elements.add(new Element(19, "K","Potassi", 39.098f, 1,3, 1, 4, 's'));
        Elements.add(new Element(20, "Ca","Calci", 40.078f, 2, 3, 2, 4, 's'));
        Elements.add(new Element(21, "Sc","Escandi", 44.956f, 5, 3, 3, 4, 'd'));
        Elements.add(new Element(22, "Ti","Titani", 47.867f, 5, 3, 4, 4, 'd'));
        Elements.add(new Element(23, "V", "Vanadi", 50.942f, 5, 3, 5, 4, 'd'));
        Elements.add(new Element(24, "Cr", "Crom", 51.996f, 5, 3, 6, 4, 'd'));
        Elements.add(new Element(25, "Mn", "Manganès", 54.938f, 5, 3, 7, 4, 'd'));
        Elements.add(new Element(26, "Fe", "Ferro", 55.845f, 5, 3, 8, 4, 'd'));
        Elements.add(new Element(27, "Co", "Cobalt", 58.933f, 5, 3, 9, 4, 'd'));
        Elements.add(new Element(28, "Ni", "Níquel", 58.693f, 5, 3, 10, 4, 'd'));
        Elements.add(new Element(29, "Cu", "Coure", 63.546f, 5, 3, 11, 4, 'd'));
        Elements.add(new Element(30, "Zn", "Zinc", 65.38f, 5, 3, 12, 4, 'd'));
        Elements.add(new Element(31, "Ga", "Gal·li", 69.723f, 6, 3, 13, 4, 'p'));
        Elements.add(new Element(32, "Ge", "Germani", 72.630f, 7, 3, 14, 4, 'p'));
        Elements.add(new Element(33, "As", "Arsènic", 74.922f, 7, 3, 15, 4, 'p'));
        Elements.add(new Element(34, "Se", "Seleni", 78.971f, 8, 3, 16, 4, 'p'));
        Elements.add(new Element(35, "Br", "Brom", 79.904f, 9, 1, 17, 4, 'p'));
        Elements.add(new Element(36, "Kr", "Criptó", 83.798f, 10, 2, 18, 4, 'p'));
        Elements.add(new Element(37, "Rb", "Rubidi", 85.4678f, 1, 2, 1, 5, 's'));
        Elements.add(new Element(38, "Sr", "Estronci", 87.62f, 2, 2, 2, 5, 's'));
        Elements.add(new Element(39, "Y", "Itri", 88.905f, 5, 2, 3, 5, 'd'));
        Elements.add(new Element(40, "Zr", "Zirconi", 91.224f, 5, 2, 4, 5, 'd'));
        Elements.add(new Element(41, "Nb", "Niobi", 92.906f, 5, 2, 5, 5, 'd'));
        Elements.add(new Element(42, "Mo", "Molibdè", 95.96f, 5, 2, 6, 5, 'd'));
        Elements.add(new Element(43, "Tc", "Tecneci", 98.00f, 5, 2, 7, 5, 'd'));
        Elements.add(new Element(44, "Ru", "Ruteni", 101.07f, 5, 2, 8, 5, 'd'));
        Elements.add(new Element(45, "Rh", "Rodi", 102.905f, 5, 2, 9, 5, 'd'));
        Elements.add(new Element(46, "Pd", "Pal", 106.42f, 5, 2, 10, 5, 'd'));
        Elements.add(new Element(47, "Ag", "Plata", 107.86f, 5, 2, 11, 5, 'd'));
        Elements.add(new Element(48, "Cd", "Cadmi", 112.411f, 5, 2, 12, 5, 'd'));
        Elements.add(new Element(49, "In", "Indi", 114.818f, 6, 2, 13, 5, 'p'));
        Elements.add(new Element(50, "Sn", "Estany", 118.710f, 6, 2, 14, 5, 'p'));
        Elements.add(new Element(51, "Sb", "Antimoni", 121.760f, 7, 3, 15, 5, 'p'));
        Elements.add(new Element(52, "Te", "Tel·luri", 127.60f, 7, 3, 16, 5, 'p'));
        Elements.add(new Element(53, "I", "Iode", 126.904f, 9, 3, 17, 5, 'p'));
        Elements.add(new Element(54, "Xe", "Xenó", 131.29f, 10, 2, 18, 5, 'p'));
        Elements.add(new Element(55, "Cs", "Cesi", 132.90f, 1, 3, 1, 6, 's'));
        Elements.add(new Element(56, "Ba", "Bari", 137.32f, 2, 3, 2, 6, 's'));
        Elements.add(new Element(57, "La", "Lantani", 138.91f, 3, 3, 4, 6, 'f'));
        Elements.add(new Element(58, "Ce", "Ceri", 140.116f, 6, 3, 5, 6, 'f'));
        Elements.add(new Element(59, "Pr", "Praseodimi", 140.907f, 3, 3, 6, 6, 'f'));
        Elements.add(new Element(60, "Nd", "Neodimi", 144.242f, 3, 3, 7, 6, 'f'));
        Elements.add(new Element(61, "Pm", "Prometeu", 145f, 3, 3, 8, 6, 'f'));
        Elements.add(new Element(62, "Sm", "Samari", 150.36f, 3, 3, 9, 6, 'f'));
        Elements.add(new Element(63, "Eu", "Europium", 151.964f, 3, 3, 10, 6, 'f'));
        Elements.add(new Element(64, "Gd", "Gadolinium", 157.25f, 3, 3, 11, 6, 'f'));
        Elements.add(new Element(65, "Tb", "Terbi", 158.925f, 3, 3, 12, 6, 'f'));
        Elements.add(new Element(66, "Dy", "Disprosi", 162.50f, 3, 3, 13, 6, 'f'));
        Elements.add(new Element(67, "Ho", "Holmium", 164.930f, 3, 3, 14, 6, 'f'));
        Elements.add(new Element(68, "Er", "Erbi", 167.259f, 3, 3, 15, 6, 'f'));
        Elements.add(new Element(69, "Tm", "Tuli", 168.934f, 3, 3, 16, 6, 'f'));
        Elements.add(new Element(70, "Yb", "Itterbi", 173.054f, 3, 3, 17, 6, 'f'));
        Elements.add(new Element(71, "Lu", "Luteci", 174.966f, 3, 3, 18, 6, 'f'));
        Elements.add(new Element(72, "Hf", "Hafnium", 178.49f, 5, 3, 4, 6, 'd'));
        Elements.add(new Element(73, "Ta", "Tàntal", 180.947f, 5, 3, 5, 6, 'd'));
        Elements.add(new Element(74, "W", "Tungstè", 183.84f, 5, 3, 6, 6, 'd'));
        Elements.add(new Element(75, "Re", "Reni", 186.207f, 5, 3, 7, 6, 'd'));
        Elements.add(new Element(76, "Os", "Osmi", 190.23f, 5, 3, 8, 6, 'd'));
        Elements.add(new Element(77, "Ir", "Iridi", 192.217f, 5, 3, 9, 6, 'd'));
        Elements.add(new Element(78, "Pt", "Platí", 195.084f, 5, 3, 10, 6, 'd'));
        Elements.add(new Element(79, "Au", "Or", 196.966f, 5, 3, 11, 6, 'd'));
        Elements.add(new Element(80, "Hg", "Mercuri", 200.59f, 5, 1, 12, 6, 'd'));
        Elements.add(new Element(81, "Tl", "Tal·li", 204.383f, 6, 3, 13, 6, 'p'));
        Elements.add(new Element(82, "Pb", "Plom", 207.2f, 6, 3, 14, 6, 'p'));
        Elements.add(new Element(83, "Bi", "Bismut", 208.980f, 6, 3, 15, 6, 'p'));
        Elements.add(new Element(84, "Po", "Polonium", 209f, 7, 3, 16, 6, 'p'));
        Elements.add(new Element(85, "At", "Astàtic", 1210f, 9, 3, 17, 6, 'p'));
        Elements.add(new Element(86, "Rn", "Radó", 222f, 10, 3, 18, 6, 'p'));
        Elements.add(new Element(87, "Fr", "Francium", 223f, 1, 3, 1, 7, 's'));
        Elements.add(new Element(88, "Ra", "Radi", 226f, 2, 3, 2, 7, 's'));
        Elements.add(new Element(89, "Ac", "Actini", 227.0f, 4, 3, 4, 7, 'f'));
        Elements.add(new Element(90, "Th", "Tori", 232.0380f, 4, 3, 5, 7, 'f'));
        Elements.add(new Element(91, "Pa", "Protactini", 231.035f, 4, 3, 6, 7, 'f'));
        Elements.add(new Element(92, "U", "Urani", 238.028f, 4, 3, 7, 7, 'f'));
        Elements.add(new Element(93, "Np", "Neptuni", 237f, 4, 3, 8, 7, 'f'));
        Elements.add(new Element(94, "Pu", "Plutoni", 244f, 4, 3, 9, 7, 'f'));
        Elements.add(new Element(95, "Am", "Americi", 243f, 4, 3, 10, 7, 'f'));
        Elements.add(new Element(96, "Cm", "Curium", 247f, 4, 3, 11, 7, 'f'));
        Elements.add(new Element(97, "Bk", "Berkelium", 247f, 4, 4, 12, 7, 'f'));
        Elements.add(new Element(98, "Cf", "Californium", 251f, 4, 4, 13, 7, 'f'));
        Elements.add(new Element(99, "Es", "Einsteinium", 252f, 4, 4, 14, 7, 'f'));
        Elements.add(new Element(100, "Fm", "Fermium", 257f, 4, 4, 15, 7, 'f'));
        Elements.add(new Element(101, "Md", "Mendelevi", 258f, 4, 4, 16, 7, 'f'));
        Elements.add(new Element(102, "No", "Nobelium", 259f, 4, 4, 17, 7, 'f'));
        Elements.add(new Element(103, "Lr", "Lawrencium", 262f, 5, 4, 18, 7, 'f'));
        Elements.add(new Element(104, "Rf", "Rutherfordi", 267.0f, 5, 4, 4, 7, 'd'));
        Elements.add(new Element(105, "Db", "Dubni", 268.0f, 5, 4, 5, 7, 'd'));
        Elements.add(new Element(106, "Sg", "Seaborgi", 269.0f, 5, 4, 6, 7, 'd'));
        Elements.add(new Element(107, "Bh", "Bohri", 270.0f, 5, 4, 7, 7, 'd'));
        Elements.add(new Element(108, "Hs", "Hassi", 277.0f, 5, 4, 8, 7, 'd'));
        Elements.add(new Element(109, "Mt", "Meitnerium", 276f, 5, 4, 9, 7, 'd'));
        Elements.add(new Element(110, "Ds", "Darmstadtium", 281f, 5, 4, 10, 7, 'd'));
        Elements.add(new Element(111, "Rg", "Roentgenium", 280f, 5, 4, 11, 7, 'd'));
        Elements.add(new Element(112, "Cn", "Copernicium", 285f, 5, 4, 12, 7, 'd'));
        Elements.add(new Element(113, "Nh", "Nihonium", 284f, 6, 4, 13, 7, 'p'));
        Elements.add(new Element(114, "Fl", "Flerovium", 289f, 6, 4, 14, 7, 'p'));
        Elements.add(new Element(115, "Mc", "Moscovium", 288f, 6, 4, 15, 7, 'p'));
        Elements.add(new Element(116, "Lv", "Livermorium", 293f, 6, 4, 16, 7, 'p'));
        Elements.add(new Element(117, "Ts", "Tennessina", 294f, 6, 4, 17, 7, 'p'));
        Elements.add(new Element(118, "Og", "Oganesson", 294f, 10, 4, 18, 7, 'p'));
    }
}


class AdaptadorElements extends ArrayAdapter<Element> {

    private Context context;

    public AdaptadorElements(Context context, ArrayList<Element> datos) {
        super(context, R.layout.layout_element, datos);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View item = inflater.inflate(R.layout.layout_element, null);

        Element element = (Element) getItem(position);

        TextView edt = (TextView)item.findViewById(R.id.edtNumero);
        edt.setText(String.valueOf(element.getNombreAtomic()));

        //Definim el color de text
        edt.setTextColor(fontColor(element));

        edt = (TextView)item.findViewById(R.id.edtSimbol);
        edt.setText(element.getSimbol());

        edt = (TextView)item.findViewById(R.id.edtNom);
        edt.setText(element.getNom());

        edt = (TextView)item.findViewById(R.id.edtPes);
        edt.setText(String.valueOf(element.getPes()));

        //Color de fons de cada item
        item.setBackgroundColor(Color.parseColor(colorFons(element)));

        return(item);
    }

    private String colorFons(Element e){
        String color="";
        int serieQuimicaNum = e.getSerieQuimica();

        switch(serieQuimicaNum){
            case 1:
                color="#ff6666";
                break;
            case 2:
                color="#ffdead";
                break;
            case 3:
                color="#ffbfff";
                break;
            case 4:
                color="#ff99cc";
                break;
            case 5:
                color="#ffc0c0";
                break;
            case 6:
                color="#cccccc";
                break;
            case 7:
                color="#cccc99";
                break;
            case 8:
                color="#a0ffa0";
                break;
            case 9:
                color="#ffff99";
                break;
            case 10:
                color="#c0ffff";
                break;
            default:
                color="#ffffff";
        }

        return color;
    }

    private int fontColor(Element e){
        int estat = e.getEstat();
        int colorEstat;

        switch(estat){
            case 1:
                colorEstat = Color.BLUE;
                break;
            case 2:
                colorEstat = Color.GREEN;
                break;
            case 3:
                colorEstat = Color.BLACK;
                break;
            case 4:
                colorEstat = Color.RED;
                break;
            default:
                colorEstat = Color.BLACK;
        }
        return colorEstat;
    }

}






