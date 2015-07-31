/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: cmc
 * $Id:  Datagroup.java 2014-10-27 10:37:54 $
 */



package cn.thinkjoy.common.managerui.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Datagroup extends CreateBaseDomain<Long>{
    private String name;
    private String number;
    private String description;

	public Datagroup(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setNumber(String value) {
        this.number = value;
    }

    public String getNumber() {
        return this.number;
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
			.append("Name",getName())
			.append("Number",getNumber())
			.append("Description",getDescription())
			.append("Status",getStatus())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Datagroup == false) return false;
		if(this == obj) return true;
		Datagroup other = (Datagroup)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

