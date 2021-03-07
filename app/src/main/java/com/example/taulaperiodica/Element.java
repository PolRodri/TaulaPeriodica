package com.example.taulaperiodica;

public class Element {
    private int nombreAtomic;
    private String simbol;
    private String nom;
    private float pes;
    private int serieQuimica;
    private int estat;
    private int grup;
    private int periode;
    private char bloc;
    private String configuracio;

    public Element(int nombreAtomic, String simbol, String nom, float pes, int serieQuimica, int estat, int grup, int periode, char bloc){
        this.nombreAtomic = nombreAtomic;
        this.simbol = simbol;
        this.nom = nom;
        this.pes = pes;
        this.serieQuimica = serieQuimica;
        this.estat = estat;
        this.grup = grup;
        this.periode = periode;
        this.bloc = bloc;
    }

    public int getNombreAtomic() {
        return nombreAtomic;
    }

    public void setNombreAtomic(int nombreAtomic) {
        this.nombreAtomic = nombreAtomic;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPes() {
        return pes;
    }

    public void setPes(float pes) {
        this.pes = pes;
    }

    public int getSerieQuimica() {
        return serieQuimica;
    }

    public void setSerieQuimica(int serieQuimica) {
        this.serieQuimica = serieQuimica;
    }

    public int getEstat() {
        return estat;
    }

    public void setEstat(int estat) {
        this.estat = estat;
    }

    public int getGrup() {
        return grup;
    }

    public void setGrup(int grup) {
        this.grup = grup;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public char getBloc() {
        return bloc;
    }

    public void setBloc(char bloc) {
        this.bloc = bloc;
    }

    public String configuracioElectronica(){
        return (String.valueOf(this.periode) + "" + String.valueOf(this.bloc));
    }

    public String electronsPerNivell(){
        String electrons;

        if(this.bloc == 's'){
            electrons = ("2");
        } else if(this.bloc == 'p'){
            electrons = ("2, 6");
        } else if(this.bloc == 'd'){
            electrons = ("2, 6, 10");
        } else{
            electrons = ("2, 6, 10, 14");
        }
        return electrons;
    }
}
