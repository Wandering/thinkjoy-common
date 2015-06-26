//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.06.16 时间 11:34:30 AM CST 
//


package cn.thinkjoy.common.filter.model;

import cn.thinkjoy.common.filter.context.IUserContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Message complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Message">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="har" type="{http://www.mashape.com/analytics}Har"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", propOrder = {
    "serviceToken",
    "har"
})
public class Message {

    @XmlElement(required = true)
    protected String serviceToken;
    @XmlElement(required = true)
    protected Har har;
    @XmlElement(required = true)
    protected IUserContext userContext;
    /**
     * 获取serviceToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceToken() {
        return serviceToken;
    }

    /**
     * 设置serviceToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceToken(String value) {
        this.serviceToken = value;
    }

    /**
     * 获取har属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Har }
     *     
     */
    public Har getHar() {
        return har;
    }

    /**
     * 设置har属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Har }
     *     
     */
    public void setHar(Har value) {
        this.har = value;
    }

    public IUserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(IUserContext userContext) {
        this.userContext = userContext;
    }
}
