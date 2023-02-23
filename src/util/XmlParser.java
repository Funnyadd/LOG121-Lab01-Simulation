package util;

import model.*;
import model.MachineComponent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

    private static List<Building> buildingList;
    private static List<Chemin> cheminList;

    public static ProductionChain parseXml(String filePath) {

        buildingList = new LinkedList<>();
        cheminList = new LinkedList<>();

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

            readSimulation(simulation);
            readMetaData(metadata);

            System.out.println("Xml file read successfully.\nConfiguration:");

            System.out.println("\nUsines:");
            for (Building b : buildingList) {
                System.out.println(b);
            }

            System.out.println("\nChemins:");
            for (Chemin c : cheminList) {
                System.out.println(c);
            }

            Entrepot observable = new Entrepot();
            for (Building b : buildingList) {
                if (b instanceof Entrepot) {
                    observable = (Entrepot) b;
                }
            }

            for (Building b : buildingList) {
                if (b instanceof Usine) {
                    observable.attach((Usine) b);
                }
            }

            return new ProductionChain(buildingList, cheminList, filePath);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error parsing xml file.");
            e.printStackTrace();
        }
        // If an error occurs
        return new ProductionChain();
    }

    private static void readSimulation(NodeList simulation) {
        for (int i = 0; i < simulation.getLength(); i++) {
            Node module = simulation.item(i);
            if (module.getNodeType() == Node.ELEMENT_NODE) {

                if (module.getNodeName().equals(USINE_TYPE)) {
                    Point coordinates = new Point(
                            parseInt(module.getAttributes().getNamedItem("x").getNodeValue()),
                            parseInt(module.getAttributes().getNamedItem("y").getNodeValue()));

                    Building building = new Building();
                    building.setId(parseInt(module.getAttributes().getNamedItem(ID_ATTRIBUTE).getNodeValue()));
                    building.setType(module.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue());
                    building.setCoordinates(coordinates);

                    if(module.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue().equals(ENTREPOT_TYPE)) {
                        Entrepot entrepot = new Entrepot(building);
                        buildingList.add(entrepot);
                    }
                    else {
                        Usine usine = new Usine(building);
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
    }

    private static void readMetaData(NodeList metadata) {
        for (int i = 0; i < metadata.getLength(); i++) {
            Node buildingData = metadata.item(i);
            if (buildingData.getNodeType() == Node.ELEMENT_NODE) {

                List<Input> inputList = new LinkedList<>();

                for (Building b : buildingList) {
                    if (buildingData.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue().equals(b.getType())) {
                        for (int j = 0; j < buildingData.getChildNodes().getLength(); j++) {
                            Node configuration = buildingData.getChildNodes().item(j);

                            if (configuration.getNodeType() == Node.ELEMENT_NODE) {
                                switch (configuration.getNodeName()) {
                                    case "icones":
                                        List<Icon> iconList = new LinkedList<>();

                                        for (int k = 0; k < configuration.getChildNodes().getLength(); k++) {
                                            Node stateIcon = configuration.getChildNodes().item(k);

                                            if (stateIcon.getNodeType() == Node.ELEMENT_NODE) {
                                                String iconType = stateIcon.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue();
                                                String iconPath = stateIcon.getAttributes().getNamedItem("path").getNodeValue();

                                                iconList.add(new Icon(iconType, iconPath));
                                            }
                                        }

                                        b.setIcon(iconList);
                                        break;

                                    case "entree":
                                        String inputType = configuration.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue();
                                        MachineComponent component = new MachineComponent(inputType, b.getCoordinates());

                                        if (b instanceof Entrepot) {
                                            int inputCapacity = Integer.parseInt(configuration.getAttributes().getNamedItem("capacite").getNodeValue());
                                            ((Entrepot) b).setInput(new EntrepotInput(component, inputCapacity));
                                        } else {
                                            int inputQuantity = Integer.parseInt(configuration.getAttributes().getNamedItem("quantite").getNodeValue());
                                            inputList.add(new UsineInput(component, inputQuantity));
                                        }
                                        break;

                                    case "sortie":
                                        String outputType = configuration.getAttributes().getNamedItem(TYPE_ATTRIBUTE).getNodeValue();
                                        b.setOutput(new Output(outputType));
                                        break;
                                    case "interval-production":
                                        b.setProductionInterval(parseInt(configuration.getTextContent()));
                                        break;
                                }

                                if (b instanceof Usine) {
                                    List<UsineInput> usineInputList = new LinkedList<>();
                                    for (Input input : inputList) {
                                        usineInputList.add((UsineInput) input);
                                    }
                                    ((Usine) b).setInputList(usineInputList);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
