package awesome.data.structure.xml;

import awesome.data.structure.xml.com.baidu.schemas.sitemap_mobile._1.Mobile;
import awesome.data.structure.xml.org.sitemaps.schemas.sitemap._0.Url;
import awesome.data.structure.xml.org.sitemaps.schemas.sitemap._0.Urlset;
import awesome.data.structure.xml.sitemap.Sitemap;
import awesome.data.structure.xml.sitemap.Sitemapindex;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Andy
 * @time: 2019/7/13 17:06
 * @since
 */
public class XMLUtils {
    public static void xmlTojava() throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用Urlset
        JAXBContext context = JAXBContext.newInstance(Urlset.class);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        InputStream stream = new FileInputStream("D:/workspace/xml/test.xml");
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        Urlset urlset = (Urlset) unmarshaller.unmarshal(stream);
        // 将结果打印到控制台（需要重写 urlset 的 toString 方法）
        System.out.println(urlset);
    }


    public static void javaToxml(Urlset urlset) throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用Urlset
        JAXBContext context = JAXBContext.newInstance(Urlset.class);
        // 创建 Marshaller 实例
        Marshaller marshaller = context.createMarshaller();
        // 设置转换参数 -> 这里举例是告诉序列化器是否格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //省略掉 xml 头部的 standalone
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
        // 构建输出环境 -> 这里使用标准输出，输出到 D:/workspace/xml/create_test.xml
        OutputStream out = new FileOutputStream("D:/workspace/xml/create_test.xml");
        // 将所需对象序列化 -> 该方法没有返回值
        marshaller.marshal(urlset, out);
    }

    public static void javaToxml(Sitemapindex sitemapindex) throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用Urlset
        JAXBContext context = JAXBContext.newInstance(Sitemapindex.class);
        // 创建 Marshaller 实例
        Marshaller marshaller = context.createMarshaller();
        // 设置转换参数 -> 这里举例是告诉序列化器是否格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //省略掉 xml 头部的 standalone
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
        // 构建输出环境 -> 这里使用标准输出，输出到 D:/workspace/xml/create_test.xml
        OutputStream out = new FileOutputStream("D:/workspace/xml/create_sitemapindex.xml");
        // 将所需对象序列化 -> 该方法没有返回值
        marshaller.marshal(sitemapindex, out);
    }

    public static <T> void javabeanToXml(T object, Class<T> clazz, String filePath) throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里根据clazz获取
        JAXBContext context = JAXBContext.newInstance(clazz);
        // 创建 Marshaller 实例
        Marshaller marshaller = context.createMarshaller();
        // 设置转换参数 -> 这里举例是告诉序列化器是否格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //省略掉 xml 头部的 standalone
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
        // 构建输出环境 -> 这里使用标准输出，输出到 D:/workspace/xml/create_test.xml
        OutputStream out = new FileOutputStream(filePath);
        // 将所需对象序列化 -> 该方法没有返回值
        marshaller.marshal(object, out);
    }

    public static <T> T xmlToJavabean(Class<T> clazz, String filePath) throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用根据clazz获取
        JAXBContext context = JAXBContext.newInstance(clazz);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        InputStream stream = new FileInputStream(filePath);
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        return (T) unmarshaller.unmarshal(stream);
    }


    public static void main(String[] args) throws Exception {
        /*Urlset urlset = getUrlset();
        //生成 xml 文件
        javaToxml(urlset);*/

        /*Sitemapindex sitemapindex = getSitemapindex();
        //生成 xml 文件
        javaToxml(sitemapindex);*/

        String filePath = "D:/workspace/xml/create_sitemapindex.xml";
        javabeanToXml(getSitemapindex(), Sitemapindex.class, filePath);
    }

    /**
     * 根据以下 xml 文件获得 Urlset 对象
     * <p>
     * <?xml version="1.0" encoding="UTF-8" ?>
     * <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
     * xmlns:mobile="http://www.baidu.com/schemas/sitemap-mobile/1/">
     * <url>
     * <loc>http://m.example.com/index.html</loc>
     * <mobile:mobile type="mobile"/>
     * <lastmod>2009-12-14</lastmod>
     * <changefreq>daily</changefreq>
     * <priority>0.8</priority>
     * </url>
     * <url>
     * <loc>http://www.example.com/index.html</loc>
     * <lastmod>2009-12-14</lastmod>
     * <changefreq>daily</changefreq>
     * <priority>0.8</priority>
     * </url>
     * <url>
     * <loc>http://www.example.com/autoadapt.html</loc>
     * <mobile:mobile type="pc,mobile"/>
     * <lastmod>2009-12-14</lastmod>
     * <changefreq>daily</changefreq>
     * <priority>0.8</priority>
     * </url>
     * <url>
     * <loc>http://www.example.com/htmladapt.html</loc>
     * <mobile:mobile type="htmladapt"/>
     * <lastmod>2009-12-14</lastmod>
     * <changefreq>daily</changefreq>
     * <priority>0.8</priority>
     * </url>
     * </urlset>
     */
    private static Urlset getUrlset() {
        /**
         * 第一个url对象：
         *
         *     <url>
         *         <loc>http://m.example.com/index.html</loc>
         *         <mobile:mobile type="mobile"/>
         *         <lastmod>2009-12-14</lastmod>
         *         <changefreq>daily</changefreq>
         *         <priority>0.8</priority>
         *     </url>
         */
        Url url1 = new Url();
        url1.setLoc("http://m.example.com/index.html");
        Mobile mobile1 = new Mobile();
        mobile1.setType("mobile");
        url1.setMobile(mobile1);
        url1.setLastmod("2009-12-14");
        url1.setChangefreq("daily");
        url1.setPriority(BigDecimal.valueOf(0.8));

        /**
         * 第二个 url 对象
         *
         *     <url>
         *         <loc>http://www.example.com/index.html</loc>
         *         <lastmod>2009-12-14</lastmod>
         *         <changefreq>daily</changefreq>
         *         <priority>0.8</priority>
         *     </url>
         */
        Url url2 = new Url();
        url2.setLoc("http://www.example.com/index.html");
        url2.setLastmod("2009-12-14");
        url2.setChangefreq("daily");
        url2.setPriority(BigDecimal.valueOf(0.8));

        /**
         * 第三个 url 对象
         *
         *     <url>
         *         <loc>http://www.example.com/autoadapt.html</loc>
         *         <mobile:mobile type="pc,mobile"/>
         *         <lastmod>2009-12-14</lastmod>
         *         <changefreq>daily</changefreq>
         *         <priority>0.8</priority>
         *     </url>
         */
        Url url3 = new Url();
        url3.setLoc("http://www.example.com/autoadapt.html");
        Mobile mobile3 = new Mobile();
        mobile3.setType("pc,mobile");
        url3.setMobile(mobile3);
        url3.setLastmod("2009-12-14");
        url3.setChangefreq("daily");
        url3.setPriority(BigDecimal.valueOf(0.8));

        /**
         * 第四个 url 对象
         *
         *     <url>
         *         <loc>http://www.example.com/htmladapt.html</loc>
         *         <mobile:mobile type="htmladapt"/>
         *         <lastmod>2009-12-14</lastmod>
         *         <changefreq>daily</changefreq>
         *         <priority>0.8</priority>
         *     </url>
         */
        Url url4 = new Url();
        url4.setLoc("http://www.example.com/htmladapt.html");
        Mobile mobile4 = new Mobile();
        mobile4.setType("htmladapt");
        url4.setMobile(mobile4);
        url4.setLastmod("2009-12-14");
        url4.setChangefreq("daily");
        url4.setPriority(BigDecimal.valueOf(0.8));

        //获得一个包含 4 个url 的 Urlset 对象
        Urlset urlset = new Urlset();
        List<Url> urlList = new ArrayList<>();
        urlList.add(url1);
        urlList.add(url2);
        urlList.add(url3);
        urlList.add(url4);
        for (int i = 0; i < 50000 - 4; i++) {
            urlList.add(url1);
        }
        // setUrl 方式需要自己添加。xjc 生成的 Javabean 对象只有 getUrl 方法
        urlset.setUrl(urlList);
        return urlset;
    }

    /**
     * 根据以下文件内容获得 Sitemapindex 对象
     * <p>
     * <?xml version="1.0" encoding="utf-8"?>
     * <!-- XML文件需以utf-8编码-->
     * <sitemapindex>
     * <!--必填，以 <sitemapindex> 开始标记作为开始，以 </sitemapindex> 结束标记作为结束-->
     * <sitemap>
     * <!--必填，以<sitemap>标签提交一个子sitemap文件-->
     * <loc>http://example.com/ext/xmlsitemap/add/201201/index_20120106.xml</loc>
     * <p>
     * <!--必填，识别sitemap的位置-->
     * <lastmod>2009-12-14</lastmod>
     * <!--选填，识别相对sitemap文件的修改时间-->
     * </sitemap>
     * <!--必填，标签闭合-->
     * <!--必填，以 <sitemapindex> 开始标记作为开始，以 </sitemapindex> 结束标记作为结束-->
     * <sitemap>
     * <!--必填，以<sitemap>标签提交一个子sitemap文件-->
     * <loc>http://example.com/ext/xmlsitemap/add/201201/index_20120106.xml</loc>
     * <p>
     * <!--必填，识别sitemap的位置-->
     * <lastmod>2009-12-14</lastmod>
     * <!--选填，识别相对sitemap文件的修改时间-->
     * </sitemap>
     * <!--必填，标签闭合-->
     * </sitemapindex>
     * <!--必填，标签闭合-->
     *
     * @return
     */
    private static Sitemapindex getSitemapindex() {
        /**
         * <!--必填，以 <sitemapindex> 开始标记作为开始，以 </sitemapindex> 结束标记作为结束-->
         *     <sitemap>
         *         <!--必填，以<sitemap>标签提交一个子sitemap文件-->
         *         <loc>http://example.com/ext/xmlsitemap/add/201201/index_20120106.xml</loc>
         *
         *         <!--必填，识别sitemap的位置-->
         *         <lastmod>2009-12-14</lastmod>
         *         <!--选填，识别相对sitemap文件的修改时间-->
         *     </sitemap>
         *     <!--必填，标签闭合-->
         */
        Sitemap sitemap1 = new Sitemap();
        sitemap1.setLoc("http://example.com/ext/xmlsitemap/add/201201/index_20120106.xml");
        sitemap1.setLastmod("2009-12-14");

        /**
         * <!--必填，以 <sitemapindex> 开始标记作为开始，以 </sitemapindex> 结束标记作为结束-->
         *     <sitemap>
         *         <!--必填，以<sitemap>标签提交一个子sitemap文件-->
         *         <loc>http://example.com/ext/xmlsitemap/add/201201/index_20120106.xml</loc>
         *
         *         <!--必填，识别sitemap的位置-->
         *         <lastmod>2009-12-14</lastmod>
         *         <!--选填，识别相对sitemap文件的修改时间-->
         *     </sitemap>
         *     <!--必填，标签闭合-->
         */
        Sitemap sitemap2 = new Sitemap();
        sitemap2.setLoc("http://example.com/ext/xmlsitemap/add/201201/index_20120106.xml");
        sitemap2.setLastmod("2009-12-14");

        Sitemapindex sitemapindex = new Sitemapindex();
        List<Sitemap> sitemapList = new ArrayList<>();
        sitemapList.add(sitemap1);
        sitemapList.add(sitemap2);
        sitemapindex.setSitemap(sitemapList);

        return sitemapindex;
    }
}
