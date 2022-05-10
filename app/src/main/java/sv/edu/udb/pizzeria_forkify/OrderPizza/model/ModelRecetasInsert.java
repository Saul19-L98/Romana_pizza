package sv.edu.udb.pizzeria_forkify.OrderPizza.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelRecetasInsert implements Serializable {
    private String Titulo,RefImg,Descripcion,Tiempo;
    private ArrayList<ModelArray> Ingredientes,Pasos;
    private Integer NoPersonas;
    String Key;

    public ModelRecetasInsert() {
    }

    public ModelRecetasInsert(String titulo, String refImg, String descripcion, String tiempo, ArrayList<ModelArray> ingredientes, ArrayList<ModelArray> pasos, Integer noPersonas) {
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

    public ArrayList<ModelArray> getIngredientes() {
        return Ingredientes;
    }

    public void setIngredientes(ArrayList<ModelArray> ingredientes) {
        Ingredientes = ingredientes;
    }

    public ArrayList<ModelArray> getPasos() {
        return Pasos;
    }

    public void setPasos(ArrayList<ModelArray> pasos) {
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

