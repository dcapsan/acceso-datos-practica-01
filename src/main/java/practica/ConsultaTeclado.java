package practica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultaTeclado {

    public static List<Integer> pedirPosiciones(Scanner sc, int max) {
        while (true) {
            System.out.print("Introduce una posición (ej. 3), un rango (ej. 2-5) o varias (ej. 2,4,6) [1-" + max + "]: ");
            String linea = sc.nextLine().trim();
            List<Integer> posiciones = interpretar(linea, max);
            if (posiciones != null) {
                return posiciones;
            }
        }
    }

    private static List<Integer> interpretar(String linea, int max) {
        List<Integer> resultado = new ArrayList<>();
        try {
            if (linea.matches("\\d+")) {
                resultado.add(Integer.parseInt(linea));
            } else if (linea.matches("\\d+\\s*-\\s*\\d+")) {
                String[] partes = linea.split("-");
                int desde = Integer.parseInt(partes[0].trim());
                int hasta = Integer.parseInt(partes[1].trim());
                if (desde > hasta) {
                    System.out.println("Error: en el rango, el primer número no puede ser mayor que el segundo.");
                    return null;
                }
                for (int i = desde; i <= hasta; i++) {
                    resultado.add(i);
                }
            } else if (linea.matches("\\d+(\\s*,\\s*\\d+)+")) {
                for (String parte : linea.split(",")) {
                    resultado.add(Integer.parseInt(parte.trim()));
                }
            } else {
                System.out.println("Error: formato no válido. Usa 3, 2-5 o 2,4,6.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: número demasiado grande.");
            return null;
        }

        for (int p : resultado) {
            if (p < 1 || p > max) {
                System.out.println("Error: la posición " + p + " está fuera del rango 1-" + max + ".");
                return null;
            }
        }
        return resultado;
    }
}