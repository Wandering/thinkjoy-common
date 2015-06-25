//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.06.16 时间 11:34:30 AM CST 
//


package cn.thinkjoy.common.filter.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Request complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="method" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="httpVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="queryString" type="{http://www.mashape.com/analytics}NameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="headers" type="{http://www.mashape.com/analytics}NameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="headersSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="bodySize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="content" type="{http://www.mashape.com/analytics}Content" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", propOrder = {
    "method",
    "url",
    "httpVersion",
    "queryString",
    "headers",
    "headersSize",
    "bodySize",
    "content"
})
public class Request {

    @XmlElement(required = true)
    protected String method;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(required = true)
    protected String httpVersion;
    protected List<NameValuePair> queryString;
    protected List<NameValuePair> headers;
    protected int headersSize;
    protected int bodySize;
    protected Content content;

    /**
     * 获取method属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置method属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethod(String value) {
        this.method = value;
    }

    /**
     * 获取url属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * 获取httpVersion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHttpVersion() {
        return httpVersion;
    }

    /**
     * 设置httpVersion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHttpVersion(String value) {
        this.httpVersion = value;
    }

    /**
     * Gets the value of the queryString property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queryString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueryString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValuePair }
     * 
     * 
     */
    public List<NameValuePair> getQueryString() {
        if (queryString == null) {
            queryString = new ArrayList<NameValuePair>();
        }
        return this.queryString;
    }

    /**
     * Gets the value of the headers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the headers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHeaders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValuePair }
     * 
     * 
     */
    public List<NameValuePair> getHeaders() {
        if (headers == null) {
            headers = new ArrayList<NameValuePair>();
        }
        return this.headers;
    }

    /**
     * 获取headersSize属性的值。
     * 
     */
    public int getHeadersSize() {
        return headersSize;
    }

    /**
     * 设置headersSize属性的值。
     * 
     */
    public void setHeadersSize(int value) {
        this.headersSize = value;
    }

    /**
     * 获取bodySize属性的值。
     * 
     */
    public int getBodySize() {
        return bodySize;
    }

    /**
     * 设置bodySize属性的值。
     * 
     */
    public void setBodySize(int value) {
        this.bodySize = value;
    }

    /**
     * 获取content属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Content }
     *     
     */
    public Content getContent() {
        return content;
    }

    /**
     * 设置content属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Content }
     *     
     */
    public void setContent(Content value) {
        this.content = value;
    }

}
