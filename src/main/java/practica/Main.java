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
            
            FicheroAleatorio.escribir(clientes, "salida/clientes.dat");
            int n = FicheroAleatorio.contarRegistros("salida/clientes.dat");
            System.out.println("Registros en el fichero aleatorio: " + n);
            System.out.println(FicheroAleatorio.leer("salida/clientes.dat", 1));
            System.out.println(FicheroAleatorio.leer("salida/clientes.dat", n));
            System.out.println("Fichero generado: salida/clientes_pedidos.xml");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}