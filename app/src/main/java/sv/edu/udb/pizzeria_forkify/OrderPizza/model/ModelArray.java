package sv.edu.udb.pizzeria_forkify.OrderPizza.model;



public class ModelArray {
    private String Valor;
    String Key;

    public ModelArray() {

    }

    public ModelArray(String valor, String key) {
        Valor = valor;
        Key = key;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
