package util;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;

public final class XmlParser {

    private final static String USINE_TYPE = "usine";
    private static final String ENTREPOT_TYPE = "entrepot";
    private static final String TYPE_ATTRIBUTE = "type";
    private static final String ID_ATTRIBUTE = "id";

    public static ProductionChain parseXml(String filePath) {


        List<Building> buildingList = new LinkedList<>();
        List<Chemin> cheminList = new LinkedList<>();

        //Instancier la Factory qui permet d'accéder à un parser (appelé DocumentBuilder)
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            //Récupérer le parser
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Parser le fichier XML
            Document doc = db.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            var simulation = doc.getElementsByTagName("simulation").item(0).getChildNodes();
            var metadata = doc.getElementsByTagName("metadonnees").item(0).getChildNodes();

            for (int i = 0; i < simulation.getLength(); i++) {
                Node module = simulation.item(i);
                if (module.getNodeType() == Node.ELEMENT_NODE) {

                    if (module.getNodeName().equals(USINE_TYPE)) {
                        Point coordinates = new Point(
                                parseInt(module.getAttributes().getNamedItem("x").getNodeValue()),
                                parseInt(module.getAttributes().getNamedItem("y").getNodeValue()));

                        if(module.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue().equals(ENTREPOT_TYPE)) {
                            Entrepot entrepot = new Entrepot();
                            entrepot.setId(parseInt(module.getAttributes().getNamedItem(ID_ATTRIBUTE).getNodeValue()));
                            entrepot.setType(module.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue());
                            entrepot.setCoordinates(coordinates);

                            buildingList.add(entrepot);
                        }
                        else {
                            Usine usine = new Usine();
                            usine.setId(parseInt(module.getAttributes().getNamedItem(ID_ATTRIBUTE).getNodeValue()));
                            usine.setType(module.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue());
                            usine.setCoordinates(coordinates);

                            buildingList.add(usine);
                        }
                    } else {
                        for (int j = 0; j < module.getChildNodes().getLength(); j++) {
                            Node chemin = module.getChildNodes().item(j);

                            if (chemin.getNodeType() == Node.ELEMENT_NODE) {
                                cheminList.add(new Chemin(
                                        parseInt(chemin.getAttributes().getNamedItem("de").getNodeValue()),
                                        parseInt(chemin.getAttributes().getNamedItem("vers").getNodeValue())));
                            }
                        }

                    }
                }
            }

            for (int i = 0; i < metadata.getLength(); i++) {
                Node buildingData = metadata.item(i);
                if (buildingData.getNodeType() == Node.ELEMENT_NODE) {

                    List<Input> inputList = new LinkedList<>();

                    for (Building b : buildingList) {
                        if(buildingData.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue().equals(b.getType())) {
                            for (int j = 0; j < buildingData.getChildNodes().getLength(); j++) {
                                Node component = buildingData.getChildNodes().item(j);

                                if (component.getNodeType() == Node.ELEMENT_NODE) {
                                    switch (component.getNodeName()) {
                                        case "icones":
                                            List<Icon> iconList = new LinkedList<>();

                                            for (int k = 0; k < component.getChildNodes().getLength(); k++) {
                                                Node stateIcon = component.getChildNodes().item(k);

                                                if (stateIcon.getNodeType() == Node.ELEMENT_NODE) {
                                                    String iconType = stateIcon.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue();
                                                    String iconPath = stateIcon.getAttributes().getNamedItem("path").getNodeValue();

                                                    iconList.add(new Icon(iconType, iconPath));
                                                }
                                            }

                                            b.setIcon(iconList);
                                            break;

                                        case "entree":
                                            String inputType = component.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue();

                                            if (b.getType().equals(ENTREPOT_TYPE)) {
                                                String inputCapacity = component.getAttributes().getNamedItem("capacite").getNodeValue();
                                                inputList.add(new EntrepotInput(inputType, inputCapacity));
                                            } else {
                                                String inputQuantity = component.getAttributes().getNamedItem("quantite").getNodeValue();
                                                inputList.add(new UsineInput(inputType, inputQuantity));
                                            }
                                            break;

                                        case "sortie":
                                            String outputType = component.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue();
                                            b.setOutput(new Output(outputType));
                                            break;
                                        case "interval-production":
                                            b.setProductionInterval(parseInt(component.getTextContent()));
                                            break;
                                    }
                                    b.setInput(inputList);
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Xml file read successfully.");
            return new ProductionChain(buildingList, cheminList);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error parsing xml file.");
            e.printStackTrace();
        }
        return new ProductionChain();
    }
}
