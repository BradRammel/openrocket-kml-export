package net.kmlexport;

import net.kmlexport.models.staticpath.*;
import net.sf.openrocket.simulation.FlightData;
import net.sf.openrocket.simulation.FlightDataBranch;
import net.sf.openrocket.simulation.FlightDataType;
import net.sf.openrocket.simulation.SimulationConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class KMLExporter {

    private final static Logger log = LoggerFactory.getLogger(KMLExporter.class);

    private final static String LATITUDE_TYPE_NAME = "latitude";
    private final static String LONGITUDE_TYPE_NAME = "longitude";
    private final static String ALTITUDE_TYPE_NAME = "altitude";

    private List<Double> latitude;
    private List<Double> longitude;
    private List<Double> altitude;

    public KMLExporter() {}

    public void generateSimulationKML(SimulationConditions simulationConditions) {
        FlightData flightData = simulationConditions.getSimulation().getSimulatedData();
        if (flightData == null || flightData.getBranchCount() == 0) {
            log.info("There was no data to export");
            return;
        }
        FlightDataBranch branch = flightData.getBranch(0);
        setCoordinateInformationFromBranch(branch);
        cleanData();
        KML kml = populateFields();
        marshal(kml);
    }

    private void setCoordinateInformationFromBranch(FlightDataBranch branch) {
        FlightDataType[] types = branch.getTypes();
        for (FlightDataType type : types) {
            if (type.getName().toLowerCase().equals(LATITUDE_TYPE_NAME)) {
                latitude = branch.get(type);
            } else if (type.getName().toLowerCase().equals(LONGITUDE_TYPE_NAME)) {
                longitude = branch.get(type);
            } else if (type.getName().toLowerCase().equals(ALTITUDE_TYPE_NAME)) {
                altitude = branch.get(type);
            }
        }
    }

    private void cleanData() {
        cleanLatitudeData();
        cleanLongitudeData();
        cleanAltitudeData();
    }

    private void cleanLatitudeData() {

    }

    private void cleanLongitudeData() {

    }

    private void cleanAltitudeData() {

    }

    private KML populateFields() {
        LineStyle lineStyle = new LineStyle("Green", 1);
        PolyStyle polyStyle = new PolyStyle("Blue");
        Style style = new Style("yellowLineGreenPoly", lineStyle, polyStyle);
        String coordinates = createCoordinateString();
        LineString lineString = new LineString(1, "flight", coordinates);
        Placemark placemark = new Placemark("Rammel", "Another description", "Style URL", lineString);
        Document document = new Document("Brad", "Description", style, placemark);
        return new KML(document, "    http://www.opengis.net/kml/2.2");
    }

    private String createCoordinateString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < latitude.size(); i++) {
            stringBuilder.append("\t\t\t\t\t\t\t\t").append(latitude.get(i).toString()).append(",");
            stringBuilder.append(longitude.get(i).toString()).append(",");
            stringBuilder.append(altitude.get(i).toString()).append(",");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private void marshal(KML kml) {
        try {
            JAXBContext context = JAXBContext.newInstance(KML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(kml, new File("C:\\Users\\bmram\\Documents\\kmltemplate.kml"));
        } catch (JAXBException e) {
            log.error(e.getMessage(), e);
        }
    }

}
