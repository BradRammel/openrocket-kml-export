package net.kmlexport;

import net.kmlexport.models.staticpath.*;
import net.sf.openrocket.simulation.*;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class KMLExporter extends AbstractSimulationListener {

    private final static Logger log = LoggerFactory.getLogger(KMLExporter.class);

    private final static String LATITUDE_TYPE_NAME = "latitude";
    private final static String LONGITUDE_TYPE_NAME = "longitude";
    private final static String ALTITUDE_TYPE_NAME = "altitude";

    private List<Double> latitude;
    private List<Double> longitude;
    private List<Double> altitude;
    private List<Integer> roundedAlt;

    public KMLExporter() {}

    public void generateSimulationKML(SimulationConditions simulationConditions) {
        FlightData flightData = simulationConditions.getSimulation().getSimulatedData();
        if (flightData == null || flightData.getBranchCount() == 0) {
            log.error("There was no data to export");
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
                log.error(type.getUnitGroup().toString());
                latitude = branch.get(type);
            } else if (type.getName().toLowerCase().equals(LONGITUDE_TYPE_NAME)) {
                log.error(type.getUnitGroup().toString());
                longitude = branch.get(type);
            } else if (type.getName().toLowerCase().equals(ALTITUDE_TYPE_NAME)) {
                log.error(type.getUnitGroup().toString());
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
        for (int i = 0; i < latitude.size(); i++) {
            Double lat = latitude.get(i);
            Double degrees = (lat * 180) / Math.PI;
            latitude.set(i, degrees);
        }
    }

    private void cleanLongitudeData() {
        for (int i = 0; i < longitude.size(); i++) {
            Double lon = longitude.get(i);
            Double degrees = (lon * 180) / Math.PI;
            longitude.set(i, degrees);
        }
    }

    private void cleanAltitudeData() {
        roundedAlt = new ArrayList<>();
        for (int i = 0; i < altitude.size(); i++) {
            Double alt = altitude.get(i);
            int rounded = (int) Math.rint(alt);
            roundedAlt.add(rounded);
        }
    }

    private KML populateFields() {
        LineStyle lineStyle = new LineStyle("7f00ffff", 4);
        PolyStyle polyStyle = new PolyStyle("7f00ff00");
        Style style = new Style("yellowLineGreenPoly", lineStyle, polyStyle);
        String coordinates = createCoordinateString();
        LineString lineString = new LineString(1, "absolute", coordinates);
        Placemark placemark = new Placemark("Absolute Extruded", "", "#yellowLineGreenPoly", lineString);
        Document document = new Document("Paths", "", style, placemark);
        return new KML(document, "    http://www.opengis.net/kml/2.2");
    }

    private String createCoordinateString() {
        StringBuilder stringBuilder = new StringBuilder();
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(8);
        for (int i = 0; i < latitude.size(); i++) {
            stringBuilder.append(nf.format(longitude.get(i))).append(",");
            stringBuilder.append(nf.format(latitude.get(i))).append(",");
            stringBuilder.append(roundedAlt.get(i).toString());
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
