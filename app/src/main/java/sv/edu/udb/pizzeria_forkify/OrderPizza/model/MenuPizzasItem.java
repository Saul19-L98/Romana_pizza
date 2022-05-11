package sv.edu.udb.pizzeria_forkify.OrderPizza.model;

import java.security.Key;

public class MenuPizzasItem {
    private String Nombre,Ingredientes,Precio,RefImg;
    String Key;

    public MenuPizzasItem() {
    }

    public MenuPizzasItem(String nombre, String ingredientes, String precio, String refImg) {
        Nombre = nombre;
        Ingredientes = ingredientes;
        Precio = precio;
        RefImg = refImg;

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getIngredientes() {
        return Ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        Ingredientes = ingredientes;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getRefImg() {
        return RefImg;
    }

    public void setRefImg(String refImg) {
        RefImg = refImg;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
