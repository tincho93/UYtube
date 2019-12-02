
package publicadores;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtCanalWeb complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtCanalWeb">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomCanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imgUsr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCanalWeb", propOrder = {
    "nickname",
    "nomCanal",
    "imgUsr"
})
public class DtCanalWeb {

    protected String nickname;
    protected String nomCanal;
    protected String imgUsr;

    /**
     * Gets the value of the nickname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the value of the nickname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Gets the value of the nomCanal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomCanal() {
        return nomCanal;
    }

    /**
     * Sets the value of the nomCanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomCanal(String value) {
        this.nomCanal = value;
    }

    /**
     * Gets the value of the imgUsr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgUsr() {
        return imgUsr;
    }

    /**
     * Sets the value of the imgUsr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgUsr(String value) {
        this.imgUsr = value;
    }

}
