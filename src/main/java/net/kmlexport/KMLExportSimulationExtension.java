package net.kmlexport;

import net.sf.openrocket.simulation.SimulationConditions;
import net.sf.openrocket.simulation.extension.AbstractSimulationExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KMLExportSimulationExtension extends AbstractSimulationExtension {

    private final static Logger log = LoggerFactory.getLogger(KMLExportSimulationExtension.class);

    @Override
    public void initialize(SimulationConditions simulationConditions) {
        log.error("Opening the kml export");

        KMLExporter exporter = new KMLExporter();
        exporter.generateSimulationKML(simulationConditions);
    }

}
