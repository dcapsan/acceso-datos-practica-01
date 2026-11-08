package practica;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Cliente> clientes = LectorClientes.leer("datos/clientes.xml");
            System.out.println("Clientes leídos: " + clientes.size());

            List<Pedido> pedidos = LectorPedidos.leer("datos/pedidos.json");
            System.out.println("Pedidos leídos: " + pedidos.size());
            pedidos.forEach(p -> {
                System.out.println(p);
                p.getArticulos().forEach(a -> System.out.println("    " + a));
            });
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}