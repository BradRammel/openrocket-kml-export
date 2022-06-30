package net.kmlexport.models.staticpath;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"color", "width"})
public class LineStyle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String color;
    private Integer width;

    public LineStyle() {}

    public LineStyle(String color, Integer width) {
        this.color = color;
        this.width = width;
    }

    @XmlElement
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @XmlElement
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}
