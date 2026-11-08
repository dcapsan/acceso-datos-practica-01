package practica;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorClientes {

    public static List<Cliente> leer(String ruta)
            throws ParserConfigurationException, SAXException, IOException {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new File(ruta));
        doc.getDocumentElement().normalize();

        List<Cliente> clientes = new ArrayList<>();
        NodeList nodos = doc.getElementsByTagName("cliente");

        for (int i = 0; i < nodos.getLength(); i++) {
            Element e = (Element) nodos.item(i);
            clientes.add(new Cliente(
                    e.getAttribute("dni"),
                    texto(e, "nombre"),
                    texto(e, "direccion"),
                    texto(e, "telefono"),
                    texto(e, "email")));
        }
        return clientes;
    }

    private static String texto(Element padre, String etiqueta) {
        NodeList lista = padre.getElementsByTagName(etiqueta);
        return lista.getLength() == 0 ? "" : lista.item(0).getTextContent().trim();
    }
}