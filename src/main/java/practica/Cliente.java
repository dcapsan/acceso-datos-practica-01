package practica;
import java.util.ArrayList;
import java.util.List;


public class Cliente {
    private final String dni;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final String email;
    private final List<Pedido> pedidos = new ArrayList<>();

    public Cliente(String dni, String nombre, String direccion, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public List<Pedido> getPedidos() { return pedidos; }


    @Override
    public String toString() {
        return dni + " | " + nombre + " | " + direccion + " | " + telefono + " | " + email;
    }
}