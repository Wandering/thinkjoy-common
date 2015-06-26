//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.06.16 时间 11:34:30 AM CST 
//


package cn.thinkjoy.common.dap.model.webfilter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Entry complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Entry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startedDateTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="request" type="{http://www.mashape.com/analytics}Request"/>
 *         &lt;element name="response" type="{http://www.mashape.com/analytics}Response"/>
 *         &lt;element name="timings" type="{http://www.mashape.com/analytics}Timings"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entry", propOrder = {
    "serverIPAddress",
    "clientIPAddress",
    "startedDateTime",
    "time",
    "request",
    "response",
    "timings"
})
public class Entry {

    @XmlElement(required = true)
    protected String serverIPAddress;
    @XmlElement(required = true)
    protected String clientIPAddress;
    @XmlElement(required = true)
    protected String startedDateTime;
    protected long time;
    @XmlElement(required = true)
    protected Request request;
    @XmlElement(required = true)
    protected Response response;
    @XmlElement(required = true)
    protected Timings timings;

    /**
     * 获取serverIPAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerIPAddress() {
        return serverIPAddress;
    }

    /**
     * 设置serverIPAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerIPAddress(String value) {
        this.serverIPAddress = value;
    }

    /**
     * 获取clientIPAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientIPAddress() {
        return clientIPAddress;
    }

    /**
     * 设置clientIPAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientIPAddress(String value) {
        this.clientIPAddress = value;
    }

    /**
     * 获取startedDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartedDateTime() {
        return startedDateTime;
    }

    /**
     * 设置startedDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartedDateTime(String value) {
        this.startedDateTime = value;
    }

    /**
     * 获取time属性的值。
     * 
     */
    public long getTime() {
        return time;
    }

    /**
     * 设置time属性的值。
     * 
     */
    public void setTime(long value) {
        this.time = value;
    }

    /**
     * 获取request属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Request }
     *     
     */
    public Request getRequest() {
        return request;
    }

    /**
     * 设置request属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Request }
     *     
     */
    public void setRequest(Request value) {
        this.request = value;
    }

    /**
     * 获取response属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getResponse() {
        return response;
    }

    /**
     * 设置response属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setResponse(Response value) {
        this.response = value;
    }

    /**
     * 获取timings属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Timings }
     *     
     */
    public Timings getTimings() {
        return timings;
    }

    /**
     * 设置timings属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Timings }
     *     
     */
    public void setTimings(Timings value) {
        this.timings = value;
    }

}
