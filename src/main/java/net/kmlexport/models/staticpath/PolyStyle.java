package net.kmlexport.models.staticpath;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class PolyStyle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String color;

    public PolyStyle() {}

    public PolyStyle(String color) {
        this.color = color;
    }

    @XmlElement
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
