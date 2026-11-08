package practica;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FicheroAleatorio {

    private static final int L_DNI = 9;
    private static final int L_NOMBRE = 30;
    private static final int L_DIRECCION = 40;
    private static final int L_TELEFONO = 9;
    private static final int L_EMAIL = 40;

    public static final int TAM_REGISTRO =
            2 * (L_DNI + L_NOMBRE + L_DIRECCION + L_TELEFONO + L_EMAIL) + 4 + 8;

    public static void escribir(List<Cliente> clientes, String ruta) throws IOException {
        Path destino = Paths.get(ruta);
        if (destino.getParent() != null) {
            Files.createDirectories(destino.getParent());
        }

        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.setLength(0);
            for (Cliente c : clientes) {
                escribirTexto(raf, c.getDni(), L_DNI);
                escribirTexto(raf, c.getNombre(), L_NOMBRE);
                escribirTexto(raf, c.getDireccion(), L_DIRECCION);
                escribirTexto(raf, c.getTelefono(), L_TELEFONO);
                escribirTexto(raf, c.getEmail(), L_EMAIL);

                double total = 0;
                for (Pedido p : c.getPedidos()) {
                    total += p.getTotal();
                }
                raf.writeInt(c.getPedidos().size());
                raf.writeDouble(total);
            }
        }
    }

    public static int contarRegistros(String ruta) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            return (int) (raf.length() / TAM_REGISTRO);
        }
    }

    public static String leer(String ruta, int posicion) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            long total = raf.length() / TAM_REGISTRO;
            if (posicion < 1 || posicion > total) {
                throw new IllegalArgumentException("Posición fuera de rango: " + posicion);
            }
            raf.seek((long) (posicion - 1) * TAM_REGISTRO);

            String dni = leerTexto(raf, L_DNI);
            String nombre = leerTexto(raf, L_NOMBRE);
            String direccion = leerTexto(raf, L_DIRECCION);
            String telefono = leerTexto(raf, L_TELEFONO);
            String email = leerTexto(raf, L_EMAIL);
            int numPedidos = raf.readInt();
            double totalPedidos = raf.readDouble();

            return String.format(java.util.Locale.ROOT,
                    "[%d] %s | %s | %s | %s | %s | pedidos: %d | total: %.2f",
                    posicion, dni, nombre, direccion, telefono, email, numPedidos, totalPedidos);
        }
    }

    private static void escribirTexto(RandomAccessFile raf, String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto == null ? "" : texto);
        if (sb.length() > longitud) {
            sb.setLength(longitud);
        }
        while (sb.length() < longitud) {
            sb.append(' ');
        }
        raf.writeChars(sb.toString());
    }

    private static String leerTexto(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();
    }
}