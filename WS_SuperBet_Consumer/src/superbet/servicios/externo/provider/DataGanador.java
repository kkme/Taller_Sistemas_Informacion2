
package superbet.servicios.externo.provider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataGanador complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataGanador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ganancia" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="idApuesta" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataGanador", propOrder = {
    "ganancia",
    "idApuesta",
    "idUsuario"
})
public class DataGanador {

    protected Double ganancia;
    protected Integer idApuesta;
    protected Integer idUsuario;

    /**
     * Gets the value of the ganancia property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGanancia() {
        return ganancia;
    }

    /**
     * Sets the value of the ganancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGanancia(Double value) {
        this.ganancia = value;
    }

    /**
     * Gets the value of the idApuesta property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdApuesta() {
        return idApuesta;
    }

    /**
     * Sets the value of the idApuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdApuesta(Integer value) {
        this.idApuesta = value;
    }

    /**
     * Gets the value of the idUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Sets the value of the idUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdUsuario(Integer value) {
        this.idUsuario = value;
    }

}
