//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.06.16 时间 11:34:30 AM CST 
//


package cn.thinkjoy.common.dap.model.webfilter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Timings complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Timings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="send" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="wait" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="receive" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Timings", propOrder = {
    "send",
    "wait",
    "receive"
})
public class Timings {

    protected long send;
    protected long wait;
    protected long receive;

    /**
     * 获取send属性的值。
     * 
     */
    public long getSend() {
        return send;
    }

    /**
     * 设置send属性的值。
     * 
     */
    public void setSend(long value) {
        this.send = value;
    }

    /**
     * 获取wait属性的值。
     * 
     */
    public long getWait() {
        return wait;
    }

    /**
     * 设置wait属性的值。
     * 
     */
    public void setWait(long value) {
        this.wait = value;
    }

    /**
     * 获取receive属性的值。
     * 
     */
    public long getReceive() {
        return receive;
    }

    /**
     * 设置receive属性的值。
     * 
     */
    public void setReceive(long value) {
        this.receive = value;
    }

}
