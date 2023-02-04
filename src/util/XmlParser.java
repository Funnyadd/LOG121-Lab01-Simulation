package util;

import model.Building;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class XmlParser {

    public static void parseXml(String filePath) {
        List<Building> buildingList = new LinkedList<>();

        //Instancier la Factory qui permet d'accéder à un parser (appelé DocumentBuilder)
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            //Récupérer le parser
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Parser le fichier XML
            Document doc = db.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            var metadata = doc.getElementsByTagName("metadonnees").item(0).getChildNodes();
            var simulation = doc.getElementsByTagName("simulation").item(0).getChildNodes();

            System.out.println("metadata:\n");
            for (int i = 0; i < metadata.getLength(); i++) {
                Node building = metadata.item(i);
                if (building.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(building.getAttributes().getNamedItem("type").getNodeValue());

                    for (int j = 0; j < building.getChildNodes().getLength(); j++) {
                        Node component = building.getChildNodes().item(j);
                        if (component.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println(component.getNodeName() + ": ====");

                            switch (component.getNodeName()) {
                                case "icones":
                                    for (int k = 0; k < component.getChildNodes().getLength(); k++) {
                                        Node stateIcon = component.getChildNodes().item(k);
                                        if(stateIcon.getNodeType() == Node.ELEMENT_NODE) {
                                            System.out.println("type: " + stateIcon.getAttributes().getNamedItem("type").getNodeValue());
                                            System.out.println("path: " + stateIcon.getAttributes().getNamedItem("path").getNodeValue());
                                        }
                                    }
                                    break;
                                case "entree":
                                    System.out.println("type" + component.getAttributes().getNamedItem("type"));
                                    System.out.println("quantity/capacity: " + component.getAttributes().getNamedItem("type"));
                                    break;
                                case "sortie":
                                    System.out.println("type:" + component.getAttributes().getNamedItem("type"));
                                    break;
                                case "interval-production":
                                    System.out.println("Inteval:" + component.getTextContent());
                                    break;
                            }
                        }

                    }

                    System.out.println();
                }
            }

            System.out.println("-----------------\n");

            System.out.println("simulation: \n");
            for (int i = 0; i < simulation.getLength(); i++) {
                Node module = simulation.item(i);
                if (module.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(module.getNodeName());

                    if (module.getNodeName().equals("usine")) {
                        for (int j = 0; j < module.getAttributes().getLength(); j++) {
                            Node attribute = module.getAttributes().item(j);
                            System.out.println(attribute.getNodeName() + ": " + attribute.getNodeValue());
                        }
                    } else {
                        for (int j = 0; j < module.getChildNodes().getLength(); j++) {
                            Node chemin = module.getChildNodes().item(j);
                            if (chemin.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.println(chemin.getNodeName());
                                System.out.println("from: " + chemin.getAttributes().getNamedItem("de").getNodeValue());
                                System.out.println("to: " + chemin.getAttributes().getNamedItem("vers").getNodeValue());
                            }
                            System.out.println();
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
