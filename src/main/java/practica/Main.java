package practica;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Cliente> clientes = LectorClientes.leer("datos/clientes.xml");
            System.out.println("Clientes leídos: " + clientes.size());
            clientes.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error leyendo clientes: " + e.getMessage());
        }
    }
}