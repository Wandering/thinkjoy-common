/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  ResourceAction.java 2014-10-03 16:39:52 $
 */



package cn.thinkjoy.common.managerui.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class ResourceAction extends CreateBaseDomain{
    private Integer resourceId;
    private String divId;
    private String actionScript;
    private String name;
    private String actionAlias;
    private String description;

	public ResourceAction(){
	}
    public void setResourceId(Integer value) {
        this.resourceId = value;
    }

    public Integer getResourceId() {
        return this.resourceId;
    }
    public void setDivId(String value) {
        this.divId = value;
    }

    public String getDivId() {
        return this.divId;
    }
    public void setActionScript(String value) {
        this.actionScript = value;
    }

    public String getActionScript() {
        return this.actionScript;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setActionAlias(String value) {
        this.actionAlias = value;
    }

    public String getActionAlias() {
        return this.actionAlias;
    }
    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ResourceId",getResourceId())
			.append("DivId",getDivId())
			.append("ActionScript",getActionScript())
			.append("Name",getName())
			.append("ActionAlias",getActionAlias())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Description",getDescription())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ResourceAction == false) return false;
		if(this == obj) return true;
		ResourceAction other = (ResourceAction)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

