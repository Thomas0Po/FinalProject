
package fr.wedley.clientwebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour softwareLastVersions complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété coreSize.
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
     * Définit la valeur de la propriété coreSize.
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
     * Obtient la valeur de la propriété coreURL.
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
     * Définit la valeur de la propriété coreURL.
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
     * Obtient la valeur de la propriété coreVersion.
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
     * Définit la valeur de la propriété coreVersion.
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
     * Obtient la valeur de la propriété navigationSize.
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
     * Définit la valeur de la propriété navigationSize.
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
     * Obtient la valeur de la propriété navigationURL.
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
     * Définit la valeur de la propriété navigationURL.
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
     * Obtient la valeur de la propriété navigationVersion.
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
     * Définit la valeur de la propriété navigationVersion.
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
     * Obtient la valeur de la propriété pluginsSize.
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
     * Définit la valeur de la propriété pluginsSize.
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
     * Obtient la valeur de la propriété pluginsURL.
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
     * Définit la valeur de la propriété pluginsURL.
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
     * Obtient la valeur de la propriété pluginsVersion.
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
     * Définit la valeur de la propriété pluginsVersion.
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
     * Obtient la valeur de la propriété softwareUpdateVersion.
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
     * Définit la valeur de la propriété softwareUpdateVersion.
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
     * Obtient la valeur de la propriété workspaceSize.
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
     * Définit la valeur de la propriété workspaceSize.
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
     * Obtient la valeur de la propriété workspaceURL.
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
     * Définit la valeur de la propriété workspaceURL.
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
     * Obtient la valeur de la propriété workspaceVersion.
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
     * Définit la valeur de la propriété workspaceVersion.
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
