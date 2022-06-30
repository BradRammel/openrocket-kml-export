package net.kmlexport.models.staticpath;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "Document")
@XmlType(propOrder = {"name", "description", "style", "placemark"})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Style style;
    private Placemark placemark;

    public Document() {}

    public Document(String name, String description, Style style,
                    Placemark placemark) {
        this.name = name;
        this.description = description;
        this.style = style;
        this.placemark = placemark;
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

    @XmlElement(name = "Style")
    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @XmlElement(name = "Placemark")
    public Placemark getPlacemark() {
        return placemark;
    }

    public void setPlacemark(Placemark placemark) {
        this.placemark = placemark;
    }

}
