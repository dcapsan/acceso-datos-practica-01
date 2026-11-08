package practica;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorPedidos {

    public static List<Pedido> leer(String ruta) throws IOException, JsonParseException {
        try (Reader reader = Files.newBufferedReader(Paths.get(ruta), StandardCharsets.UTF_8)) {
            JsonObject raiz = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray lista = raiz.getAsJsonObject("pedidos").getAsJsonArray("pedido");

            List<Pedido> pedidos = new ArrayList<>();
            for (JsonElement el : lista) {
                JsonObject o = el.getAsJsonObject();
                Pedido pedido = new Pedido(
                        o.get("id").getAsString(),
                        o.get("dni").getAsString(),
                        o.get("fecha").getAsString(),
                        o.get("total").getAsDouble());

                if (o.has("articulos")) {
                    recoger(o.get("articulos"), pedido.getArticulos());
                }
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (IllegalStateException | NullPointerException | ClassCastException e) {
            throw new JsonParseException("Estructura del JSON no válida: " + e.getMessage(), e);
        }
    }

    // Acepta lista, objeto con clave "articulo" o artículo directo
    private static void recoger(JsonElement el, List<Articulo> destino) {
        if (el.isJsonArray()) {
            for (JsonElement hijo : el.getAsJsonArray()) {
                recoger(hijo, destino);
            }
        } else if (el.isJsonObject()) {
            JsonObject o = el.getAsJsonObject();
            if (o.has("articulo")) {
                recoger(o.get("articulo"), destino);
            } else {
                destino.add(new Articulo(
                        o.get("productoId").getAsString(),
                        o.get("descripcion").getAsString(),
                        o.get("cantidad").getAsInt(),
                        o.get("precioUnitario").getAsDouble()));
            }
        }
    }
}