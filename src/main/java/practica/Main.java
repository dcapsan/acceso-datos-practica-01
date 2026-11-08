package practica;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Cliente> clientes = LectorClientes.leer("datos/clientes.xml");
            List<Pedido> pedidos = LectorPedidos.leer("datos/pedidos.json");
            System.out.println("Clientes: " + clientes.size() + " | Pedidos: " + pedidos.size());

            List<Pedido> sinCliente = Unidor.unir(clientes, pedidos);
            sinCliente.forEach(p ->
                    System.out.println("Aviso: pedido " + p.getId() + " sin cliente (DNI " + p.getDni() + ")"));

            Unidor.eliminarDniDePedidos(clientes);

            EscritorXml.escribir(clientes, "salida/clientes_pedidos.xml");
            System.out.println("Fichero generado: salida/clientes_pedidos.xml");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}