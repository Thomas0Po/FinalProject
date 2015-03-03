
package fr.wedley.clientwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour softwareLastVersions complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="softwareLastVersions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coreSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="coreURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coreVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="navigationSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="navigationURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="navigationVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pluginsSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pluginsURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pluginsVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="softwareUpdateVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workspaceSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="workspaceURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workspaceVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "softwareLastVersions", propOrder = {
    "coreSize",
    "coreURL",
    "coreVersion",
    "navigationSize",
    "navigationURL",
    "navigationVersion",
    "pluginsSize",
    "pluginsURL",
    "pluginsVersion",
    "softwareUpdateVersion",
    "workspaceSize",
    "workspaceURL",
    "workspaceVersion"
})
public class SoftwareLastVersions {

    protected Integer coreSize;
    protected String coreURL;
    protected String coreVersion;
    protected Integer navigationSize;
    protected String navigationURL;
    protected String navigationVersion;
    protected Integer pluginsSize;
    protected String pluginsURL;
    protected String pluginsVersion;
    protected String softwareUpdateVersion;
    protected Integer workspaceSize;
    protected String workspaceURL;
    protected String workspaceVersion;

    /**
     * Obtient la valeur de la propri�t� coreSize.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCoreSize() {
        return coreSize;
    }

    /**
     * D�finit la valeur de la propri�t� coreSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCoreSize(Integer value) {
        this.coreSize = value;
    }

    /**
     * Obtient la valeur de la propri�t� coreURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoreURL() {
        return coreURL;
    }

    /**
     * D�finit la valeur de la propri�t� coreURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoreURL(String value) {
        this.coreURL = value;
    }

    /**
     * Obtient la valeur de la propri�t� coreVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoreVersion() {
        return coreVersion;
    }

    /**
     * D�finit la valeur de la propri�t� coreVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoreVersion(String value) {
        this.coreVersion = value;
    }

    /**
     * Obtient la valeur de la propri�t� navigationSize.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNavigationSize() {
        return navigationSize;
    }

    /**
     * D�finit la valeur de la propri�t� navigationSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNavigationSize(Integer value) {
        this.navigationSize = value;
    }

    /**
     * Obtient la valeur de la propri�t� navigationURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNavigationURL() {
        return navigationURL;
    }

    /**
     * D�finit la valeur de la propri�t� navigationURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNavigationURL(String value) {
        this.navigationURL = value;
    }

    /**
     * Obtient la valeur de la propri�t� navigationVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNavigationVersion() {
        return navigationVersion;
    }

    /**
     * D�finit la valeur de la propri�t� navigationVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNavigationVersion(String value) {
        this.navigationVersion = value;
    }

    /**
     * Obtient la valeur de la propri�t� pluginsSize.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPluginsSize() {
        return pluginsSize;
    }

    /**
     * D�finit la valeur de la propri�t� pluginsSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPluginsSize(Integer value) {
        this.pluginsSize = value;
    }

    /**
     * Obtient la valeur de la propri�t� pluginsURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPluginsURL() {
        return pluginsURL;
    }

    /**
     * D�finit la valeur de la propri�t� pluginsURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPluginsURL(String value) {
        this.pluginsURL = value;
    }

    /**
     * Obtient la valeur de la propri�t� pluginsVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPluginsVersion() {
        return pluginsVersion;
    }

    /**
     * D�finit la valeur de la propri�t� pluginsVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPluginsVersion(String value) {
        this.pluginsVersion = value;
    }

    /**
     * Obtient la valeur de la propri�t� softwareUpdateVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareUpdateVersion() {
        return softwareUpdateVersion;
    }

    /**
     * D�finit la valeur de la propri�t� softwareUpdateVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareUpdateVersion(String value) {
        this.softwareUpdateVersion = value;
    }

    /**
     * Obtient la valeur de la propri�t� workspaceSize.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkspaceSize() {
        return workspaceSize;
    }

    /**
     * D�finit la valeur de la propri�t� workspaceSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkspaceSize(Integer value) {
        this.workspaceSize = value;
    }

    /**
     * Obtient la valeur de la propri�t� workspaceURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkspaceURL() {
        return workspaceURL;
    }

    /**
     * D�finit la valeur de la propri�t� workspaceURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkspaceURL(String value) {
        this.workspaceURL = value;
    }

    /**
     * Obtient la valeur de la propri�t� workspaceVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkspaceVersion() {
        return workspaceVersion;
    }

    /**
     * D�finit la valeur de la propri�t� workspaceVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkspaceVersion(String value) {
        this.workspaceVersion = value;
    }

}
