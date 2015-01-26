/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  Resource.java 2014-10-03 16:39:52 $
 */



package cn.thinkjoy.common.managerui.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Resource extends CreateBaseDomain{
    private String url;
    private Integer orderNum;
    private Integer parentId;
    private String number;
    private String longNumber;
    private String name;
    private String description;
    private Integer modelId;
    private String bizModelName;

	public Resource(){
	}
    public void setUrl(String value) {
        this.url = value;
    }

    public String getUrl() {
        return this.url;
    }
    public void setOrderNum(Integer value) {
        this.orderNum = value;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }
    public void setParentId(Integer value) {
        this.parentId = value;
    }

    public Integer getParentId() {
        return this.parentId;
    }
    public void setNumber(String value) {
        this.number = value;
    }

    public String getNumber() {
        return this.number;
    }
    public void setLongNumber(String value) {
        this.longNumber = value;
    }

    public String getLongNumber() {
        return this.longNumber;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }
    public void setModelId(Integer value) {
        this.modelId = value;
    }

    public Integer getModelId() {
        return this.modelId;
    }
    public void setBizModelName(String value) {
        this.bizModelName = value;
    }

    public String getBizModelName() {
        return this.bizModelName;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Url",getUrl())
			.append("OrderNum",getOrderNum())
			.append("ParentId",getParentId())
			.append("Number",getNumber())
			.append("LongNumber",getLongNumber())
			.append("Name",getName())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Description",getDescription())
			.append("ModelId",getModelId())
			.append("BizModelName",getBizModelName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Resource == false) return false;
		if(this == obj) return true;
		Resource other = (Resource)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

