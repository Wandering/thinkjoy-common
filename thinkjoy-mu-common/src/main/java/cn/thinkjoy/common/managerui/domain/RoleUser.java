/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  RoleUser.java 2014-10-03 16:39:53 $
 */



package cn.thinkjoy.common.managerui.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class RoleUser extends CreateBaseDomain{
    private Integer status;
    private Integer userId;
    private Integer roleId;

	public RoleUser(){
	}
    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setRoleId(Integer value) {
        this.roleId = value;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("UserId",getUserId())
			.append("RoleId",getRoleId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RoleUser == false) return false;
		if(this == obj) return true;
		RoleUser other = (RoleUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

