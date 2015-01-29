/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: tecc-cn.thinkjoy.common
 * $Id: CreateBaseDomain.java 2014年4月14日 下午10:40:25 $
 */
package cn.thinkjoy.common.domain;

import java.util.Date;

/**
 * 具有操作员操作属性的domain
 * <p/>
 * 创建时间: 2014年4月14日 下午10:40:25 <br/>
 *
 * @author qyang
 * @version
 * @since v0.0.1
 */
public class CreateBaseDomain extends BaseDomain {
    /** 创建人及创建时间 */
    private Long              creator;
    private Long              createDate;

    /** 最后修改人及最后修改时间 */
    private Long              lastModifier;
    private Long              lastModDate = System.currentTimeMillis();
    private Integer               status;

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Long lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Long getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Long lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDateAsDate() {
        if(createDate == null){
            return new Date();
        }
        return createDate > 0 ? new Date(createDate) : null;
    }

    public Date getLastModDateAsDate() {
        if(lastModDate == null){
            return new Date();
        }
        return lastModDate > 0 ? new Date(lastModDate) : null;
    }
}
