package net.kmlexport;

import net.sf.openrocket.simulation.SimulationConditions;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulationListener extends AbstractSimulationListener {

    private static final Logger log = LoggerFactory.getLogger(SimulationListener.class);

    private SimulationConditions simulationConditions;

    public SimulationListener(SimulationConditions simulationConditions) {
        this.simulationConditions = simulationConditions;
    }

    @Override
    public void endSimulation(SimulationStatus status, SimulationException exception) {
        KMLExporter exporter = new KMLExporter();
        exporter.generateSimulationKML(simulationConditions);
    }

}
