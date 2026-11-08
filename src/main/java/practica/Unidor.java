package practica;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Unidor {

    public static List<Pedido> unir(List<Cliente> clientes, List<Pedido> pedidos) {
        Map<String, Cliente> porDni = new LinkedHashMap<>();
        for (Cliente c : clientes) {
            if (porDni.putIfAbsent(c.getDni(), c) != null) {
                System.out.println("Aviso: DNI de cliente duplicado: " + c.getDni());
            }
        }

        List<Pedido> sinCliente = new ArrayList<>();
        for (Pedido p : pedidos) {
            Cliente c = porDni.get(p.getDni());
            if (c != null) {
                c.getPedidos().add(p);
            } else {
                sinCliente.add(p);
            }
        }
        return sinCliente;
    }

    public static void eliminarDniDePedidos(List<Cliente> clientes) {
        for (Cliente c : clientes) {
            for (Pedido p : c.getPedidos()) {
                p.setDni(null);
            }
        }
    }
}