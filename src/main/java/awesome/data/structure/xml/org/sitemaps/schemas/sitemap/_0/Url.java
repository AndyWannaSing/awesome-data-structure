//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.07.14 时间 11:16:25 PM CST
//


package awesome.data.structure.xml.org.sitemaps.schemas.sitemap._0;

import awesome.data.structure.xml.com.baidu.schemas.sitemap_mobile._1.Mobile;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>anonymous complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.sitemaps.org/schemas/sitemap/0.9}loc"/>
 *         &lt;element ref="{http://www.baidu.com/schemas/sitemap-mobile/1/}mobile" minOccurs="0"/>
 *         &lt;element ref="{http://www.sitemaps.org/schemas/sitemap/0.9}lastmod"/>
 *         &lt;element ref="{http://www.sitemaps.org/schemas/sitemap/0.9}changefreq"/>
 *         &lt;element ref="{http://www.sitemaps.org/schemas/sitemap/0.9}priority"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "loc",
        "mobile",
        "lastmod",
        "changefreq",
        "priority"
})
@XmlRootElement(name = "url")
public class Url {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String loc;
    @XmlElement(namespace = "http://www.baidu.com/schemas/sitemap-mobile/1/")
    protected Mobile mobile;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String lastmod;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String changefreq;
    @XmlElement(required = true)
    protected BigDecimal priority;

    /**
     * 获取loc属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLoc() {
        return loc;
    }

    /**
     * 设置loc属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLoc(String value) {
        this.loc = value;
    }

    /**
     * 获取mobile属性的值。
     *
     * @return
     *     possible object is
     *     {@link Mobile }
     *
     */
    public Mobile getMobile() {
        return mobile;
    }

    /**
     * 设置mobile属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link Mobile }
     *
     */
    public void setMobile(Mobile value) {
        this.mobile = value;
    }

    /**
     * 获取lastmod属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastmod() {
        return lastmod;
    }

    /**
     * 设置lastmod属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastmod(String value) {
        this.lastmod = value;
    }

    /**
     * 获取changefreq属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getChangefreq() {
        return changefreq;
    }

    /**
     * 设置changefreq属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setChangefreq(String value) {
        this.changefreq = value;
    }

    /**
     * 获取priority属性的值。
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getPriority() {
        return priority;
    }

    /**
     * 设置priority属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPriority(BigDecimal value) {
        this.priority = value;
    }

}
