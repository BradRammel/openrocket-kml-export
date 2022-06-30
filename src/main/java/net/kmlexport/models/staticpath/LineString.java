package net.kmlexport.models.staticpath;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"tessellate", "altitudeMode", "coordinates"})
public class LineString implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tessellate;
    private String altitudeMode;
    private String coordinates;

    public LineString() {}

    public LineString(Integer tessellate, String altitudeMode, String coordinates) {
        this.tessellate = tessellate;
        this.altitudeMode = altitudeMode;
        this.coordinates = coordinates;
    }

    @XmlElement
    public Integer getTessellate() {
        return tessellate;
    }

    public void setTessellate(Integer tessellate) {
        this.tessellate = tessellate;
    }

    @XmlElement
    public String getAltitudeMode() {
        return altitudeMode;
    }

    public void setAltitudeMode(String altitudeMode) {
        this.altitudeMode = altitudeMode;
    }

    @XmlElement
    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

}
