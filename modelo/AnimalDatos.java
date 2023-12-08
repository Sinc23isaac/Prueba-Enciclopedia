package com.example.animaltfg.modelo;

public class AnimalDatos {
    //declaramos los datos que vamos a utilizar en nuestra Api para mostar datos de los animales

    private String nombre;
    private String ubicacion;
    private String presas;
    private String habitat;
    private String dieta;

    //constructores

    public AnimalDatos(String nombre, String ubicacion, String presas, String habitat, String dieta) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.presas = presas;
        this.habitat = habitat;
        this.dieta = dieta;
    }
    //getters

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getPresas() {
        return presas;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getDieta() {
        return dieta;
    }
    //setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setPresas(String presas) {
        this.presas = presas;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }
    //to string

    @Override
    public String toString() {
        return "AnimalDatos{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", presas='" + presas + '\'' +
                ", habitat='" + habitat + '\'' +
                ", dieta='" + dieta + '\'' +
                '}';
    }
}
