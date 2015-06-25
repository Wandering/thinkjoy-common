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
 * <p>Response complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="httpVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "Response", propOrder = {
    "status",
    "statusText",
    "httpVersion",
    "headers",
    "headersSize",
    "bodySize",
    "content"
})
public class Response {

    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String statusText;
    @XmlElement(required = true)
    protected String httpVersion;
    protected List<NameValuePair> headers;
    protected int headersSize;
    protected int bodySize;
    protected Content content;

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * 获取statusText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * 设置statusText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusText(String value) {
        this.statusText = value;
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
