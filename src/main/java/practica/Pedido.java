package practica;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String id;
    private String dni;
    private String fecha;
    private double total;
    private List<Articulo> articulos = new ArrayList<>();

    public Pedido(String id, String dni, String fecha, double total) {
        this.id = id;
        this.dni = dni;
        this.fecha = fecha;
        this.total = total;
    }

    public String getId() { return id; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getFecha() { return fecha; }
    public double getTotal() { return total; }
    public List<Articulo> getArticulos() { return articulos; }

    @Override
    public String toString() {
        return id + " | " + fecha + " | " + total + " | " + articulos.size() + " artículos";
    }
}