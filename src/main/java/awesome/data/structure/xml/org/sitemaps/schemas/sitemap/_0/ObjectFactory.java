//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.07.14 时间 11:16:25 PM CST
//


package awesome.data.structure.xml.org.sitemaps.schemas.sitemap._0;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.sitemaps.schemas.sitemap._0 package. 
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

    private final static QName _Loc_QNAME = new QName("http://www.sitemaps.org/schemas/sitemap/0.9", "loc");
    private final static QName _Lastmod_QNAME = new QName("http://www.sitemaps.org/schemas/sitemap/0.9", "lastmod");
    private final static QName _Priority_QNAME = new QName("http://www.sitemaps.org/schemas/sitemap/0.9", "priority");
    private final static QName _Changefreq_QNAME = new QName("http://www.sitemaps.org/schemas/sitemap/0.9", "changefreq");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.sitemaps.schemas.sitemap._0
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Urlset }
     *
     */
    public Urlset createUrlset() {
        return new Urlset();
    }

    /**
     * Create an instance of {@link Url }
     *
     */
    public Url createUrl() {
        return new Url();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9", name = "loc")
    public JAXBElement<String> createLoc(String value) {
        return new JAXBElement<String>(_Loc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9", name = "lastmod")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createLastmod(String value) {
        return new JAXBElement<String>(_Lastmod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9", name = "priority")
    public JAXBElement<BigDecimal> createPriority(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Priority_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.sitemaps.org/schemas/sitemap/0.9", name = "changefreq")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createChangefreq(String value) {
        return new JAXBElement<String>(_Changefreq_QNAME, String.class, null, value);
    }

}
