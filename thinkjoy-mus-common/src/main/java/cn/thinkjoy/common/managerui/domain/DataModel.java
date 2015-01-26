/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  DataModel.java 2014-10-03 16:39:50 $
 */



package cn.thinkjoy.common.managerui.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class DataModel extends CreateBaseDomain{
    private Integer priority;
    private Integer modelId;
    private String assignUrl;
    private String whereSql;
    private String name;

	public DataModel(){
	}
    public void setPriority(Integer value) {
        this.priority = value;
    }

    public Integer getPriority() {
        return this.priority;
    }
    public void setModelId(Integer value) {
        this.modelId = value;
    }

    public Integer getModelId() {
        return this.modelId;
    }
    public void setAssignUrl(String value) {
        this.assignUrl = value;
    }

    public String getAssignUrl() {
        return this.assignUrl;
    }
    public void setWhereSql(String value) {
        this.whereSql = value;
    }

    public String getWhereSql() {
        return this.whereSql;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("Priority",getPriority())
			.append("ModelId",getModelId())
			.append("AssignUrl",getAssignUrl())
			.append("WhereSql",getWhereSql())
			.append("Name",getName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DataModel == false) return false;
		if(this == obj) return true;
		DataModel other = (DataModel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

