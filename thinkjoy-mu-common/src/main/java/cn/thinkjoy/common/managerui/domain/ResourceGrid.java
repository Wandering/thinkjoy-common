/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  ResourceGrid.java 2014-10-03 16:39:52 $
 */



package cn.thinkjoy.common.managerui.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class ResourceGrid extends CreateBaseDomain{
    private Integer status;
    private Integer resId;
    private String displayName;
    private String colId;
    private Integer orderNum;
    private Integer width;
    private String editoptions;
    private String edittype;
    private String unformat;
    private String description;
    private String moduleName;

	public ResourceGrid(){
	}
    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }
    public void setResId(Integer value) {
        this.resId = value;
    }

    public Integer getResId() {
        return this.resId;
    }
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    public String getDisplayName() {
        return this.displayName;
    }
    public void setColId(String value) {
        this.colId = value;
    }

    public String getColId() {
        return this.colId;
    }
    public void setOrderNum(Integer value) {
        this.orderNum = value;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }
    public void setWidth(Integer value) {
        this.width = value;
    }

    public Integer getWidth() {
        return this.width;
    }
    public void setEditoptions(String value) {
        this.editoptions = value;
    }

    public String getEditoptions() {
        return this.editoptions;
    }
    public void setEdittype(String value) {
        this.edittype = value;
    }

    public String getEdittype() {
        return this.edittype;
    }
    public void setUnformat(String value) {
        this.unformat = value;
    }

    public String getUnformat() {
        return this.unformat;
    }
    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }
    public void setModuleName(String value) {
        this.moduleName = value;
    }

    public String getModuleName() {
        return this.moduleName;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("ResId",getResId())
			.append("DisplayName",getDisplayName())
			.append("ColId",getColId())
			.append("OrderNum",getOrderNum())
			.append("Width",getWidth())
			.append("Editoptions",getEditoptions())
			.append("Edittype",getEdittype())
			.append("Unformat",getUnformat())
			.append("Description",getDescription())
			.append("ModuleName",getModuleName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ResourceGrid == false) return false;
		if(this == obj) return true;
		ResourceGrid other = (ResourceGrid)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

