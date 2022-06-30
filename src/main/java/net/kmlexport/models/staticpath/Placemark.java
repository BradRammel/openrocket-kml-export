package net.kmlexport.models.staticpath;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"name", "description", "styleUrl", "lineString"})
public class Placemark implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String styleUrl;
    private LineString lineString;

    public Placemark() {}

    public Placemark(String name, String description, String styleUrl,
                     LineString lineString) {
        this.name = name;
        this.description = description;
        this.styleUrl = styleUrl;
        this.lineString = lineString;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getStyleUrl() {
        return styleUrl;
    }

    public void setStyleUrl(String styleUrl) {
        this.styleUrl = styleUrl;
    }

    @XmlElement(name = "LineString")
    public LineString getLineString() {
        return lineString;
    }

    public void setLineString(LineString lineString) {
        this.lineString = lineString;
    }

}
