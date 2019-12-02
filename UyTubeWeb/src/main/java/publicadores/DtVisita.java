
package publicadores;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtVisita complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtVisita">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantVisitas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nomVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ultimaVisita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="urlVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usrVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtVisita", propOrder = {
    "cantVisitas",
    "nomVideo",
    "ultimaVisita",
    "urlVideo",
    "usrVideo"
})
public class DtVisita {

    protected Integer cantVisitas;
    protected String nomVideo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ultimaVisita;
    protected String urlVideo;
    protected String usrVideo;

    /**
     * Gets the value of the cantVisitas property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantVisitas() {
        return cantVisitas;
    }

    /**
     * Sets the value of the cantVisitas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantVisitas(Integer value) {
        this.cantVisitas = value;
    }

    /**
     * Gets the value of the nomVideo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomVideo() {
        return nomVideo;
    }

    /**
     * Sets the value of the nomVideo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomVideo(String value) {
        this.nomVideo = value;
    }

    /**
     * Gets the value of the ultimaVisita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUltimaVisita() {
        return ultimaVisita;
    }

    /**
     * Sets the value of the ultimaVisita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUltimaVisita(XMLGregorianCalendar value) {
        this.ultimaVisita = value;
    }

    /**
     * Gets the value of the urlVideo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Sets the value of the urlVideo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlVideo(String value) {
        this.urlVideo = value;
    }

    /**
     * Gets the value of the usrVideo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsrVideo() {
        return usrVideo;
    }

    /**
     * Sets the value of the usrVideo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsrVideo(String value) {
        this.usrVideo = value;
    }

}
