package net.kmlexport.models.staticpath;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "Style")
public class Style implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private LineStyle lineStyle;
    private PolyStyle polyStyle;

    public Style() {}

    public Style(String id, LineStyle lineStyle, PolyStyle polyStyle) {
        this.id = id;
        this.lineStyle = lineStyle;
        this.polyStyle = polyStyle;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "LineStyle")
    public LineStyle getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    @XmlElement(name = "PolyStyle")
    public PolyStyle getPolyStyle() {
        return polyStyle;
    }

    public void setPolyStyle(PolyStyle poleStyle) {
        this.polyStyle = poleStyle;
    }

}
