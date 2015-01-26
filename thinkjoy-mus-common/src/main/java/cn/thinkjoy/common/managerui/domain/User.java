/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  User.java 2014-09-26 10:33:05 $
 */



package cn.thinkjoy.common.managerui.domain;

import cn.thinkjoy.common.domain.CreateBaseDomain;
import cn.thinkjoy.common.domain.UserDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class User extends UserDomain {
    private Integer isAdmin;

	private Integer bizDimension;

    private Integer employeeId;

	public User(){
	}
    public void setIsAdmin(Integer value) {
        this.isAdmin = value;
    }

    public Integer getIsAdmin() {
        return this.isAdmin;
    }

    public void setEmployeeId(Integer value) {
        this.employeeId = value;
    }

    public Integer getEmployeeId() {
        return this.employeeId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("IsAdmin",getIsAdmin())
			.append("Name",getName())
			.append("Password",getPassword())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("EmployeeId",getEmployeeId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public Integer getBizDimension() {
		return bizDimension;
	}

	public void setBizDimension(Integer bizDimension) {
		this.bizDimension = bizDimension;
	}
}

