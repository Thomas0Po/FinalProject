
package fr.wedley.clientwebservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.wedley.clientwebservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetLastSoftwareVersions_QNAME = new QName("http://webservices.wedley.fr/", "getLastSoftwareVersions");
    private final static QName _GetLastSoftwareVersionsResponse_QNAME = new QName("http://webservices.wedley.fr/", "getLastSoftwareVersionsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.wedley.clientwebservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLastSoftwareVersions }
     * 
     */
    public GetLastSoftwareVersions createGetLastSoftwareVersions() {
        return new GetLastSoftwareVersions();
    }

    /**
     * Create an instance of {@link GetLastSoftwareVersionsResponse }
     * 
     */
    public GetLastSoftwareVersionsResponse createGetLastSoftwareVersionsResponse() {
        return new GetLastSoftwareVersionsResponse();
    }

    /**
     * Create an instance of {@link SoftwareLastVersions }
     * 
     */
    public SoftwareLastVersions createSoftwareLastVersions() {
        return new SoftwareLastVersions();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLastSoftwareVersions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.wedley.fr/", name = "getLastSoftwareVersions")
    public JAXBElement<GetLastSoftwareVersions> createGetLastSoftwareVersions(GetLastSoftwareVersions value) {
        return new JAXBElement<GetLastSoftwareVersions>(_GetLastSoftwareVersions_QNAME, GetLastSoftwareVersions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLastSoftwareVersionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.wedley.fr/", name = "getLastSoftwareVersionsResponse")
    public JAXBElement<GetLastSoftwareVersionsResponse> createGetLastSoftwareVersionsResponse(GetLastSoftwareVersionsResponse value) {
        return new JAXBElement<GetLastSoftwareVersionsResponse>(_GetLastSoftwareVersionsResponse_QNAME, GetLastSoftwareVersionsResponse.class, null, value);
    }

}
