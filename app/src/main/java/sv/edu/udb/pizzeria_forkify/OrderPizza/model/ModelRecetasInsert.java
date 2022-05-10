package sv.edu.udb.pizzeria_forkify.OrderPizza.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelRecetasInsert implements Serializable {
    private String titulo,refImg,descripcion,tiempo;
    private Integer noPersonas;
    String Key;

    public ModelRecetasInsert() {
    }

    public ModelRecetasInsert(String Titulo, String RefImg, String Descripcion, String Tiempo, Integer NoPersonas) {
        this.titulo = Titulo;
        this.refImg = RefImg;
        this.descripcion = Descripcion;
        this.tiempo = Tiempo;
        this.noPersonas = NoPersonas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRefImg() {
        return refImg;
    }

    public void setRefImg(String refImg) {
        this.refImg = refImg;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getNoPersonas() {
        return noPersonas;
    }

    public void setNoPersonas(Integer noPersonas) {
        this.noPersonas = noPersonas;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}

