package com.qtone.mh.item.entity;

import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;
import cn.thinkjoy.common.mybatis.core.mybatis.mapping.SelectColumnMapping;
import cn.thinkjoy.common.mybatis.core.mybatis.mapping.SelectColumnMappings;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author shadow
 * @email 124010356@qq.com
 * @create 2015-12-29 13:19
 * 
 */
public class AdcProduct implements PKEntity<Long> {

    /**
     * null<br/>
     * 对应数据库字段 RZZX.ADC_PRODUCT.ID
     *
     * @mbggenerated 2015-12-29 13:26
     */
    private Long id;

    /**
     * null<br/>
     * 对应数据库字段 RZZX.ADC_PRODUCT.NAME
     *
     * @mbggenerated 2015-12-29 13:26
     */
    private String name;

    /**
     * null<br/>
     * 对应数据库字段 RZZX.ADC_PRODUCT.CREATE_DATE
     *
     * @mbggenerated 2015-12-29 13:26
     */
    private Date createDate;

    /**
     * null<br/>
     * 对应数据库字段 RZZX.ADC_PRODUCT.LINK_URL
     *
     * @mbggenerated 2015-12-29 13:26
     */
    private String linkUrl;

    /**
     * null<br/>
     * 对应数据库字段 RZZX.ADC_PRODUCT.SECTION
     *
     * @mbggenerated 2015-12-29 13:26
     */
    private BigDecimal section;


    /**
     * 返回: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.ID
     *
     * @返回  RZZX.ADC_PRODUCT.ID
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public Long getId() {
        return id;
    }

    /**
     *  设置: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.ID
     *
     * @param id null
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 返回: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.NAME
     *
     * @返回  RZZX.ADC_PRODUCT.NAME
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public String getName() {
        return name;
    }

    /**
     *  设置: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.NAME
     *
     * @param name null
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 返回: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.CREATE_DATE
     *
     * @返回  RZZX.ADC_PRODUCT.CREATE_DATE
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *  设置: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.CREATE_DATE
     *
     * @param createDate null
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 返回: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.LINK_URL
     *
     * @返回  RZZX.ADC_PRODUCT.LINK_URL
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     *  设置: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.LINK_URL
     *
     * @param linkUrl null
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * 返回: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.SECTION
     *
     * @返回  RZZX.ADC_PRODUCT.SECTION
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public BigDecimal getSection() {
        return section;
    }

    /**
     *  设置: null<br>
     * 对应数据库字段: RZZX.ADC_PRODUCT.SECTION
     *
     * @param section null
     *
     * @mbggenerated 2015-12-29 13:26
     */
    public void setSection(BigDecimal section) {
        this.section = section;
    }
}