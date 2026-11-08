package practica;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String CLIENTES_XML = "datos/clientes.xml";
    private static final String PEDIDOS_JSON = "datos/pedidos.json";
    private static final String SALIDA_XML = "salida/clientes_pedidos.xml";
    private static final String FICHERO_DAT = "salida/clientes.dat";

    public static void main(String[] args) {
        try {
            List<Cliente> clientes = LectorClientes.leer(CLIENTES_XML);
            List<Pedido> pedidos = LectorPedidos.leer(PEDIDOS_JSON);
            System.out.println("Clientes: " + clientes.size() + " | Pedidos: " + pedidos.size());

            List<Pedido> sinCliente = Unidor.unir(clientes, pedidos);
            sinCliente.forEach(p ->
                    System.out.println("Aviso: pedido " + p.getId() + " sin cliente (DNI " + p.getDni() + ")"));

            Unidor.eliminarDniDePedidos(clientes);

            EscritorXml.escribir(clientes, SALIDA_XML);
            System.out.println("Fichero generado: " + SALIDA_XML);

            FicheroAleatorio.escribir(clientes, FICHERO_DAT);
            int n = FicheroAleatorio.contarRegistros(FICHERO_DAT);
            System.out.println("Registros en el fichero aleatorio: " + n);

            Scanner sc = new Scanner(System.in);
            List<Integer> posiciones = ConsultaTeclado.pedirPosiciones(sc, n);
            for (int pos : posiciones) {
                try {
                    System.out.println(FicheroAleatorio.leer(FICHERO_DAT, pos));
                } catch (IOException | IllegalArgumentException e) {
                    System.out.println("No se pudo leer la posición " + pos + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}