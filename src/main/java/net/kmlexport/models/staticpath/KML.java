package net.kmlexport.models.staticpath;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "kml")
public class KML implements Serializable {

    private static final long serialVersionUID = 1L;

    private Document document;
    private String xmlns;

    public KML () {}

    public KML(Document document, String xmlns) {
        this.document = document;
        this.xmlns = xmlns;
    }

    @XmlElement(name = "Document")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @XmlAttribute
    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

}
