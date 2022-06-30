package net.kmlexport;

import net.sf.openrocket.plugin.Plugin;
import net.sf.openrocket.simulation.extension.AbstractSimulationExtensionProvider;

@Plugin
public class KMLExportProvider extends AbstractSimulationExtensionProvider {

    public KMLExportProvider() {
        super(KMLExportSimulationExtension.class, "KML Export");
    }

}