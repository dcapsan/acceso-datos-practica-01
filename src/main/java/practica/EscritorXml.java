package practica;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

public class EscritorXml {

    public static void escribir(List<Cliente> clientes, String ruta)
            throws ParserConfigurationException, TransformerException, java.io.IOException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element raiz = doc.createElement("clientes");
        doc.appendChild(raiz);

        for (Cliente c : clientes) {
            Element eCliente = doc.createElement("cliente");
            eCliente.setAttribute("dni", c.getDni());
            raiz.appendChild(eCliente);

            añadir(doc, eCliente, "nombre", c.getNombre());
            añadir(doc, eCliente, "direccion", c.getDireccion());
            añadir(doc, eCliente, "telefono", c.getTelefono());
            añadir(doc, eCliente, "email", c.getEmail());

            Element ePedidos = doc.createElement("pedidos");
            eCliente.appendChild(ePedidos);

            for (Pedido p : c.getPedidos()) {
                Element ePedido = doc.createElement("pedido");
                ePedido.setAttribute("id", p.getId());
                ePedidos.appendChild(ePedido);

                añadir(doc, ePedido, "fecha", p.getFecha());
                añadir(doc, ePedido, "total", formato(p.getTotal()));

                Element eArticulos = doc.createElement("articulos");
                ePedido.appendChild(eArticulos);
                for (Articulo a : p.getArticulos()) {
                    Element eArticulo = doc.createElement("articulo");
                    eArticulos.appendChild(eArticulo);
                    añadir(doc, eArticulo, "productoId", a.getProductoId());
                    añadir(doc, eArticulo, "descripcion", a.getDescripcion());
                    añadir(doc, eArticulo, "cantidad", String.valueOf(a.getCantidad()));
                    añadir(doc, eArticulo, "precioUnitario", formato(a.getPrecioUnitario()));
                }
            }
        }

        Path destino = Paths.get(ruta);
        if (destino.getParent() != null) {
            Files.createDirectories(destino.getParent());
        }

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        t.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
    }

    private static void añadir(Document doc, Element padre, String nombre, String valor) {
        Element e = doc.createElement(nombre);
        e.setTextContent(valor);
        padre.appendChild(e);
    }

    private static String formato(double v) {
        return String.format(Locale.ROOT, "%.2f", v);
    }
}