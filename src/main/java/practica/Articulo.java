package practica;

public class Articulo {
    private String productoId;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;

    public Articulo(String productoId, String descripcion, int cantidad, double precioUnitario) {
        this.productoId = productoId;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getProductoId() { return productoId; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }

    @Override
    public String toString() {
        return productoId + " - " + descripcion + " x" + cantidad + " a " + precioUnitario;
    }
}