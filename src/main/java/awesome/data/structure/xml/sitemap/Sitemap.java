//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.07.15 时间 03:25:13 AM CST
//


package awesome.data.structure.xml.sitemap;

import javax.xml.bind.annotation.*;
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
 *         &lt;element ref="{}loc"/>
 *         &lt;element ref="{}lastmod"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "loc",
        "lastmod"
})
@XmlRootElement(name = "sitemap")
public class Sitemap {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String loc;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String lastmod;

    /**
     * 获取loc属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getLoc() {
        return loc;
    }

    /**
     * 设置loc属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLoc(String value) {
        this.loc = value;
    }

    /**
     * 获取lastmod属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getLastmod() {
        return lastmod;
    }

    /**
     * 设置lastmod属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLastmod(String value) {
        this.lastmod = value;
    }

}
