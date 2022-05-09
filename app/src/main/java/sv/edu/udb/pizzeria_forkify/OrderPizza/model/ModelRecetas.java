package sv.edu.udb.pizzeria_forkify.OrderPizza.model;

import java.util.ArrayList;

public class ModelRecetas {
    private String Titulo,RefImg,Descripcion,Tiempo;
    private ArrayList<String> Ingredientes,Pasos;
    private Integer NoPersonas;
    String Key;

    public ModelRecetas() {
    }

    public ModelRecetas(String titulo, String refImg, String descripcion, String tiempo, ArrayList<String> ingredientes, ArrayList<String> pasos, Integer noPersonas) {
        Titulo = titulo;
        RefImg = refImg;
        Descripcion = descripcion;
        Tiempo = tiempo;
        Ingredientes = ingredientes;
        Pasos = pasos;
        NoPersonas = noPersonas;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getRefImg() {
        return RefImg;
    }

    public void setRefImg(String refImg) {
        RefImg = refImg;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }

    public ArrayList<String> getIngredientes() {
        return Ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        Ingredientes = ingredientes;
    }

    public ArrayList<String> getPasos() {
        return Pasos;
    }

    public void setPasos(ArrayList<String> pasos) {
        Pasos = pasos;
    }

    public Integer getNoPersonas() {
        return NoPersonas;
    }

    public void setNoPersonas(Integer noPersonas) {
        NoPersonas = noPersonas;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}

